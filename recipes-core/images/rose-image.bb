require recipes-core/images/core-image-base.bb

IMAGE_FEATURES += "allow-empty-password empty-root-password allow-root-login"

IMAGE_INSTALL:append = " \
    mesa \
    vulkan-loader \
    kmscube \
    iproute2 \
    ifupdown \
    ethtool \
    openssh \
    vim \
    python3 \
    iperf2 \
    iperf3 \
    mdio-tool \
"
