SUMMARY = "Fixed OpenSSH host keys, generated once per build directory"
DESCRIPTION = "Installs pre-generated SSH host keys into /etc/ssh so a device \
presents the same host identity after every reflash. Without this, sshd \
generates fresh host keys on the first boot of every newly flashed rootfs and \
SSH clients print the 'REMOTE HOST IDENTIFICATION HAS CHANGED' warning. \
\
The keys are deliberately kept out of recipe space: they live in \
ROSE_SSH_HOSTKEY_DIR inside the build directory, are generated with ssh-keygen \
the first time this recipe is built and reused by every following build. \
\
Every image built from that build directory ships the same private keys, and \
those keys sit unencrypted on the build host. This is a convenience for lab and \
development devices only. It is NOT part of rose-image by default and must not \
be enabled for production images; opt in per build directory with \
EXTRA_IMAGE_FEATURES += \"ssh-fixed-hostkeys\" in local.conf."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit allarch

INHIBIT_DEFAULT_DEPS = "1"

# Where the keys are kept. Defaults to the build directory, next to tmp/,
# downloads/ and sstate-cache/, so they survive "rm -rf tmp" and
# "bitbake -c cleanall ssh-hostkeys-rose". Override in local.conf to share one
# identity between build directories. Delete a single key file to roll that key.
ROSE_SSH_HOSTKEY_DIR ?= "${TOPDIR}/ssh-hostkeys"
# oe-core's openssh only wires the ecdsa key into sshd_config by default
# (PACKAGECONFIG hostkey-ecdsa); the others are generated anyway so a changed
# PACKAGECONFIG keeps working.
ROSE_SSH_HOSTKEY_TYPES ?= "rsa ecdsa ed25519"
ROSE_SSH_HOSTKEY_COMMENT ?= "rose-fixed-hostkey"

do_fetch[noexec] = "1"
do_unpack[noexec] = "1"
do_patch[noexec] = "1"
do_configure[noexec] = "1"
do_compile[noexec] = "1"

# Generate whatever key is missing, mirroring what sshd_check_keys does on the
# target. Uses the build host's ssh-keygen (see HOSTTOOLS_NONFATAL in
# conf/distro/rose.conf), since openssh has no -native recipe.
do_generate_hostkeys() {
    if ! command -v ssh-keygen >/dev/null 2>&1; then
        bbfatal "ssh-keygen is not available on the build host; it is needed to generate the fixed SSH host keys. Install the OpenSSH client, or drop 'ssh-fixed-hostkeys' from EXTRA_IMAGE_FEATURES."
    fi
    mkdir -p -m 0700 "${ROSE_SSH_HOSTKEY_DIR}"
    for type in ${ROSE_SSH_HOSTKEY_TYPES}; do
        key="${ROSE_SSH_HOSTKEY_DIR}/ssh_host_${type}_key"
        [ -f "${key}" ] && continue
        bbnote "generating fixed SSH ${type} host key ${key}"
        rm -f "${key}.tmp" "${key}.tmp.pub"
        ssh-keygen -q -t "${type}" -N '' -C '${ROSE_SSH_HOSTKEY_COMMENT}' -f "${key}.tmp"
        chmod 0600 "${key}.tmp"
        mv -f "${key}.tmp.pub" "${key}.pub"
        mv -f "${key}.tmp" "${key}"
    done
}
addtask generate_hostkeys before do_install

# ${TOPDIR} is excluded from task signatures, so these checksum entries, and
# only these, tie both tasks to the key files: a deleted key re-runs the
# generation, a changed or rolled key rebuilds the package. (Not [nostamp]: a
# same-recipe dependency without a stamp makes bitbake re-run do_install and
# the whole packaging chain on every build.) On the very first build the glob
# is still empty when the signatures are computed, so expect exactly one extra
# run of both tasks on the build after that; not a bug.
do_generate_hostkeys[file-checksums] += "${ROSE_SSH_HOSTKEY_DIR}/ssh_host_*:True"
do_install[file-checksums] += "${ROSE_SSH_HOSTKEY_DIR}/ssh_host_*:True"

do_install() {
    install -d -m 0755 ${D}${sysconfdir}/ssh
    for type in ${ROSE_SSH_HOSTKEY_TYPES}; do
        key="${ROSE_SSH_HOSTKEY_DIR}/ssh_host_${type}_key"
        install -m 0600 "${key}"     ${D}${sysconfdir}/ssh/
        install -m 0644 "${key}.pub" ${D}${sysconfdir}/ssh/
        bbnote "fixed SSH ${type} host key: $(ssh-keygen -lf "${key}.pub")"
    done
    bbwarn "ssh-fixed-hostkeys: packaging the SSH host keys from ${ROSE_SSH_HOSTKEY_DIR}. Every image built here shares this host identity; development builds only."
}

# sshd's init script / sshdgenkeys.service only generate a key when the file is
# missing (openssh's sshd_check_keys), so shipping the files disables runtime
# generation and pins the identity.
FILES:${PN} = "${sysconfdir}/ssh/ssh_host_*"
RDEPENDS:${PN} = "openssh-sshd"

# No sstate archive for this recipe: it would carry the private keys into a
# cache that may be shared or mirrored, and a second build directory with its
# own keys must never be served this one's from a matching hash.
SSTATE_SKIP_CREATION = "1"
