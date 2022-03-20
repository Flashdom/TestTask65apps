package com.test.testtask65apps.room

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class JobWithWorkers(
    @Embedded
    val job: JobEntity,
    @Relation(
        parentColumn = JobEntity.COLUMN_ID,
        entityColumn = WorkerEntity.COLUMN_ID,
        associateBy = Junction(WorkerJobCrossRef::class)
    )
    val workers: List<WorkerEntity>
)