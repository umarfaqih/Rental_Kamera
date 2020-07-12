package id.ac.esaunggul.rentalkamera.di

import android.content.SharedPreferences
import androidx.navigation.NavController
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.ac.esaunggul.rentalkamera.util.dispatchers.Dispatcher
import id.ac.esaunggul.rentalkamera.util.dispatchers.MainDispatcher

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class ViewModelModule {

    @ActivityRetainedScoped
    @Binds
    abstract fun bindNavigationDispatcher(mainDispatcher: MainDispatcher<(NavController) -> Unit>): Dispatcher<(NavController) -> Unit>

    @ActivityRetainedScoped
    @Binds
    abstract fun bindSharedPrefsDispatcher(mainDispatcher: MainDispatcher<((SharedPreferences) -> Unit)>): Dispatcher<(SharedPreferences) -> Unit>
}