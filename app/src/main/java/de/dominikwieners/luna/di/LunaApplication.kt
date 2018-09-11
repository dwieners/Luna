package de.dominikwieners.luna.di

import android.app.Application

class LunaApplication : Application() {

    private lateinit var component:AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
                .builder()
                .build()
    }

    fun getComponent():AppComponent{
        return component
    }

}