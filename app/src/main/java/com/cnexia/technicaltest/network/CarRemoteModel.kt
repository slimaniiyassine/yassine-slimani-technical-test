package com.cnexia.technicaltest.network

import com.google.gson.annotations.SerializedName

data class CarRemoteModel(
    @SerializedName("consList") val consList: List<String>,
    @SerializedName("customerPrice") var customerPrice: Int,
    @SerializedName("make") var brand: String,
    @SerializedName("marketPrice") var marketPrice: Int,
    @SerializedName("model") var brandModel: String,
    @SerializedName("prosList") var prosList: List<String>,
    @SerializedName("rating") var numberOfStarts: Int
)