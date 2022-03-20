package com.test.testtask65apps.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WorkerDao {

    @Transaction
    open fun saveWorkersWithJobs(workerEntities: List<WorkerWithJobs>) {
        workerEntities.forEach { workerWithJobs ->
            saveWorker(workerWithJobs.worker)
            saveJobs(workerWithJobs.jobs)
            workerWithJobs.jobs.forEach { jobEntity ->
                saveCrossReference(WorkerJobCrossRef(workerWithJobs.worker.id, jobEntity.id))
            }
        }

    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCrossReference(crossRef: WorkerJobCrossRef)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveWorker(workerEntities: WorkerEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveJobs(jobEntities: List<JobEntity>)

    @Query("SELECT * FROM ${JobEntity.TABLE_NAME} WHERE jobId=:jobId")
    abstract fun listenJobWithWorkers(jobId: Int): Flow<JobWithWorkers>

    @Query("SELECT * FROM ${WorkerEntity.TABLE_NAME} WHERE workerId=:workerId")
    abstract fun listenWorkerWithJobs(workerId: Int): Flow<WorkerWithJobs>

    @Query("SELECT * FROM ${JobEntity.TABLE_NAME}")
    abstract fun listenJobs(): Flow<List<JobEntity>>
}