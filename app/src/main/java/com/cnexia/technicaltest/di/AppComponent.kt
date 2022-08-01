package com.cnexia.technicaltest.di

import com.cnexia.technicaltest.ApplicationClass
import com.cnexia.technicaltest.di.module.ActivityBuildersModule
import com.cnexia.technicaltest.di.module.AppModule
import com.cnexia.technicaltest.di.module.NetworkModule
import com.cnexia.technicaltest.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<ApplicationClass> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: ApplicationClass): AppComponent
    }
}