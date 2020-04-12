package com.chudy.miquidocatfacts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.chudy.miquidocatfacts.model.CatFactRepository
import com.chudy.miquidocatfacts.networking.Resource
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    factory { MainViewModel(get()) }
}

/**
 * ViewModel to connect to the activity, it's loading a resource from the repository
 * and sends the data when it is obtained.
 */
class MainViewModel(private val catFactRepository: CatFactRepository): ViewModel() {
    fun loadData() = liveData(Dispatchers.IO){
        emit(Resource.loading(null))
        emit(catFactRepository.getThirtyRandomCatFacts())
    }
}