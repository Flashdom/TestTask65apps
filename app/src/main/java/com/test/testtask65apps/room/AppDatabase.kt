package com.test.testtask65apps.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [JobEntity::class, WorkerEntity::class, WorkerJobCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
}