package id.ac.esaunggul.rentalkamera.data.repo

import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.data.model.UserModel
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import kotlinx.coroutines.flow.Flow

interface RentalKameraRepo {

    suspend fun login(email: String, password: String): Flow<ResourceState<UserModel>>

    suspend fun register(userModel: UserModel): Flow<ResourceState<Nothing?>>

    suspend fun getAllCamera(): Flow<ResourceState<List<CameraModel>>>
}