package com.cnexia.technicaltest.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cnexia.technicaltest.repository.CarRepository
import com.cnexia.technicaltest.repository.CarsMapper
import com.cnexia.technicaltest.view.adapter.CarsAdapter
import io.reactivex.Single
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Rule
    @JvmField
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainViewModel: MainViewModel
    private val carRepository = mock<CarRepository>()
    private val carsAdapter = mock<CarsAdapter>()
    private val carsMapper = mock<CarsMapper>()

    @Before
    fun setUp() {
        mainViewModel = MainViewModel(carRepository, carsAdapter, carsMapper)
        whenever(carRepository.getCars()).thenReturn(Single.just(emptyList()))
    }

    @Test
    fun testGetData() {
        mainViewModel.getData()
        verify(carRepository).getCars()
    }

    @Test
    fun testSetFabButtonStateDefaultValue() {
        assertEquals(mainViewModel.currentCarsList.value?.size, 0)
    }
}