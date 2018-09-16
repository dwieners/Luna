package de.dominikwieners.luna

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.view.View
import android.widget.ImageView
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.view.GifActivity
import de.dominikwieners.luna.view.UnsplashActivity
import de.dominikwieners.luna.view.UnsplashDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun showGifActivity(activity: Activity){
        val intent = Intent(activity, GifActivity::class.java )
        activity.startActivity(intent)
        activity.finish()
    }

    fun showUnsplashDetailActivity(activity: Activity, unsplashPictureResponse: UnsplashPictureResponse){
        val intent = Intent(activity, UnsplashDetailActivity::class.java)
        intent.putExtra(Config.UNSPLASH_DEAIL_EXTRA, unsplashPictureResponse)
        activity.startActivity(intent)
        activity.finish()
    }

    fun showUnsplashDetailActivityImageTransition(activity: Activity, unsplashPictureResponse: UnsplashPictureResponse, view:ImageView){
        val intent = Intent(activity, UnsplashDetailActivity::class.java)
        val options:ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, "unsplashPicture")
        intent.putExtra(Config.UNSPLASH_DEAIL_EXTRA, unsplashPictureResponse)
        activity.startActivity(intent, options.toBundle())
        activity.finish()

    }


    fun showUnsplashActivity(activity: Activity){
        val intent = Intent(activity, UnsplashActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}