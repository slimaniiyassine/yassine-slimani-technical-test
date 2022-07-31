package com.cnexia.technicaltest.view.data

data class RecyclerViewItem(
    val title: String,
    val price: String,
    val image: String,
    val numberOfStarts: Int,
    val pros: List<String>,
    val cons: List<String>
)