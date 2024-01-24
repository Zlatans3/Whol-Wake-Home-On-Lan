package wake.on.whol_wakehomeonlan.presentation.main

sealed class MainActivityActions {
    data class OnIpTextFieldChanged(val ipAddress: String) : MainActivityActions()
    data class OnMacAddressTextFieldChanged(val macAddress: String) : MainActivityActions()
    data class OnPortTextFieldChanged(val portNumber: String) : MainActivityActions()
    data object OnWakeOnLanClicked : MainActivityActions()

    data object OnDeviceSaved : MainActivityActions()
}