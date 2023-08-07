package com.saad.fragmentsmvvm.api

import com.saad.fragmentsmvvm.model.Jokes
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("get_memes")
    suspend fun getMemes(): Response<Jokes>
}