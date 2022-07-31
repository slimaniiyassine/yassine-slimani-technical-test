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
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainViewModel @Inject constructor(private val carRepository: CarRepository, val carsAdapter: CarsAdapter, val carsMapper: CarsMapper) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val _currentCarsList: MutableLiveData<List<RecyclerViewItem>> = MutableLiveData(emptyList())
    val currentCarsList: LiveData<List<RecyclerViewItem>> = _currentCarsList

    fun getData() {
        compositeDisposable.add(getCars())
    }

    private fun getCars(): Disposable {
        return carRepository.getCars().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    _currentCarsList.postValue(carsMapper.mapToRecyclerViewItem(it))
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