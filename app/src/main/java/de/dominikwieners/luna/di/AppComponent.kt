package de.dominikwieners.luna.di

import dagger.Component
import de.dominikwieners.luna.view.UnsplashActivity
import de.dominikwieners.luna.view.UnsplashDetailActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(unsplashActivity: UnsplashActivity)
    fun inject(unsplashDetailActivity: UnsplashDetailActivity)
}