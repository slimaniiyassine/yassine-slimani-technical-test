package com.cnexia.technicaltest.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.cnexia.technicaltest.di.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}