SUMMARY = "Mainline Linux Kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

inherit kernel kernel-yocto

LINUX_VERSION ?= "${PV}"
LINUX_VERSION_EXTENSION = "-rose"

KBRANCH = "linux-7.2.y"

SRCREV = "52c36105f76e96b638152a42e735f2e7767ed946"
SRC_URI = " \
    git://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git;protocol=https;branch=${KBRANCH} \
    file://0001-net-phy-microchip_t1-make-lan887x-config_init-self-s.patch \
    file://0002-net-phy-microchip_t1-restore-lan887x-interrupt-mask-.patch \
    file://0003-net-phy-microchip_t1-poll-lan887x-statistics-periodi.patch \
    file://0004-net-phy-microchip_t1-recover-lan887x-from-link-loss.patch \
    file://0005-net-phy-microchip_t1-report-lan887x-master-slave-rol.patch \
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