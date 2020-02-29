SUMMARY = "Kodi Media Center systemd service"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit kodi-common systemd

PREFFERED_KODI_SERVICE ?= "kodi-${@bb.utils.contains('KODI_GRAPHICAL_BACKEND', 'x11', 'x11', 'gbm', d)}.service"

SRC_URI = "file://${PREFFERED_KODI_SERVICE}"

SYSTEMD_SERVICE_${PN} = "${PREFFERED_KODI_SERVICE}"
SYSTEMD_AUTO_ENABLE_${PN} = "${KODI_SYSTEMD_AUTOSTART}"

FILES_${PN} = "${systemd_unitdir}/system"

do_install() {
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/${PREFFERED_KODI_SERVICE} ${D}${systemd_unitdir}/system/
}
