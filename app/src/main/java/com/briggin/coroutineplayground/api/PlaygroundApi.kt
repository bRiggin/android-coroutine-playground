package com.briggin.coroutineplayground.api

import com.briggin.coroutineplayground.api.model.ApiModel
import com.briggin.coroutineplayground.api.model.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

const val BASE_URL = "http://trials.mtcmobile.co.uk/api/football/1.0/"

interface PlaygroundApi {
    @POST("search")
    fun getData(@Body body: RequestBody): Call<ApiModel>
}