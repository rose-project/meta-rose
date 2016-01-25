SUMMARY = "Creates console only recovery/primary images"
LICENSE = "MIT"

DESCRIPTION = "Minimal bootable image with network support and update mechanism.
               Recommend for tiny embedded systems without graphics"

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

#read-only-rootfs
IMAGE_FEATURES += "ssh-server-openssh"
IMAGE_LINGUAS = " "

inherit core-image

IMAGE_ROOTFS_SIZE ?= "8192"
