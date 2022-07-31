package com.cnexia.technicaltest.di.module

import android.content.Context
import com.cnexia.technicaltest.ApplicationClass
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: ApplicationClass): Context {
        return application.applicationContext
    }
}