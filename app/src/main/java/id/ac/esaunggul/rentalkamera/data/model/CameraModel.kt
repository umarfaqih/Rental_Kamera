package id.ac.esaunggul.rentalkamera.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "camera")
data class CameraModel(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    var manufacturer: String,
    var type: String,
    var model: String,
    var price: Long,
    @ColumnInfo(name = "image_url") var imageUrl: String
)