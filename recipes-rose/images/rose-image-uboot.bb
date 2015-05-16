SUMMARY = "Creates U-Boot raw images"

IMAGE_FEATURES += ""

inherit core-image

IMAGE_INSTALL += " \
    u-boot-mkimage \
"


