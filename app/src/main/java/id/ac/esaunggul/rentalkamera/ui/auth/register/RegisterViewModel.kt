package id.ac.esaunggul.rentalkamera.ui.auth.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.data.model.UserModel
import id.ac.esaunggul.rentalkamera.data.repo.RentalKameraRepo
import id.ac.esaunggul.rentalkamera.util.dispatchers.Dispatcher
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import id.ac.esaunggul.rentalkamera.util.validators.FormValidator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class RegisterViewModel
@ViewModelInject
constructor(
    private val repo: RentalKameraRepo,
    private val navigationDispatcher: Dispatcher<(NavController) -> Unit>
) : ViewModel() {

    val phoneField = MutableLiveData<String?>()
    val nameField = MutableLiveData<String?>()
    val addressField = MutableLiveData<String?>()
    val emailField = MutableLiveData<String?>()
    val passwordField = MutableLiveData<String?>()
    val passwordConfirmField = MutableLiveData<String?>()

    private val _phoneError: MutableLiveData<Int?> = MutableLiveData()
    private val _nameError: MutableLiveData<Int?> = MutableLiveData()
    private val _addressError: MutableLiveData<Int?> = MutableLiveData()
    private val _emailError: MutableLiveData<Int?> = MutableLiveData()
    private val _passwordError: MutableLiveData<Int?> = MutableLiveData()
    private val _passwordConfirmError: MutableLiveData<Int?> = MutableLiveData()

    val phoneError: LiveData<Int?> = _phoneError
    val nameError: LiveData<Int?> = _nameError
    val addressError: LiveData<Int?> = _addressError
    val emailError: LiveData<Int?> = _emailError
    val passwordError: LiveData<Int?> = _passwordError
    val passwordConfirmError: LiveData<Int?> = _passwordError

    fun register() {
        if (formValidation()) {
            viewModelScope.launch {
                // TODO: hash the password.
                repo.register(
                    UserModel(
                        isAdmin = false,
                        phoneNumber = phoneField.value?.toLong()!!,
                        name = nameField.value!!,
                        address = addressField.value!!,
                        email = emailField.value!!,
                        password = passwordField.value!!
                    )
                ).collect { state ->
                    when (state) {
                        is ResourceState.Loading -> Timber.d("Loading...")
                        is ResourceState.Success -> navigationDispatcher.emit { navController ->
                            navController.navigate(RegisterFragmentDirections.actionRegistered())
                        }
                        is ResourceState.NotAuthorized -> Timber.d("Register failed.")
                        else -> Timber.d("This shouldn't ever happened.")
                    }
                }
            }
        }
    }

    private fun formValidation(): Boolean {
        resetError()
        var state = true

        if (phoneField.value.isNullOrEmpty()) {
            _phoneError.value = R.string.form_empty
            state = false
        }

        if (nameField.value.isNullOrEmpty()) {
            _nameError.value = R.string.form_empty
            state = false
        } else if (FormValidator.isNameNotValid(nameField.value)) {
            _nameError.value = R.string.name_invalid
            state = false
        }

        if (emailField.value.isNullOrEmpty()) {
            _emailError.value = R.string.form_empty
            state = false
        } else if (FormValidator.isEmailNotValid(emailField.value)) {
            _emailError.value = R.string.email_invalid
            state = false
        }

        if (addressField.value.isNullOrEmpty()) {
            _addressError.value = R.string.form_empty
            state = false
        }

        if (passwordField.value.isNullOrEmpty()) {
            _passwordError.value = R.string.form_empty
            state = false
        } else if (FormValidator.isPasswordWeak(passwordField.value)) {
            _passwordError.value = R.string.password_weak
            state = false
        }

        if (passwordField.value != passwordConfirmField.value) {
            _passwordError.value = R.string.password_not_matched
            _passwordConfirmError.value = R.string.password_not_matched
            state = false
        }

        return state
    }

    private fun resetError() {
        _phoneError.value = null
        _nameError.value = null
        _addressError.value = null
        _emailError.value = null
        _passwordError.value = null
        _passwordConfirmError.value = null
    }
}