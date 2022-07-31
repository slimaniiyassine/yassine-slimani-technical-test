package com.cnexia.technicaltest.repository

import RxImmediateSchedulerRule
import com.cnexia.technicaltest.network.ApiInterface
import com.cnexia.technicaltest.network.CarRemoteModel
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CarRepositoryTest {
    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    private val apiInterface = mock<ApiInterface>()
    lateinit var listOfCars: MutableList<CarRemoteModel>

    @Before
    fun setUp() {
        listOfCars = mutableListOf()
        for (i in 0 until 10) {
            listOfCars.add(
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
        whenever(apiInterface.getCars()).thenReturn(Single.just(listOfCars))
    }

    @Test
    fun testGetDataFromApi() {
        val result = apiInterface.getCars()
        val testObserver = TestObserver<List<CarRemoteModel>>()
        result.test()
        result.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(listResult.size, 10)
        assertEquals(listResult[0].consList.size, 1)
        assertEquals(listResult[0].prosList.size, 1)
        assertEquals(listResult[0].numberOfStarts, 0)
        assertEquals(listResult[0].customerPrice, 123)
    }
}