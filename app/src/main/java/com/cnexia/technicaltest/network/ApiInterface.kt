package com.cnexia.technicaltest.network

import io.reactivex.Single
import retrofit2.http.PUT

interface ApiInterface {
    @PUT("cars")
    fun getCars(): Single<List<CarRemoteModel>>
}