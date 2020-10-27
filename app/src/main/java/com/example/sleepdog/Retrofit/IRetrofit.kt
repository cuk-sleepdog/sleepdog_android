package com.example.sleepdog.Retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofit {
    @GET("/PetinfoAPI")
    fun testApi() : Call<JsonElement>



}