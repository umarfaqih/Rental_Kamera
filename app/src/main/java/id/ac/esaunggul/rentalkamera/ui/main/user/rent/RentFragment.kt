package id.ac.esaunggul.rentalkamera.ui.main.user.rent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.transition.MaterialFadeThrough
import dagger.hilt.android.AndroidEntryPoint
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.FragmentRentBinding
import id.ac.esaunggul.rentalkamera.ui.auth.register.RegisterViewModel
import id.ac.esaunggul.rentalkamera.util.states.ResourceState
import timber.log.Timber

@AndroidEntryPoint
class RentFragment : Fragment(R.layout.fragment_rent) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRentBinding.bind(view)
        val rentViewModel: RentViewModel by viewModels()
        val adapter = RentCardAdapter(rentViewModel)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()

        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter

        rentViewModel.cameras.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is ResourceState.Loading -> Timber.d("Loading...")
                is ResourceState.Success -> {
                    adapter.submitList(state.data)
                }
                is ResourceState.Error -> Timber.e("Failed to get data: ${state.message}.")
                else -> Timber.e("This shouldn't ever happened.")
            }
        })
    }
}