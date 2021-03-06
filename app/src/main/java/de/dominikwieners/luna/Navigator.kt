package de.dominikwieners.luna

import android.app.Activity
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.widget.ImageView
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.view.GiphyActivity
import de.dominikwieners.luna.view.UnsplashActivity
import de.dominikwieners.luna.view.UnsplashDetailActivity
import de.dominikwieners.luna.view.UnsplashSearchActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun showGifActivity(activity: Activity){
        val intent = Intent(activity, GiphyActivity::class.java )
        activity.startActivity(intent)
        activity.finish()
    }

    fun showUnsplashDetailActivity(activity: Activity, unsplashPictureResponse: UnsplashPictureResponse, destroy: Boolean){
        val intent = Intent(activity, UnsplashDetailActivity::class.java)
        intent.putExtra(Config.UNSPLASH_DEAIL_EXTRA, unsplashPictureResponse)
        activity.startActivity(intent)
        if(destroy) {
            activity.finish()
        }
    }

    fun showUnsplashSearchActivity(activity:Activity, destroy: Boolean){
        val intent = Intent(activity, UnsplashSearchActivity::class.java)
        activity.startActivity(intent)
        if(destroy) {
            activity.finish()
        }
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