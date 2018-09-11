package de.dominikwieners.luna.di

import dagger.Component
import de.dominikwieners.luna.view.MainActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}