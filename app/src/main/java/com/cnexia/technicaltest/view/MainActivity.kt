package com.cnexia.technicaltest.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.cnexia.technicaltest.R
import com.cnexia.technicaltest.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding

    private val mainActivityViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        mainActivityViewModel.someKindOfInit()
        mainActivityViewModel.getCars().subscribe(
            {
                Timber.d("Cars %d", it.size)
            },
            {
               Timber.e(it)
            }
        )
    }
}