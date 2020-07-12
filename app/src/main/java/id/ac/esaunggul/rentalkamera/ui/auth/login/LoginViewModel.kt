package id.ac.esaunggul.rentalkamera.ui.auth.login

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.data.repo.RentalKameraRepo
import id.ac.esaunggul.rentalkamera.util.dispatchers.Dispatcher
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import id.ac.esaunggul.rentalkamera.util.validators.FormValidator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel
@ViewModelInject
constructor(
    private val repo: RentalKameraRepo,
    private val navigationDispatcher: Dispatcher<(NavController) -> Unit>,
    private val sharedPrefsDispatcher: Dispatcher<(SharedPreferences) -> Unit>
) : ViewModel() {

    val emailField: MutableLiveData<String?> = MutableLiveData()
    val passwordField: MutableLiveData<String?> = MutableLiveData()

    private val _emailError: MutableLiveData<Int?> = MutableLiveData()
    private val _passwordError: MutableLiveData<Int?> = MutableLiveData()

    val emailError: LiveData<Int?> = _emailError
    val passwordError: LiveData<Int?> = _passwordError

    fun login() {
        if (formValidation()) {
            viewModelScope.launch {
                repo.login(
                    emailField.value!!,
                    passwordField.value!!
                ).collect { state ->
                    when (state) {
                        is ResourceState.Loading -> Timber.d("Loading...")
                        is ResourceState.Success -> {
                            if (state.data.isAdmin) {
                                state.data.id?.let {
                                    saveAuthKey(true, it)
                                    navigateToMain()
                                }
                            } else {
                                state.data.id?.let {
                                    saveAuthKey(false, it)
                                    navigateToMain()
                                }
                            }
                        }
                        is ResourceState.NotAuthorized -> Timber.d("Login failed: ${state.message}")
                        else -> Timber.e("This shouldn't ever happened.")
                    }
                }
            }
        }
    }

    private fun navigateToMain() {
        navigationDispatcher.emit { navController ->
            navController.navigate(LoginFragmentDirections.actionLoggedOn())
        }
    }

    private fun saveAuthKey(admin: Boolean, id: Long) {
        sharedPrefsDispatcher.emit { sharedPrefs ->
            with(sharedPrefs.edit()) {
                putBoolean("id.ac.esaunggul.rentalkamera.ADMIN_KEY", admin)
                putLong("id.ac.esaunggul.rentalkamera.ID_KEY", id)
                commit()
            }
        }
    }

    private fun formValidation(): Boolean {
        resetError()
        var state = true

        if (emailField.value.isNullOrEmpty()) {
            _emailError.value = R.string.form_empty
            state = false
        } else if (FormValidator.isEmailNotValid(emailField.value)) {
            _emailError.value = R.string.email_invalid
            state = false
        }

        if (passwordField.value.isNullOrEmpty()) {
            _passwordError.value = R.string.form_empty
            state = false
        } else if (FormValidator.isPasswordWeak(passwordField.value)) {
            _passwordError.value = R.string.password_weak
            state = false
        }

        return state
    }

    private fun resetError() {
        _emailError.value = null
        _passwordError.value = null
    }
}