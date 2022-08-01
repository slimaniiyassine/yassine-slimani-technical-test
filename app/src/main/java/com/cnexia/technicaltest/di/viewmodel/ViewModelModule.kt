package com.cnexia.technicaltest.di.viewmodel

import androidx.lifecycle.ViewModel
import com.cnexia.technicaltest.view.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindDriverListViewModel(viewModel: MainViewModel): ViewModel
}