DESCRIPTION = "Tool to read and write MII registers of Ethernet Phys"
SECTION = "devtools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8c1458438ead3c34974bc0be3a03ed6"

SRC_URI = "git://github.com/PieVo/mdio-tool.git;protocol=https;branch=master"
SRCREV = "72bd5a915ff046a59ce4303c8de672e77622a86c"

S = "${WORKDIR}/git"

inherit cmake
