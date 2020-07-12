package id.ac.esaunggul.rentalkamera.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.data.model.RentDetailModel
import id.ac.esaunggul.rentalkamera.data.model.UserModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RentalKameraDao {

    @Query("SELECT * FROM camera")
    suspend fun getAllCamera(): List<CameraModel>

    @Query("SELECT * FROM rent_detail_view")
    suspend fun getAllRent(): List<RentDetailModel>

    @Query("SELECT * FROM camera WHERE id = :id")
    suspend fun getCameraById(id: Long): CameraModel

    @Query("SELECT * FROM user WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): UserModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(userModel: UserModel)
}