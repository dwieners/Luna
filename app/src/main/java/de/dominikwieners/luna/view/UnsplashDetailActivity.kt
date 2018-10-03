package de.dominikwieners.luna.view

import android.app.WallpaperManager
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import android.widget.Toast
import com.squareup.picasso.Callback
import de.dominikwieners.luna.viewmodel.UnsplashDetailViewModel
import java.io.IOException
import java.lang.Exception
import java.sql.Timestamp
import java.text.SimpleDateFormat


class UnsplashDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    lateinit var binding: ActivityUnsplashDetailBinding
    lateinit var unsplashPictureResponse:UnsplashPictureResponse
    lateinit var unsplashDetailViewModel:UnsplashDetailViewModel

    var pictureDrawable = MutableLiveData<Drawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LunaApplication).getComponent().inject(this)

        unsplashPictureResponse = intent.extras.getParcelable(Config.UNSPLASH_DEAIL_EXTRA)

        initViewModel(unsplashPictureResponse)
        initBinding(unsplashDetailViewModel)
        initToolbar()
        setImages()
        setWallpaperImage()
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
        binding.unsplashDetailToolbar.title = " "
        setSupportActionBar(binding.unsplashDetailToolbar)
        supportActionBar?.let{
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setImages(){
       Picasso.get().load(binding.viewmodel!!.detail.value!!.urls?.regular).into(binding.unsplashDetailIvPhoto, object : Callback {
           override fun onSuccess() {
               pictureDrawable.value = binding.unsplashDetailIvPhoto.drawable
           }

           override fun onError(e: Exception?) {
               TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
           }
       })

        Picasso.get().load(binding.viewmodel!!.detail.value!!.user?.profile_image?.small).into(binding.unsplashDetailIvUser)
    }

    private fun setWallpaperImage(){
        binding.unsplashDetailBuWallpaper.setOnClickListener {
            var wallpaperManager:WallpaperManager = WallpaperManager.getInstance(this)
            try {
                wallpaperManager.setBitmap((pictureDrawable.value as BitmapDrawable).bitmap)
                Toast.makeText(this,"Wallpaper successfully changed",Toast.LENGTH_LONG).show()
            }catch (e:IOException){
                e.printStackTrace()
            }
        }
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
