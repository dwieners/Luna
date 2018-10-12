package de.dominikwieners.luna.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import de.dominikwieners.luna.Navigator
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityUnsplashBinding
import de.dominikwieners.luna.di.LunaApplication
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.repository.unsplash.UnsplashService
import de.dominikwieners.luna.viewmodel.UnsplashViewModel
import kotlinx.android.synthetic.main.activity_unsplash.view.*
import javax.inject.Inject




class UnsplashActivity : AppCompatActivity(){

    // View (Activity/Fragment/XML)
    //--------------------------------
    // Working with android.view, android.widget, etc.
    // Showing Dialogs, Toasts, Snackbars
    // Event listeners
    // Startign Activitey
    // Handling Menu
    // Handling permissions
    // Other Android specific stuff

    // Event Listeners
    //--------------------
    // Should be implementet in the View
    // onRefreshButtonClick, onDialogPositiveClick

    // Expressions
    //--------------------
    // Views should be as dumb as possible
    // Primitve expressions are probably OK
    // Implementaon should be in the View Model

    //---------------------------------------
    // public interface StockDetailView {
    //      void onButtonClick(View view);
    //}
    //---------------------------------------
    // public void onButtonClick(View view){
    //  getViewModel().loadStock();
    //}
    //----------------------------------------
    //
    // Define view and viewModel in xml layout
    //
    //


    private lateinit var startViewModel: UnsplashViewModel
    private lateinit var binding:ActivityUnsplashBinding

    private lateinit var recycler:RecyclerView
    private lateinit var unsplashPostAdapter:UnsplashPostAdapter
    private lateinit var mLayoutManager: LinearLayoutManager

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle

    private val START_PAGE = 1
    private var currentPage = START_PAGE

    private var isLoading = false

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as LunaApplication).getComponent().inject(this)
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
        initToolbar()
        initRecycler()




        loadFirstData(currentPage, 10, UnsplashService.Order.ORDER_BY_LATEST)
        var scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = mLayoutManager.itemCount
                val visibleItemCount = mLayoutManager.childCount
                val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()

                startViewModel.isNextLoading.get()?.let {
                    isLoading = it
                }

                if (!isLoading)
                    if( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0){
                        currentPage++
                        loadNextData(currentPage, 10, UnsplashService.Order.ORDER_BY_LATEST)
                    }

                }
        }
        recycler.addOnScrollListener(scrollListener)
    }


    private fun initViewModel(){
        startViewModel = ViewModelProviders.of(this).get(UnsplashViewModel::class.java)
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unsplash)
        binding.viewmodel = startViewModel
        binding.setLifecycleOwner(this)
    }

    private fun initToolbar(){
        binding.activityUnsplashToolbar.title = getString(R.string.unsplash_name)
        setSupportActionBar(binding.activityUnsplashToolbar)

        toolbar = binding.activityUnsplashToolbar
        drawer = binding.drawerLayout

        drawerToggle = ActionBarDrawerToggle(this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun initRecycler(){
        recycler = binding.root.rv_unsplash_posts
        this.mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = mLayoutManager
    }

    private fun initRecyclerAdapter(list: List<UnsplashPictureResponse>){
        this.unsplashPostAdapter = UnsplashPostAdapter(list as ArrayList<UnsplashPictureResponse>, this, navigator)
        recycler.adapter = unsplashPostAdapter
    }


    private fun loadFirstData(page:Int, per_page:Int, order:String){
        startViewModel.fetchUnsplashPictures(page, per_page, order)
        startViewModel.resultData.observe(this, Observer {
            it?.let {
                initRecyclerAdapter(it)
                startViewModel.isError.value = false
            }
        })
        showLoadDataError()
    }

    private fun loadNextData(page:Int, per_page: Int, order: String){
        startViewModel.fetchNextUnsplshPictures(page, per_page, order)
        startViewModel.nextData.observe(this, Observer {
            it?.let {
                unsplashPostAdapter.addAll(it as ArrayList<UnsplashPictureResponse>)
                startViewModel.isNextError.value = false
                startViewModel.nextData.value = null
            }
        })
        showLoadNextDataError()
    }

    private fun showLoadDataError(){
        startViewModel.isError.observe(this, Observer {
            it?.let {
                if(it) {
                    Toast.makeText(this, "no Internet connection", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun showLoadNextDataError(){
        startViewModel.isNextError.observe(this, Observer {
            it?.let {
                if(it){
                    Toast.makeText(this, "no Internet for more data", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.main_menu_giphy -> {navigator.showGifActivity(this)}
            R.id.main_menu_search -> {navigator.showUnsplashSearchActivity(this, true)}

        }
        return true
    }

}
