DESCRIPTION = "Tool to read and write MII registers of Ethernet Phys"
SECTION = "devtools"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e8c1458438ead3c34974bc0be3a03ed6"

SRC_URI = "git://github.com/PieVo/mdio-tool.git;protocol=https;branch=master \
           file://0001-Fix-CMake-minimum-version.patch \
           "

PV = "1.0+git"
SRCREV = "4facab64880adecbb7874c6a48b307cec04d08e3"

inherit cmake
