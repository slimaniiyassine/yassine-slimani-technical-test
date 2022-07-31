package com.cnexia.technicaltest.network

import android.content.Context
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import timber.log.Timber
import java.io.IOException


class MockClient(context: Context) : Interceptor {
    var context: Context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val url: HttpUrl = chain.request().url
        Timber.d("TAG", "url=$url")
        return when (url.encodedPath) {
            "/api/cars" -> {
                val response: String = getJsonDataFromAsset(context, "car_list.json")
                return Response.Builder()
                    .code(200)
                    .message(response)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_1)
                    .body(response.toByteArray().toResponseBody("application/json".toMediaTypeOrNull()))
                    .addHeader("content-type", "application/json")
                    .build()
            }
            else -> {
                Response.Builder().build()
            }
        }
    }


    init {
        this.context = context
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }
}