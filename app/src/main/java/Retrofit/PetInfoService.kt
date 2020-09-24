package Retrofit

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PetInfoService {
    @GET("/PetinfoAPI/{Petname}/")
    fun getPetInfo(@Path("Petname") Petname : String) : Call<JsonElement>


    //콜값과 콜백값은 petInfoApi.kt에 data class로 정의 해놓은 형식으로 주고 받는다
    @POST("/PetinfoAPI/")
    fun postPetInfo(@Body petInfosetting: petInfosetting) : Call<petInfosetting>


    //이런 형식으로도 Post 가능 하지만 @Body로 하는게 더 편함
    /*
    @FormUrlEncoded
    @POST("/PetinfoAPI/")
    fun postPetInfo(@Field("Petname") Petname : String,
                    @Field("Happy") Happy : String,
                    @Field("Kind") Kind : String,
                    @Field("Gender") Gender : String,
                    @Field("Weight") Weight : Int) : Call<JsonElement>
     */
}