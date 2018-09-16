package de.dominikwieners.luna.view

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



class UnsplashDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    lateinit var binding: ActivityUnsplashDetailBinding
    lateinit var unsplashPictureResponse:UnsplashPictureResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LunaApplication).getComponent().inject(this)

        unsplashPictureResponse = intent.extras.getParcelable(Config.UNSPLASH_DEAIL_EXTRA)

        initBinding(unsplashPictureResponse)
        initToolbar()
        setImages()

    }

    private fun initBinding(unsplashPictureResponse:UnsplashPictureResponse){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unsplash_detail)
        binding.detail = unsplashPictureResponse
    }

    private fun initToolbar(){
        binding.toolbar.title = getString(R.string.app_name)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setImages(){
        Picasso.get().load(unsplashPictureResponse.urls?.small).into(binding.unsplashDetailIvUnsplash)
        Picasso.get().load(unsplashPictureResponse.user?.profile_image?.small).into(binding.unsplashDetailIvUser)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.showUnsplashActivity(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> navigator.showUnsplashActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    fun convertPixelsToDp(px: Float, context: Context): Float {
        return px / (context.getResources().getDisplayMetrics().densityDpi as Float / DisplayMetrics.DENSITY_DEFAULT)
    }
}
