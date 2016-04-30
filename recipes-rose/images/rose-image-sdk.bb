require rose-image-minimal.bb

DESCRIPTION = "Creates a full developer SDK with all needed sources"

IMAGE_FEATURES += "dev-pkgs \
                   tools-sdk \
                   tools-debug \
                   tools-profile \
                   debug-tweaks  \
                   "

IMAGE_INSTALL += "kernel-devsrc \
                 "

IMAGE_LINGUAS = "de-de en-us en-gb"

TOOLCHAIN_TARGET_TASK_append = " kernel-devsrc"
