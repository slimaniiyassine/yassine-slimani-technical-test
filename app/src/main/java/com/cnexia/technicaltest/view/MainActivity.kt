package com.cnexia.technicaltest.view

import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.cnexia.technicaltest.databinding.ActivityMainBinding
import com.cnexia.technicaltest.view.data.HeaderDataClass
import com.cnexia.technicaltest.view.data.RecyclerViewItem
import dagger.android.support.DaggerAppCompatActivity
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
        binding.headerItem = getHeaderView()
        binding.recyclerViewItem = getRecyclerViewItem()
        setContentView(binding.root)
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

    private fun getRecyclerViewItem(): RecyclerViewItem {
        return RecyclerViewItem(
            "Alpine roadster",
            "120K",
            "alpine_roadster",
            5,
//            listOf(
//                "4 wheel drive",
//                "Disk brake",
//                "Good sound system"
//            ),
//            listOf("Bad direction")
            emptyList(),
            emptyList()
        )
    }
}