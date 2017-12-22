SUMMARY = "Jansson is a C library for encoding, decoding and manipulating JSON data"
HOMEPAGE = "http://www.digip.org/jansson/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8b70213ec164c7bd876ec2120ba52f61"

SRC_URI = "http://www.digip.org/jansson/releases/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "8f85f653fba3ac8220ad8f2f47646ef1"
SRC_URI[sha256sum] = "241125a55f739cd713808c4e0089986b8c3da746da8b384952912ad659fa2f5a"

inherit autotools pkgconfig

BBCLASSEXTEND = "native"
