package com.cnexia.technicaltest.view.data

//price should be int or float but for the sake of the test it is String
data class RecyclerViewItem(
    val title: String,
    val price: String,
    val image: String,
    val numberOfStarts: Int,
    val pros: List<String>,
    val cons: List<String>,
    val makeTitle: String
)