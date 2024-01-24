package wake.on.whol_wakehomeonlan.model

import java.net.Inet4Address

data class DeviceState(
    val ipAddress: String = "",
    val macAddress: String = "",
    val portNumber: String = "",
    val isSavingDevice: Boolean = false,
)
