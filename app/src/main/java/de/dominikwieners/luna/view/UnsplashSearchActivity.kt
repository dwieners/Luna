package de.dominikwieners.luna.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import de.dominikwieners.luna.Navigator
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityUnsplashSearchBinding
import de.dominikwieners.luna.di.LunaApplication
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.viewmodel.UnsplashSearchViewModel
import javax.inject.Inject
import com.arlib.floatingsearchview.FloatingSearchView
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import de.dominikwieners.smarttoast.SmartToast


class UnsplashSearchActivity : AppCompatActivity() {


    @Inject
    lateinit var navigator: Navigator

    lateinit var binding:ActivityUnsplashSearchBinding
    lateinit var searchViewModel:UnsplashSearchViewModel

    lateinit var recycler: RecyclerView
    lateinit var unsplashPostAdapter: UnsplashPostAdapter
    lateinit var mLayoutManager: LinearLayoutManager


    private val START_PAGE = 1
    private var currentPage = START_PAGE
    private var isLoading = false

    private var currentQuery = " "



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as LunaApplication).getComponent().inject(this)
        initBinding()
        initViewModel()
        initToolbar()
        initSearchBar()
        initRecycler()

        initSearchBarActions()
        initRecyclerActions()
        showLoading()
    }

    fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_unsplash_search)
        binding.setLifecycleOwner(this)
    }

    private fun initViewModel(){
        searchViewModel = ViewModelProviders.of(this).get(UnsplashSearchViewModel::class.java)
    }

    fun initToolbar(){
        binding.unsplashSearchToolbar.title = " "
    }

    fun initSearchBar(){
        val slideInAnimation  = AnimationUtils.loadAnimation(this, R.anim.slide_in_to_left)
        binding.unsplashSearchSearchview.startAnimation(slideInAnimation)
    }

    fun initRecycler(){
        recycler = binding.unsplashSearchRvPosts
        this.mLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler.layoutManager = mLayoutManager
    }

    private fun initRecyclerAdapter(list: List<UnsplashPictureResponse>){
        this.unsplashPostAdapter = UnsplashPostAdapter(list as ArrayList<UnsplashPictureResponse>, this, navigator)
        recycler.adapter = unsplashPostAdapter
    }

    override fun onBackPressed() {
        super.onBackPressed()
        navigator.showUnsplashActivity(this)
    }

    private fun showLoading(){
        searchViewModel.isLoading.observe(this, Observer {
            it?.let {
                if(it){
                    binding.unsplashSearchProgressLoading.visibility = View.VISIBLE
                }else{
                    binding.unsplashSearchProgressLoading.visibility = View.GONE
                }
            }
        })

        searchViewModel.isNextLoading.observe(this, Observer {
            it?.let {
                isLoading = it
                if(it){
                    binding.unsplashSearchProgressNextLoading.visibility = View.VISIBLE
                }else{
                    binding.unsplashSearchProgressNextLoading.visibility = View.GONE
                }

            }
        })
    }

    private fun loadFirstData(query:String, page:Int, per_page: Int){
        currentQuery = query
        searchViewModel.fetchUnsplashSearchResult(query, page, per_page)
        searchViewModel.resultData.observe(this, Observer {
            it?.let {
                initRecyclerAdapter(it.results)
                if (it.total == 0){
                    loadingWithoutResult();
                }
                searchViewModel.isError.value = false

            }
        })
        //showLoadDataError()
    }

    private fun loadNextData(query:String, page: Int, per_page:Int){
        searchViewModel.fetchNextUnsplshSearchResult(query, per_page, page)
        searchViewModel.nextData.observe(this, Observer {
            it?.let {
                unsplashPostAdapter.addAll(it.results as ArrayList<UnsplashPictureResponse>)
                searchViewModel.isNextError.value = false
                searchViewModel.nextData.value = null
            }
        })
        //showLoadNextDataError()
    }

    private fun loadingWithoutResult(){
        SmartToast.infoToast(this, resources.getString(R.string.unsplash_search_loading_no_result)).show()
    }
    private fun initSearchBarActions(){
        binding.unsplashSearchSearchview.setOnSearchListener(object : FloatingSearchView.OnSearchListener {
            override fun onSearchAction(currentQuery: String?) {
                currentQuery?.let {
                    loadFirstData( it , 10, currentPage)
                }
            }

            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun initRecyclerActions(){
        var scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = mLayoutManager.itemCount
                val visibleItemCount = mLayoutManager.childCount
                val lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition()
                val firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition()

                if (!isLoading)
                    if( (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0){
                        currentPage++
                        loadNextData(currentQuery, currentPage, 10)
                    }

            }
        }
        recycler.addOnScrollListener(scrollListener)
    }
}
