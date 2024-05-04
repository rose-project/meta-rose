include recipes-core/images/core-image-base.bb

IMAGE_INSTALL:append = " \
    mesa \
    vulkan-headers \
    vulkan-loader \
    kmscube \
    iproute2 \
    ifupdown \
    openssh \
    vim \
    python3 \
    iperf2 \
"

