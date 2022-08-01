package com.cnexia.technicaltest.di.module

import com.cnexia.technicaltest.di.viewmodel.ViewModelModule
import com.cnexia.technicaltest.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeDriversListActivity(): MainActivity
}