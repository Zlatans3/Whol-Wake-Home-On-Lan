package wake.on.whol_wakehomeonlan.presentation.sendwol

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import wake.on.whol_wakehomeonlan.presentation.main.MainActivityActions
import wake.on.whol_wakehomeonlan.data.model.Device
import wake.on.whol_wakehomeonlan.data.model.DeviceDao
import wake.on.whol_wakehomeonlan.data.model.DeviceState
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import javax.inject.Inject


data class SendWHoLUiState(
    val IsIpValid: Boolean? = null,
    val IsMacValid: Boolean? = null,
    val IsPortValid: Boolean? = null,
    val deviceSaved: Boolean = false,
)


const val argumentName = "name"
const val argumentEmail = "email"
const val argumentPassword = "password"

@HiltViewModel
class SendWoLViewModel @Inject constructor(
    private val deviceDao: DeviceDao,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val ipAddressTextFieldQuery = savedStateHandle.getStateFlow(argumentName, "192.168.1.22")
    val macAddressTextFieldQuery = savedStateHandle.getStateFlow(argumentEmail, "1C:1B:0D:95:DE:C0")
    val portTextFieldQuery = savedStateHandle.getStateFlow(argumentPassword, "9")

    private val _uiState = MutableStateFlow<SendWHoLUiState>(SendWHoLUiState())
    val uiState: StateFlow<SendWHoLUiState> = _uiState

    private val _deviceState = MutableStateFlow(DeviceState())

    private val saveDevice = Device(
        ipAddress = ipAddressTextFieldQuery.value,
        macAddress = macAddressTextFieldQuery.value,
        portNumber = portTextFieldQuery.value
    )

    /**
     * ip address validator
     *
     * Regex looking for following pattern
     * [01]?\\d\\d?|2[0-4]\\d|25[0-5] - 0-199
     * . - dot
     * \\ - escape character
     */
    fun validateIpAddress(ipStr: String): Boolean {
        val octet = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])"
        val ipAddressRegex = "^$octet\\.$octet\\.$octet\\.$octet$".toRegex()
        return ipAddressRegex.matches(ipStr)
    }

    fun validateMacAddress(macAddress: String): Boolean {
        val macAddressRegex = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$".toRegex()
        return macAddressRegex.matches(macAddress)
    }

    /**
     * ip [192.168.1.22]
     * mac [1C:1B:0D:95:DE:C0]
     * port [9]
     */

    private fun sendWakeOnLanCommand(
        macStr: String,
        ipStr: String = "255.255.255.255",
        portStr: String = "9",
    ) {
        val macBytes = getMacBytes(macStr)
        val address = InetAddress.getByName(ipStr)
        val port = portStr.toIntOrNull() ?: 9

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val packet = DatagramPacket(ByteArray(102).apply {
                    // Create Magic Packet
                    for (i in 0..5) {
                        this[i] = 0xff.toByte()
                    }
                    for (i in 6 until 102 step 6) {
                        System.arraycopy(macBytes, 0, this, i, macBytes.size)
                    }
                }, 102, address, port)

                DatagramSocket().use { socket ->
                    Log.d("MainActivityViewModel", "Sending packet $packet")
                    socket.send(packet)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getMacBytes(macStr: String): ByteArray {
        val bytes = ByteArray(6)
        val hex = macStr.split(":", "-")
        if (hex.size != 6) {
            throw IllegalArgumentException("Invalid MAC address.")
        }
        try {
            for (i in 0..5) {
                bytes[i] = Integer.parseInt(hex[i], 16).toByte()
            }
        } catch (e: NumberFormatException) {
            throw IllegalArgumentException("Invalid hex digit in MAC address.")
        }
        return bytes
    }


    fun onAction(action: MainActivityActions) {
        when (action) {
            is MainActivityActions.OnIpTextFieldChanged -> {
                savedStateHandle[argumentName] = action.ipAddress

                val Validate = validateIpAddress(ipAddressTextFieldQuery.value)

                // this way we only update the state when the validation is positive
                if (Validate) {
                    _uiState.update {
                        it.copy(IsIpValid = true)
                    }
                } else
                // validation reset
                    _uiState.update {
                        it.copy(IsIpValid = null)
                    }
            }

            is MainActivityActions.OnMacAddressTextFieldChanged -> {
                savedStateHandle[argumentEmail] = action.macAddress

                val Validate = validateMacAddress(macAddressTextFieldQuery.value)
                // this way we only update the state when the validation is positive
                if (Validate) {
                    _uiState.update {
                        it.copy(IsMacValid = true)
                    }
                } else
                // validation reset
                    _uiState.update {
                        it.copy(IsMacValid = null)
                    }
            }

            is MainActivityActions.OnPortTextFieldChanged -> {
                savedStateHandle[argumentPassword] = action.portNumber
            }

            is MainActivityActions.OnWakeOnLanClicked -> {
                Log.d("MainActivityViewModel", "OnWakeOnLanClicked")

                val isMacValid = validateMacAddress(macAddressTextFieldQuery.value)
                if (!isMacValid) {
                    Log.d("MainActivityViewModel", "Mac address is not valid")
                    _uiState.update {
                        it.copy(IsMacValid = false)
                    }
                } else {
                    sendWakeOnLanCommand(
                        macStr = macAddressTextFieldQuery.value,
                        ipStr = ipAddressTextFieldQuery.value,
                        portStr = portTextFieldQuery.value,
                    )
                }
            }

            MainActivityActions.OnDeviceSaved -> {
//                viewModelScope.launch {
//                    deviceDao.insertDevice(saveDevice)
//                }

                _uiState.update {
                    it.copy(deviceSaved = true)
                }
            }
        }
    }
}
