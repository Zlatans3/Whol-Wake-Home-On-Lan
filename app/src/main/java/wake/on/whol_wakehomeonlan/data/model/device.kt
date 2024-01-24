package wake.on.whol_wakehomeonlan.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "device_table")
data class Device(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val ipAddress: String,
    val macAddress: String,
    val portNumber: String
)
