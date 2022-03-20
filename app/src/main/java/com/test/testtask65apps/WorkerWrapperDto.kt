package com.test.testtask65apps

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WorkerWrapperDto(
    @Json(name = "response")
    val response: List<WorkerDto>
)

@JsonClass(generateAdapter = true)
data class WorkerDto(
    @Json(name = "f_name")
    val firstName: String,
    @Json(name = "l_name")
    val lastName: String,
    @Json(name = "birthday")
    val birthday: String?,
    @Json(name = "avatr_url")
    val avatarUrl: String?,
    @Json(name = "specialty")
    val job: List<JobDto>
)

@JsonClass(generateAdapter = true)
data class JobDto(
    @Json(name = "specialty_id")
    val jobId: Int,
    @Json(name = "name")
    val jobName: String
)