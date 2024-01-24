package wake.on.whol_wakehomeonlan.presentation.sendwol.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import wake.on.whol_wakehomeonlan.data.model.DeviceDao
import wake.on.whol_wakehomeonlan.data.model.DeviceDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDeviceDao(i: DeviceDatabase): DeviceDao {
        return i.deviceDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext i: Context): DeviceDatabase {
        return DeviceDatabase.getDatabase(i)
    }
}