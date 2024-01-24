package wake.on.whol_wakehomeonlan.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeviceDao {

//    @Query("SELECT * FROM device_table ORDER BY id ASC")
//    suspend fun getAllDevices(): Flow<List<Device>>

//    @Query("SELECT * FROM device_table WHERE id = :id")
//    suspend fun getDeviceById(id: Int): Device

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDevice(device: Device): Long

    @Query("SELECT * FROM device_table WHERE ipAddress = :ipAddress  AND macAddress = :macAddress AND portNumber = :portNumber")
    suspend fun getDevicesByFields(
        ipAddress: String,
        macAddress: String,
        portNumber: String
    ): List<Device>

    @Delete
    suspend fun deleteDevice(device: Device)
}