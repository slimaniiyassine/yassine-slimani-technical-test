package com.cnexia.technicaltest.view

import androidx.lifecycle.ViewModel
import com.cnexia.technicaltest.di.repository.CarRepository
import com.cnexia.technicaltest.network.CarRemoteModel
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val carRepository: CarRepository) : ViewModel() {

    fun someKindOfInit() {
        Timber.d("MainViewModel called")
    }

    fun getCars(): Single<List<CarRemoteModel>> {
        return carRepository.getCars()
    }
}