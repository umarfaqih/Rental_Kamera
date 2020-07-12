package id.ac.esaunggul.rentalkamera.data.model

import androidx.room.DatabaseView
import java.util.Date

@DatabaseView(
    value = "SELECT rent.id, rent.return_date AS returnDate, rent.returned, " +
            "camera.manufacturer, camera.type, camera.model, camera.price, " +
            "camera.image_url AS imageUrl, " +
            "user.phone_number AS phoneNumber, user.address, user.email, user.name FROM rent " +
            "INNER JOIN camera ON rent.camera_id = camera.id " +
            "INNER JOIN user ON rent.user_id = user.id",
    viewName = "rent_detail_view"
)
data class RentDetailModel(
    val id: Long,
    val returnDate: Date,
    val returned: Boolean,
    val manufacturer: String,
    val type: String,
    val model: String,
    val price: Long,
    val imageUrl: String,
    val phoneNumber: Long,
    val address: String,
    val email: String,
    val name: String
)