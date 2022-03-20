package com.test.testtask65apps.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class WorkerWithJobs(

    @Embedded
    val worker: WorkerEntity,

    @Relation(
        parentColumn = WorkerEntity.COLUMN_ID,
        entityColumn = JobEntity.COLUMN_ID,
        associateBy = Junction(WorkerJobCrossRef::class)
    )
    val jobs: List<JobEntity>
)