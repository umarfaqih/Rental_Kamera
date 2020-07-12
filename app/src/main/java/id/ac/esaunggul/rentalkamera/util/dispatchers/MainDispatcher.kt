package id.ac.esaunggul.rentalkamera.util.dispatchers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.ac.esaunggul.rentalkamera.util.events.OneTimeEvent
import javax.inject.Inject

class MainDispatcher<T>
@Inject constructor() : Dispatcher<T> {

    private val _event: MutableLiveData<OneTimeEvent<T>> = MutableLiveData()
    override val event: LiveData<OneTimeEvent<T>>
        get() = _event

    override fun emit(event: T) {
        _event.value = OneTimeEvent(event)
    }
}