package com.cnexia.technicaltest.repository

import com.cnexia.technicaltest.network.CarRemoteModel
import com.cnexia.technicaltest.utils.addKToPrice
import com.cnexia.technicaltest.view.data.RecyclerViewItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsMapper @Inject constructor() {

    fun mapToRecyclerViewItem(cars: List<CarRemoteModel>): List<RecyclerViewItem> {
        val result: MutableList<RecyclerViewItem> = mutableListOf()
        cars.forEach {
            result.add(
                RecyclerViewItem(
                    it.model,
                    it.customerPrice.toString().addKToPrice(),
                    getImage(it.model),
                    it.numberOfStarts,
                    it.prosList.filter { pros -> pros.isNotEmpty() },
                    it.consList.filter { cons -> cons.isNotEmpty() },
                    it.make
                )
            )
        }
        return result
    }

    private fun getImage(brandModel: String): String {
        return when (brandModel) {
            "Range Rover" -> "range_rover"
            "Roadster" -> "alpine_roadster"
            "3300i" -> "bmw_330i"
            "GLE coupe" -> "mercedez_benz_glc"
            else -> ""
        }

    }
}