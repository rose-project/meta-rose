DESCRIPTION = "The ROSE Pacakge Service library"
SECTION = "rose"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

RDEPENDS_${PN} = "openssl jansson libarchive boost"

SRC_URI = "git://github.com/rose-project/rps.git"
SRCREV = "51854fcaad0889efd6cb7b77ff3c47f6b2e67d4c"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OEMAKE = ""
