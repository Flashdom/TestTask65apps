package com.test.testtask65apps.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testtask65apps.Repository
import com.test.testtask65apps.models.Job
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val jobsMutableLivaData = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>>
        get() = jobsMutableLivaData

    init {
        fetchWorkers()
        listenJobsFromDb()
    }

    private fun fetchWorkers() {
        viewModelScope.launch {
            repository.fetchWorkers().flowOn(Dispatchers.IO).catch { throwable ->
                //Обработка ошибки
                Log.d("a", throwable.message ?: "")
            }.collect {
                //Обработка успеха
            }
        }
    }

    private fun listenJobsFromDb() {
        viewModelScope.launch {
            repository.listenJobsFromDb().flowOn(Dispatchers.IO).catch { throwable ->
                //Обработка ошибки
                Log.d("a", throwable.message ?: "")
            }.collect { jobs ->
                jobsMutableLivaData.postValue(jobs)
            }
        }
    }

}