package com.cnexia.technicaltest.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.cnexia.technicaltest.R
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu -> {
                Toast.makeText(this, R.string.main_activity_menu_item_toast_message, Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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