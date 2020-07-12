package id.ac.esaunggul.rentalkamera.ui.main.user.rent

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import id.ac.esaunggul.rentalkamera.data.model.CameraModel
import id.ac.esaunggul.rentalkamera.data.repo.RentalKameraRepo
import id.ac.esaunggul.rentalkamera.util.dispatchers.Dispatcher
import id.ac.esaunggul.rentalkamera.util.listener.ClickListener
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class RentViewModel
@ViewModelInject
constructor(
    private val repo: RentalKameraRepo,
    private val navigationDispatcher: Dispatcher<(NavController) -> Unit>
) : ViewModel(), ClickListener {

    private val _cameras: MutableLiveData<ResourceState<List<CameraModel>>> = MutableLiveData()
    val cameras: LiveData<ResourceState<List<CameraModel>>> = _cameras

    init {
        getCamera()
    }

    private fun getCamera() {
        viewModelScope.launch {
            repo.getAllCamera().collect { state ->
                _cameras.value = state
            }
        }
    }

    override fun onClick(position: Int) {
        // Nothing to do
    }
}