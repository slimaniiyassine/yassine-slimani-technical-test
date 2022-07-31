package com.cnexia.technicaltest.repository

import com.cnexia.technicaltest.network.CarRemoteModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CarsMapperTest {
    lateinit var carsMapper: CarsMapper
    lateinit var listOfModels: MutableList<CarRemoteModel>

    @Before
    fun setUp() {
        carsMapper = CarsMapper()
        listOfModels = mutableListOf()
        for (i in 0 until 10) {
            listOfModels.add(
                CarRemoteModel(
                    listOf("con $i"),
                    123,
                    "brand $i",
                    1254 * i,
                    "model$i",
                    listOf("pros $i"),
                    i % 5
                )
            )
        }
    }

    @Test
    fun testMapToRecyclerViewItems() {
        val result = carsMapper.mapToRecyclerViewItem(listOfModels)
        Assert.assertEquals(result.size, 10)
        Assert.assertEquals(result[0].cons.size, 1)
        Assert.assertEquals(result[0].pros.size, 1)
        Assert.assertEquals(result[0].image, "")
        Assert.assertEquals(result[0].numberOfStarts, 0)
        Assert.assertEquals(result[0].price, "123K")
    }

    @Test
    fun testMapToRecyclerViewItemsEmptyList() {
        val result = carsMapper.mapToRecyclerViewItem(emptyList())
        Assert.assertEquals(result.size, 0)
    }
}