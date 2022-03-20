package com.test.testtask65apps

import com.test.testtask65apps.models.Job
import com.test.testtask65apps.models.Worker
import com.test.testtask65apps.models.WorkerDetailed
import com.test.testtask65apps.room.JobEntity
import com.test.testtask65apps.room.JobWithWorkers
import com.test.testtask65apps.room.WorkerEntity
import com.test.testtask65apps.room.WorkerWithJobs
import javax.inject.Inject

class WorkerMapper @Inject constructor() {

    private var workerId = 0

    fun mapWorkerDtoListToWorkerEntityList(workers: List<WorkerDto>): List<WorkerWithJobs> {
        return workers.map { workerDto ->
            WorkerWithJobs(
                worker = WorkerEntity(
                    id = ++workerId,
                    firstName = workerDto.firstName,
                    lastName = workerDto.lastName,
                    birthday = workerDto.birthday ?: "",
                    avatarUrl = workerDto.avatarUrl ?: ""
                ),
                jobs = mapJobDtoListToJobList(workerDto.job)
            )
        }
    }


    private fun mapJobDtoListToJobList(jobs: List<JobDto>): List<JobEntity> {
        return jobs.map { jobDto ->
            JobEntity(id = jobDto.jobId, name = jobDto.jobName)
        }
    }

    fun mapJobEntityListToJobList(jobsEntity: List<JobEntity>): List<Job> {
        return jobsEntity.map { jobEntity ->
            Job(id = jobEntity.id, name = jobEntity.name)
        }
    }

    fun mapWorkerWithJobsEntityListToWorkerDetailedList(workerWithJobs: WorkerWithJobs): WorkerDetailed {
        return WorkerDetailed(
            worker = Worker(
                id = workerWithJobs.worker.id,
                firstName = workerWithJobs.worker.firstName.lowercase()
                    .replaceFirstChar { char -> char.uppercase() },
                lastName = workerWithJobs.worker.lastName.lowercase()
                    .replaceFirstChar { char -> char.uppercase() },
                birthDay = if (workerWithJobs.worker.id < 5) convertServerDateToClientDate(
                    workerWithJobs.worker.birthday
                ) else convertBackwardsServerDateToClientDate(workerWithJobs.worker.birthday),
                avatarUrl = workerWithJobs.worker.avatarUrl,
                age = if (workerWithJobs.worker.id < 5) convertServerDateToAge(workerWithJobs.worker.birthday) else convertBackwardsServerDateToAge(
                    workerWithJobs.worker.birthday
                )

            ),
            job = mapJobEntityListToJobList(workerWithJobs.jobs)
        )
    }

    fun mapWorkerEntityToWorkerEntity(jobWithWorkerEntity: JobWithWorkers): List<Worker> {
        return jobWithWorkerEntity.workers.map { workerEntity ->
            Worker(
                id = workerEntity.id,
                firstName = workerEntity.firstName.lowercase()
                    .replaceFirstChar { char -> char.uppercase() },
                lastName = workerEntity.lastName.lowercase()
                    .replaceFirstChar { char -> char.uppercase() },
                birthDay = if (workerEntity.id < 5) convertServerDateToClientDate(
                    workerEntity.birthday
                ) else convertBackwardsServerDateToClientDate(workerEntity.birthday),
                avatarUrl = workerEntity.avatarUrl,
                age = if (workerEntity.id < 5) convertServerDateToAge(workerEntity.birthday) else convertBackwardsServerDateToAge(
                    workerEntity.birthday
                )
            )
        }
    }

}