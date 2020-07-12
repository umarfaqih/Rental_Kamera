package id.ac.esaunggul.rentalkamera.ui.main.user.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProfileBinding.bind(view)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

        binding.lifecycleOwner = viewLifecycleOwner
    }
}