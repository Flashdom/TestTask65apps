package com.test.testtask65apps.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = [WorkerEntity.COLUMN_ID, JobEntity.COLUMN_ID])
data class WorkerJobCrossRef(
    @ColumnInfo(name = WorkerEntity.COLUMN_ID)
    val workerId: Int,
    @ColumnInfo(name = JobEntity.COLUMN_ID)
    val jobId: Int
)