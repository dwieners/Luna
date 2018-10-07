package de.dominikwieners.luna.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.dominikwieners.luna.Navigator
import de.dominikwieners.luna.R
import de.dominikwieners.luna.di.LunaApplication
import de.dominikwieners.smarttoast.SmartToast
import javax.inject.Inject

class GiphyActivity : AppCompatActivity() {

    @Inject lateinit var navigator:Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giphy)
        (application as LunaApplication).getComponent().inject(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.showUnsplashActivity(this)
    }
}
