package com.test.testtask65apps.network

import com.test.testtask65apps.WorkerWrapperDto
import retrofit2.http.GET

interface Api {

    @GET("testTask.json")
    suspend fun fetchData(): WorkerWrapperDto
}