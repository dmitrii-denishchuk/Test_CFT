package ru.iji.test_cft.data.database.remote

import retrofit2.http.GET

interface RandomUserApi {

    @GET("?results=25&noinfo")
    suspend fun getData(): ServiceResponse
}