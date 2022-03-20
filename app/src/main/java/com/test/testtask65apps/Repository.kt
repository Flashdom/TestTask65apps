package com.test.testtask65apps

import com.test.testtask65apps.models.Job
import com.test.testtask65apps.models.Worker
import com.test.testtask65apps.models.WorkerDetailed
import com.test.testtask65apps.network.Api
import com.test.testtask65apps.room.WorkerDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Repository @Inject constructor(
    private val mapper: WorkerMapper,
    private val api: Api,
    private val workerDao: WorkerDao
) {

    suspend fun fetchWorkers(): Flow<Unit> {
        return flow {
            emit(api.fetchData().response)
        }.map { workersDto ->
            mapper.mapWorkerDtoListToWorkerEntityList(workersDto)
        }.map { workerEntities ->
            workerDao.saveWorkersWithJobs(workerEntities)
        }
    }

    fun listenWorkersFromDb(jobId: Int): Flow<List<Worker>> {
        return workerDao.listenJobWithWorkers(jobId).map { workersList ->
            mapper.mapWorkerEntityToWorkerEntity(workersList)
        }
    }

    fun listenWorkersWithJobFromDb(workerId: Int): Flow<WorkerDetailed> {
        return workerDao.listenWorkerWithJobs(workerId).map { workerWithJobs ->
            mapper.mapWorkerWithJobsEntityListToWorkerDetailedList(workerWithJobs)
        }
    }

    fun listenJobsFromDb(): Flow<List<Job>> {
        return workerDao.listenJobs().map { jobsList ->
            mapper.mapJobEntityListToJobList(jobsList)
        }
    }
}