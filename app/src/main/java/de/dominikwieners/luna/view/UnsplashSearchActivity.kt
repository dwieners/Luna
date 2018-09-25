package de.dominikwieners.luna.view

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityUnsplashSearchBinding
import de.dominikwieners.luna.di.LunaApplication

class UnsplashSearchActivity : AppCompatActivity() {


    lateinit var binding:ActivityUnsplashSearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LunaApplication).getComponent().inject(this)
        initBinding()
        initToolbar()
        initSearchBar()

    }

    fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unsplash_search)
    }

    fun initToolbar(){
        binding.activityUnsplashDetailToolbar.title = " "
    }

    fun initSearchBar(){
        val slideInAnimation  = AnimationUtils.loadAnimation(this, R.anim.slide_in_to_left)
        binding.unsplashSearchSearchview.startAnimation(slideInAnimation)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
