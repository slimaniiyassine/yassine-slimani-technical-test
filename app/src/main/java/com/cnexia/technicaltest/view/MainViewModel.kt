package com.cnexia.technicaltest.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnexia.technicaltest.repository.CarRepository
import com.cnexia.technicaltest.repository.CarsMapper
import com.cnexia.technicaltest.view.adapter.CarsAdapter
import com.cnexia.technicaltest.view.data.RecyclerViewItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val carRepository: CarRepository, val carsAdapter: CarsAdapter, private val carsMapper: CarsMapper) :
    ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _currentCarsList: MutableLiveData<List<RecyclerViewItem>> = MutableLiveData(emptyList())
    val currentCarsList: LiveData<List<RecyclerViewItem>> = _currentCarsList

    lateinit var initialCarsData: List<RecyclerViewItem>
    lateinit var filteredCarsData: List<RecyclerViewItem>

    fun getData() {
        compositeDisposable.add(getCars())
    }

    fun filterCarsByMake(make: String) {
        if (make.isNotEmpty()) {
            filteredCarsData = initialCarsData
            _currentCarsList.postValue(filteredCarsData.filter { it.makeTitle.uppercase(Locale.getDefault()).contains(make.uppercase(Locale.getDefault())) })
        } else {
            _currentCarsList.postValue(initialCarsData)
        }
    }

    fun filterCarsByModel(model: String) {
        if (model.isNotEmpty()) {
            filteredCarsData = initialCarsData
            _currentCarsList.postValue(filteredCarsData.filter { it.title.uppercase(Locale.getDefault()).contains(model.uppercase(Locale.getDefault())) })
        } else {
            _currentCarsList.postValue(initialCarsData)
        }
    }

    private fun getCars(): Disposable {
        return carRepository.getCars().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    initialCarsData = carsMapper.mapToRecyclerViewItem(it)
                    _currentCarsList.postValue(initialCarsData)
                },
                {
                    Timber.e(it)
                }
            )
    }

    fun disposeComposite() {
        compositeDisposable.dispose()
    }
}