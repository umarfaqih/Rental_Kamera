package id.ac.esaunggul.rentalkamera.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.ac.esaunggul.rentalkamera.R
import id.ac.esaunggul.rentalkamera.databinding.ActivityCommonBinding
import id.ac.esaunggul.rentalkamera.util.dispatchers.Dispatcher
import id.ac.esaunggul.rentalkamera.util.extensions.applyInsets
import javax.inject.Inject

@AndroidEntryPoint
class CommonActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationDispatcher: Dispatcher<(NavController) -> Unit>

    @Inject
    lateinit var sharedPrefsDispatcher: Dispatcher<(SharedPreferences) -> Unit>

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RentalKamera)

        /*
         * Dynamically set system ui property for edge-to-edge experience in supported platform.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            // TODO: Recheck if appcompat has the above method backported.
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        }

        super.onCreate(savedInstanceState)

        val binding = ActivityCommonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        applyInsets(binding.commonParentLayout)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.common_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.commonNavView.setupWithNavController(navController)

        /*
         * Dispatcher for navigation in viewmodel.
         */
        navigationDispatcher.event.observe(this, Observer { event ->
            event.consume { command ->
                command(navController)
            }
        })

        /*
         * Dispatcher for shared preferences in viewmodel.
         */
        sharedPrefsDispatcher.event.observe(this, Observer { event ->
            event.consume { command ->
                command(getPreferences(Context.MODE_PRIVATE))
            }
        })
    }

    @Inject
    fun foo()
    {

    }
}