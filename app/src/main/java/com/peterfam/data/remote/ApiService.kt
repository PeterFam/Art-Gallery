package com.peterfam.data.remote

import com.peterfam.data.remote.model.ImageResponse
import com.peterfam.utils.Util
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/")
    suspend fun searchImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = Util.apikey
    ): Response<ImageResponse>


}