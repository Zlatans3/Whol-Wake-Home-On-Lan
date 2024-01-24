package wake.on.whol_wakehomeonlan.data.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {

//    @Query("SELECT * FROM device_table ORDER BY id ASC")
//    suspend fun getAllDevices(): Flow<List<Device>>

//    @Query("SELECT * FROM device_table WHERE id = :id")
//    suspend fun getDeviceById(id: Int): Device

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDevice(device: Device)

//    @Delete
//    suspend fun deleteDevice(device: Device)
}