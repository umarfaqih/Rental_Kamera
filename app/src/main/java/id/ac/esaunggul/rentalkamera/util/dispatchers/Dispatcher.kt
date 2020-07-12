package id.ac.esaunggul.rentalkamera.util.dispatchers

import androidx.lifecycle.LiveData
import id.ac.esaunggul.rentalkamera.util.events.OneTimeEvent

interface Dispatcher<T> {

    val event: LiveData<OneTimeEvent<T>>

    fun emit(event: T)
}