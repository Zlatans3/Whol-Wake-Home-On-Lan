package wake.on.whol_wakehomeonlan.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Device::class], version = 1, exportSchema = false)
abstract class DeviceDatabase : RoomDatabase() {

        abstract fun deviceDao(): DeviceDao

        companion object {
                @Volatile
                private var INSTANCE: DeviceDatabase? = null

                fun getDatabase(context: Context): DeviceDatabase {
                        return INSTANCE ?: synchronized(this) {
                                val instance = databaseBuilder(
                                        context.applicationContext,
                                        DeviceDatabase::class.java,
                                        "app_database"
                                ).build()
                                INSTANCE = instance
                                instance
                        }
                }
        }
}