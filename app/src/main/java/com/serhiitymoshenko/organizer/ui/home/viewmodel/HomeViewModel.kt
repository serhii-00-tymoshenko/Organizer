package com.serhiitymoshenko.organizer.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.serhiitymoshenko.organizer.ui.repositories.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class HomeViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val isFirstLaunch = dataStoreRepository.getAppDataStoreFlow().asLiveData()
    fun getIsFistLaunch() = isFirstLaunch

    fun changeIsFirstLaunchToFalse() = viewModelScope.launch(Dispatchers.IO + SupervisorJob()) {
        dataStoreRepository.changeIsFirstLaunchToFalse()
    }
}