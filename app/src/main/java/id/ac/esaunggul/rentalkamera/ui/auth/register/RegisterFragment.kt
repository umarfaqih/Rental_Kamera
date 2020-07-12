package id.ac.esaunggul.rentalkamera.ui.auth.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.FragmentRegisterBinding

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRegisterBinding.bind(view)
        val registerViewModel: RegisterViewModel by viewModels()

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.registerViewModel = registerViewModel
    }
}