package de.dominikwieners.luna.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.dominikwieners.luna.databinding.ActivityMainBinding
import de.dominikwieners.luna.viewmodel.StartViewModel

class MainActivity : AppCompatActivity() {

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



    private lateinit var binding: ActivityMainBinding
    private lateinit var startViewModel: StartViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)!!
        startViewModel = ViewModelProviders.of(this).get(StartViewModel::class.java)
        binding.setLifecycleOwner(this)
        setContentView(binding.root)
        startViewModel.fetchPictures()
    }


}
