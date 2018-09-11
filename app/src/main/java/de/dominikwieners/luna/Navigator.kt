package de.dominikwieners.luna

import android.app.Activity
import android.content.Intent
import de.dominikwieners.luna.view.GifActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun showGifActivity(activity: Activity){
        val intent = Intent(activity, GifActivity::class.java )
        activity.startActivity(intent)
        activity.finish()
    }
}