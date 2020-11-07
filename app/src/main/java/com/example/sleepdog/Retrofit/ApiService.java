package com.example.sleepdog.Retrofit;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("HealthAPI")
    Call<List<Health>> getHealth();

}
