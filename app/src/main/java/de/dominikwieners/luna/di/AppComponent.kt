package de.dominikwieners.luna.di

import dagger.Component
import de.dominikwieners.luna.view.GiphyActivity
import de.dominikwieners.luna.view.UnsplashActivity
import de.dominikwieners.luna.view.UnsplashDetailActivity
import de.dominikwieners.luna.view.UnsplashSearchActivity
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(unsplashActivity: UnsplashActivity)
    fun inject(unsplashDetailActivity: UnsplashDetailActivity)
    fun inject(unsplashSearchActivity: UnsplashSearchActivity)

    fun inject(giphyActivity: GiphyActivity)

}