package id.ac.esaunggul.rentalkamera.util.states

sealed class ResourceState<out R> {
    data class Success<out T>(val data: T) : ResourceState<T>()
    object Loading : ResourceState<Nothing>()
    data class Error(val message: String?) : ResourceState<Nothing>()
    data class NotAuthorized(val message: String?) : ResourceState<Nothing>()
}