require rose-image-gfx.bb

DESCRIPTION = "Image enhance graphical image with video/audio codecs."

IMAGE_INSTALL += " \
    gstreamer1.0-meta-base \
    gstreamer1.0-meta-video \
    gstreamer1.0-libav \
    gstreamer1.0-plugins-bad-fbdevsink \
    gstreamer1.0-plugins-bad-mpegpsdemux \
    "

