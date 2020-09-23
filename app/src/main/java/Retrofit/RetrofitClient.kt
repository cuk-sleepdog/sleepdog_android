package Retrofit

import android.content.ContentValues
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class petInfosetting (
    val Petname : String,
    val Happy : String,
    val Kind : String,
    val Gender : String,
    val Weight : Int
)


object RetrofitClient {
    //retrofitClient 는 null값은 못가지고 추후 초기화
    private var petinforetrofit: Retrofit? = null
    //private lateinit var petinforetrofit: Retrofit

    //PetInfoService 인터페이스를 상속받아서 초기화를 시켜준다.
    fun getService(baseUrl : String) : Retrofit? {
        Log.d(ContentValues.TAG, "레트로핏 클라이언트 - 겟 서비스 called")

        //널값을 가질경우 생성
        if (petinforetrofit == null) {
            petinforetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return petinforetrofit
    }
}