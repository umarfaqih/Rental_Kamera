package id.ac.esaunggul.rentalkamera.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.ac.esaunggul.rentalkamera.data.dao.RentalKameraDao
import id.ac.esaunggul.rentalkamera.data.database.RentalKameraDatabase
import id.ac.esaunggul.rentalkamera.data.repo.RentalKameraLocalRepo
import id.ac.esaunggul.rentalkamera.data.repo.RentalKameraRepo
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideLocalDatabase(@ApplicationContext context: Context): RentalKameraDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                RentalKameraDatabase::class.java,
                "RentalKamera.db"
            ).createFromAsset("database/RentalKamera.db").build()
        }

        @Singleton
        @Provides
        fun provideDao(rentalKameraDatabase: RentalKameraDatabase): RentalKameraDao {
            return rentalKameraDatabase.rentalCameraDao()
        }
    }

    @Singleton
    @Binds
    abstract fun bindRentalKameraLocalRepo(rentalKameraLocalRepo: RentalKameraLocalRepo): RentalKameraRepo
}