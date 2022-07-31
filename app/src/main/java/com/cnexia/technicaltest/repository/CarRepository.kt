package com.cnexia.technicaltest.repository

import com.cnexia.technicaltest.network.ApiInterface
import com.cnexia.technicaltest.network.CarRemoteModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CarRepository @Inject constructor(private val apiInterface: ApiInterface) {

    //Proper error handling must be done
    fun getCars(): Single<List<CarRemoteModel>> {
        return apiInterface.getCars().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}