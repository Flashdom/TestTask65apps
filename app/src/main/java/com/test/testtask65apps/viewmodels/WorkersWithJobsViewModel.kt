package com.test.testtask65apps.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask65apps.Repository
import com.test.testtask65apps.models.WorkerDetailed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WorkersWithJobsViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val workerLiveData = MutableLiveData<WorkerDetailed>()
    val worker: LiveData<WorkerDetailed>
        get() = workerLiveData

    fun listenWorkerWithJobs(workerId: Int) {
        viewModelScope.launch {
            repository.listenWorkersWithJobFromDb(workerId)
                .flowOn(Dispatchers.IO)
                .catch { throwable ->
                    Log.d("a", throwable.message?: "")
                    //Обработка ошибок
                }
                .collect { workerDetailed ->
                    workerLiveData.postValue(workerDetailed)
                }
        }
    }
}