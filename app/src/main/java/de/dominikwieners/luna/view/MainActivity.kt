package de.dominikwieners.luna.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import de.dominikwieners.luna.R
import de.dominikwieners.luna.databinding.ActivityMainBinding
import de.dominikwieners.luna.viewmodel.StartViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.startViewModel =   ViewModelProviders.of(this).get(StartViewModel::class.java)
    }
}
