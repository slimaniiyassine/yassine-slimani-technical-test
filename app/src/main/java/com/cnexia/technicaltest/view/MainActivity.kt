package com.cnexia.technicaltest.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.cnexia.technicaltest.databinding.ActivityMainBinding
import com.cnexia.technicaltest.view.data.HeaderDataClass
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

//TODO: make filtering working
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
        binding.headerItemValue = getHeaderView()
        binding.vm = mainActivityViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)
        mainActivityViewModel.getData()
        addFilterListeners()
    }

    private fun addFilterListeners() {
        binding.filters.filterLayoutMakeET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mainActivityViewModel.filterCarsByMake(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.filters.filterLayoutModelET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                mainActivityViewModel.filterCarsByModel(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.cnexia.technicaltest.R.menu.main_menu, menu)
        return true
    }

    private fun getHeaderView(): HeaderDataClass {
        return HeaderDataClass(
            "tacoma",
            "Tacoma 2021",
            "Get your's now"
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityViewModel.disposeComposite()
    }
}