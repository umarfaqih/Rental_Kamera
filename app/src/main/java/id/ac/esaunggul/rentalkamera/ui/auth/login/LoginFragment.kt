package id.ac.esaunggul.rentalkamera.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentLoginBinding.bind(view)
        val loginViewModel: LoginViewModel by viewModels()

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = loginViewModel
    }
}