package Retrofit

import android.content.ContentValues
import android.util.Log
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response

class RetrofitManager {
    companion object {
        val instance = RetrofitManager() //레트로핏매니저를 다른곳에서도 불러올수 잇게 해줌 ( Static )
    }

    //baseurl과 선언한 interface를 가지고 있는 레트로핏을 만들어준다
    private val petInfo : PetInfoService? = RetrofitClient.getService("http://sleepdog.mintpass.kr:3000")?.create(Retrofit.PetInfoService::class.java)


    //Get할때 액티비티에서 호출해야 하는 함수
    fun searchPetname(Petname : String?, completion: (Int, String) -> Unit) {

        //Petname이 Null값일수도 잇으므로 Null값일 때 ""을 넣는다
        val term = Petname.let {
            it
        }?:""

        val call = petInfo?.getPetInfo(Petname = term).let {
            it
        }?: return

        call.enqueue(object : retrofit2.Callback<JsonElement> {
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(ContentValues.TAG, "레트로핏 매니저 - 실패 콜 /t : $t")

                completion(0, t.toString()) //실패하면 0이랑 오류를 스트링으로 담는다
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(ContentValues.TAG, "레트로핏 매니저 - 성공 / response : ${response.body()} ")

                completion(1, response.body().toString()) //성공하면 1이랑 바디값을 스트링으로 담는다
            }

        })
    }

    //Post할때 액티비티에서 호출해야 하는 함수
    fun postPetInfoService (petname: String, happy: String, kind: String, gender: String, weight: Int, completion: (Int, String) -> Unit) {

        val call = petInfo?.postPetInfo(petInfosetting(petname, happy, kind, gender, weight)).let {
            it
        }?: return


        call.enqueue(object: retrofit2.Callback<petInfosetting> {
            override fun onFailure(call: Call<petInfosetting>, t: Throwable) {
                Log.d(ContentValues.TAG, "포스트 실패 /t: $t")
                completion(0, t.toString())
            }

            override fun onResponse(call: Call<petInfosetting>, response: Response<petInfosetting>) {
                Log.d(ContentValues.TAG, "포스트 성공 /response : ${response.body()}")
                completion(1, response.body().toString())
            }

        })
    }
}