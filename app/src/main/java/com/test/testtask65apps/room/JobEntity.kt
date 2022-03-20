package com.test.testtask65apps.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.testtask65apps.room.JobEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class JobEntity(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_NAME)
    val name: String
) {
    companion object {
        const val TABLE_NAME = "jobs"
        const val COLUMN_ID = "jobId"
        const val COLUMN_NAME = "name"
    }
}