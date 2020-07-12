package id.ac.esaunggul.rentalkamera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "rent",
    foreignKeys = [ForeignKey(
        entity = CameraModel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("camera_id"),
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = UserModel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["camera_id"]), Index(value = ["user_id"])]
)
data class RentModel(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "camera_id") var cameraId: Long,
    @ColumnInfo(name = "user_id") var userId: Long,
    @ColumnInfo(name = "rent_price") var rentPrice: Long,
    @ColumnInfo(name = "return_date") var returnDate: Date,
    var returned: Boolean
)