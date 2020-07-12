package id.ac.esaunggul.rentalkamera.data.repo

import id.ac.esaunggul.rentalkamera.data.dao.RentalKameraDao
import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.data.model.UserModel
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RentalKameraLocalRepo
@Inject
constructor(
    private val rentalKameraDao: RentalKameraDao
) : RentalKameraRepo {

    override suspend fun login(email: String, password: String): Flow<ResourceState<UserModel>> =
        flow {
            emit(ResourceState.Loading)

            emit(ResourceState.Success(rentalKameraDao.login(email, password)))
        }.catch { error ->
            emit(ResourceState.NotAuthorized(error.message))
        }.flowOn(Dispatchers.IO)

    override suspend fun register(userModel: UserModel): Flow<ResourceState<Nothing?>> = flow {
        emit(ResourceState.Loading)

        rentalKameraDao.register(userModel)

        emit(ResourceState.Success(null))
    }.catch { error ->
        emit(ResourceState.Error(error.message))
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllCamera(): Flow<ResourceState<List<CameraModel>>> = flow {
        emit(ResourceState.Loading)

        emit(ResourceState.Success(rentalKameraDao.getAllCamera()))
    }.catch { error ->
        ResourceState.Error(error.message)
    }.flowOn(Dispatchers.IO)
}