SUMMARY = "Creates console only recovery/primary images"
LICENSE = "MIT"

DESCRIPTION = "Minimal bootable image with network support and update mechanism. \
               Recommend for tiny embedded systems without graphics"

IMAGE_INSTALL = "packagegroup-core-boot ${ROOTFS_PKGMANAGE_BOOTSTRAP} ${CORE_IMAGE_EXTRA_INSTALL}"

IMAGE_INSTALL += " kernel-modules \
                   pciutils \
                   usbutils \
                   "

#read-only-rootfs
IMAGE_FEATURES += "ssh-server-openssh"

IMAGE_LINGUAS = " "

EXTRA_IMAGEDEPENDS += "e2fsprogs-native"

IMAGE_FSTYPES += "hdddirect"

inherit core-image

