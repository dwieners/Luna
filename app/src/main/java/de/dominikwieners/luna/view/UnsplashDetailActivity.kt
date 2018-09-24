package de.dominikwieners.luna.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.squareup.picasso.Picasso
import de.dominikwieners.luna.Config
import de.dominikwieners.luna.Navigator
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityUnsplashDetailBinding
import de.dominikwieners.luna.di.LunaApplication
import de.dominikwieners.luna.model.UnsplashPictureResponse
import javax.inject.Inject
import android.util.DisplayMetrics
import de.dominikwieners.luna.viewmodel.UnsplashDetailViewModel
import java.sql.Timestamp
import java.text.SimpleDateFormat


class UnsplashDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    lateinit var binding: ActivityUnsplashDetailBinding
    lateinit var unsplashPictureResponse:UnsplashPictureResponse
    lateinit var unsplashDetailViewModel:UnsplashDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LunaApplication).getComponent().inject(this)

        unsplashPictureResponse = intent.extras.getParcelable(Config.UNSPLASH_DEAIL_EXTRA)

        initViewModel(unsplashPictureResponse)
        initBinding(unsplashDetailViewModel)
        initToolbar()
        setImages()

    }

    private fun initViewModel(unsplashPictureResponse: UnsplashPictureResponse){
        unsplashDetailViewModel = ViewModelProviders.of(this).get(UnsplashDetailViewModel::class.java)
        unsplashDetailViewModel.init(unsplashPictureResponse)
    }

    private fun initBinding(unsplashDetailViewModel: UnsplashDetailViewModel){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unsplash_detail)
        binding.viewmodel = unsplashDetailViewModel
    }

    private fun initToolbar(){
        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setImages(){
       Picasso.get().load(binding.viewmodel!!.detail.value!!.urls?.small).into(binding.unsplashDetailIvPhoto)
       Picasso.get().load(binding.viewmodel!!.detail.value!!.user?.profile_image?.small).into(binding.unsplashDetailIvUser)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
