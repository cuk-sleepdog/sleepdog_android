package com.example.sleepdog.Retrofit

import android.util.Log
import androidx.constraintlayout.widget.Constraints.TAG
import com.example.sleepdog.utils.API
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }
    //레트로핏 인테페이스 가져오기
    private val iRetrofit : IRetrofit? = RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)

    //api호출
    fun testApi(testPetName:String?,completion: (String) -> Unit){
        val call = iRetrofit?.testApi().let{
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                TODO("Not yet implemented")
                Log.d(TAG, "RetrofitManager-onResponse() called/response : ${response.raw()}")
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                TODO("Not yet implemented")
                Log.d(TAG, "RetrofitManager-onFailure() called/t:$t")
            }

        })
    }
}