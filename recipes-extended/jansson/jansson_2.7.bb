SUMMARY = "Jansson is a C library for encoding, decoding and manipulating JSON data"
HOMEPAGE = "http://www.digip.org/jansson/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=517b9b5519c82636e516e4969c5ce393"

SRC_URI = "http://www.digip.org/jansson/releases/${BPN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "ffac352f9c5f80a6ae8145d451af2c0e"
SRC_URI[sha256sum] = "459f2b7cf22fb676286723f26169a17cf111fbfb6f54e3dc2ec6b6f9f4a97bdc"

inherit autotools pkgconfig
