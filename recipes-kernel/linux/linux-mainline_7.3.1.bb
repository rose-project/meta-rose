SUMMARY = "Mainline Linux Kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel kernel-yocto

LINUX_VERSION ?= "${PV}"
LINUX_VERSION_EXTENSION = "-rose"

KBRANCH = "linux-7.3.y"

SRCREV = "79a907609d53d5cd645b42c11550d295d43abf99"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=${KBRANCH} \
    file://industrial-ethernet.cfg \
    file://usb-ethernet.cfg \
    "

S = "${WORKDIR}/git"

KCONFIG_MODE = "--alldefconfig"

# The base configuration is machine specific and comes from the BSP layer that
# owns the machine: a defconfig in SRC_URI, or KBUILD_DEFCONFIG naming one of the
# kernel's in-tree defconfigs. Each BSP also opens COMPATIBLE_MACHINE for the
# machines it supports; on its own this recipe builds for nothing.
COMPATIBLE_MACHINE = "^$"