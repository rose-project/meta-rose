# AGENTS.md

ROSE distro layer. Yocto wrynose (`LAYERSERIES_COMPAT_rose`). Distro
`rose`, image `rose-image`, shared kernel `linux-mainline` 7.2.y.

Workspace rules: `$ROSE_ROOT/AGENTS.md` (manifest `rose/west.yml`).

- Image: `recipes-core/images/rose-image.bb` — `core-image-base` plus
  mesa, vulkan, kmscube, openssh, iproute2, mdio-tool, …
- Kernel lives **here**, not in the BSP: version, lan887x patches,
  industrial/USB ethernet fragments. BSPs only bbappend the machine
  defconfig / `KBUILD_DEFCONFIG`. Recipe `COMPATIBLE_MACHINE` is `^$`
  until a BSP opens it.
- `INIT_MANAGER` is sysvinit. Graphics are KMS/DRM + Wayland (no X11,
  no PulseAudio).
- `ssh-hostkeys-rose` is opt-in via `EXTRA_IMAGE_FEATURES +=
  "ssh-fixed-hostkeys"`. Lab only; never production.
- New layers belong in `conf/templates/default/bblayers.conf.sample`,
  not only in a live `build/conf/bblayers.conf`.
- Do not add `meta-yocto`. Do not retarget OE pins to `yocto-6.0.2`
  tags from this layer.
