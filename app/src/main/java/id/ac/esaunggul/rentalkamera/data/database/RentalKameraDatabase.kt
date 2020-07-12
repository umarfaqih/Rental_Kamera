package id.ac.esaunggul.rentalkamera.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.ac.esaunggul.rentalkamera.data.dao.RentalKameraDao
import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.data.model.RentDetailModel
import id.ac.esaunggul.rentalkamera.data.model.RentModel
import id.ac.esaunggul.rentalkamera.data.model.UserModel
import id.ac.esaunggul.rentalkamera.util.converters.RoomConverters

@Database(
    entities = [CameraModel::class, UserModel::class, RentModel::class],
    views = [RentDetailModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomConverters::class)
abstract class RentalKameraDatabase : RoomDatabase() {

    abstract fun rentalCameraDao(): RentalKameraDao
}