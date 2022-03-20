package com.test.testtask65apps.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask65apps.Repository
import com.test.testtask65apps.models.Worker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkersViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val workersLiveData = MutableLiveData<List<Worker>>()
    val workers: LiveData<List<Worker>>
        get() = workersLiveData


    fun listenWorkersFromDb(jobId: Int) {
        viewModelScope.launch {
            repository.listenWorkersFromDb(jobId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    Log.d("a", throwable.message ?: "")
                    //Обработка ошибок
                }
                .collect { workerList ->
                    workersLiveData.postValue(workerList)
                }
        }
    }
}