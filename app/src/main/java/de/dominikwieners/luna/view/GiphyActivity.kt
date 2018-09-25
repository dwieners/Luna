package de.dominikwieners.luna.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.dominikwieners.luna.R
import de.dominikwieners.luna.di.LunaApplication

class GiphyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giphy)
        (application as LunaApplication).getComponent().inject(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
