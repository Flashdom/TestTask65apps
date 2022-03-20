package com.test.testtask65apps.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.testtask65apps.room.WorkerEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class WorkerEntity(
    @PrimaryKey()
    @ColumnInfo(name = COLUMN_ID)
    val id: Int,
    @ColumnInfo(name = COLUMN_FIRST_NAME)
    val firstName: String,
    @ColumnInfo(name = COLUMN_LAST_NAME)
    val lastName: String,
    @ColumnInfo(name = COLUMN_BIRTHDAY)
    val birthday: String,
    @ColumnInfo(name = COLUMN_AVATAR_URL)
    val avatarUrl: String
) {
    companion object {

        const val TABLE_NAME = "workers"
        const val COLUMN_ID = "workerId"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_BIRTHDAY = "birthday"
        const val COLUMN_AVATAR_URL = "avatar_url"
    }
}