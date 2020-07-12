package id.ac.esaunggul.rentalkamera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "is_admin") var isAdmin: Boolean,
    @ColumnInfo(name = "phone_number") var phoneNumber: Long,
    var address: String,
    var email: String,
    var name: String,
    var password: String
)