DESCRIPTION = "The ROSE Pacakge Service library"
SECTION = "rose"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/rose-project/rps.git"
SRCREV = "1b6c903226eacf796b56dc3d2c005104bee8ab0d"

DEPENDS = "boost libarchive jansson"

S = "${WORKDIR}/git"

inherit cmake

EXTRA_OEMAKE = ""
