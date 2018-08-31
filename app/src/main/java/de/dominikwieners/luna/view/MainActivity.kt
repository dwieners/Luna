package de.dominikwieners.luna.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityMainBinding
import de.dominikwieners.luna.model.UnsplashPictureResponse
import de.dominikwieners.luna.viewmodel.StartViewModel

class MainActivity : AppCompatActivity(){

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


    private lateinit var startViewModel: StartViewModel
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
        loadData()
    }

    private fun initViewModel(){
        startViewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
    }

    private fun initBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = startViewModel
        binding.setLifecycleOwner(this)
    }

    private fun loadData(){
        startViewModel.fetchPictures()
        startViewModel.resultdata.observe(this, Observer {
            Toast.makeText(this, it!![0].color, Toast.LENGTH_LONG).show()
        })
    }












}
