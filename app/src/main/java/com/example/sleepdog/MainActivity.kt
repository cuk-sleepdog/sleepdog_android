package com.example.sleepdog

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.sleepdog.Retrofit.ApiService
import com.example.sleepdog.Retrofit.Health
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer


class MainActivity : AppCompatActivity() {
    private val BASEURL = "http://sleepdog.mintpass.kr:3000/"


    var TAG: String = "로그"
    private val handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var temperature = findViewById(R.id.temperature) as TextView
        var heart_rate = findViewById(R.id.heart_rate) as TextView

        val intent = intent
        var strNickname = intent.getStringExtra("name")
        tvNickname.setText(strNickname);


        var retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


     var apiService = retrofit.create(ApiService::class.java);

        var call = apiService.getHealth()


        call.enqueue(object : Callback<List<Health>> {
            override fun onResponse(call: Call<List<Health>>, response: Response<List<Health>>) {
                println("성공")
                val MainHealth = response?.body()

                // var length = MainHealth?.size
                // Log.d(TAG, length.toString())

                for (health in MainHealth!!) {


//                    Log.d(TAG, content1);
//                    Log.d(TAG, content);
                    val timer = timer(period = 5000) {

                        runOnUiThread {
                            var content = health.getBpm()
                            var content1 = health.getTemp()
                            temperature.setText(content1)
                            heart_rate.setText(content) }
                    }

                }

                //타이머로 1분?간격으로 text값 바꾸기

            }

            override fun onFailure(call: Call<List<Health>>, t: Throwable) {
                println("실패")
            }
        })


        logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("로그아웃 하시겠습니까?")
            builder.setPositiveButton("확인") { dialogInterface, i ->
                UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
                    override fun onCompleteLogout() {
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)
                    }
                })
                dialogInterface.dismiss()
            }
            builder.setNegativeButton("취소") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

    }

    fun onSleepConfirmButtonClicked(view: View){
        val intent = Intent(this, HealthActivity::class.java)
        startActivity(intent)
    }

    fun onModifiedButtonClicked(view: View){
        val intent2 = Intent(this, SettingActivity::class.java)
        startActivity(intent2)
    }

    private class splashhandler : Runnable {
        override fun run() {

        }
    }

//    fun timer(content1: String, content: String){
//        temperature.setText(content1)
//        heart_rate.setText(content)
//    }
}