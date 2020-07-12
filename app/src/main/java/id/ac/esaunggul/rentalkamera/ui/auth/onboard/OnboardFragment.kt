package id.ac.esaunggul.rentalkamera.ui.auth.onboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.FragmentOnboardBinding

@AndroidEntryPoint
class OnboardFragment : Fragment(R.layout.fragment_onboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentOnboardBinding.bind(view)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.direction = OnboardFragmentDirections
    }
}