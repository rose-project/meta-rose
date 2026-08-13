# oe-core master still pins kern-tools at SRCREV a4a362d9, whose bundled
# kconfiglib predates the kernel's "depends on X if Y" conditional-dependency
# syntax (Linux v7.0+, kernel commit 76df6815dab7). The mainline kernel
# selected by this BSP uses it (e.g. drivers/hv/Kconfig: MSHV_ROOT), so
# do_kernel_configcheck aborts while parsing the tree.
#
# Bump to the upstream yocto-kernel-tools master tip, which carries the fix:
#   b5f0085 Kconfiglib: support conditional 'depends on X if Y' dependencies
# (along with symbol_why parse-failure surfacing and a Kconfiglib selftest).
# LIC_FILES_CHKSUM is unaffected (the checksummed regions are unchanged).
# Drop this bbappend once oe-core uprevs kern-tools past that commit.
SRCREV = "96a8d58bcd260f104c193a81b727818424d7b263"
