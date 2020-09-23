package com.example.sleepdog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SettingsSlicesContract
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    var TAG: String = "로그"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity-onCreate() called")
    }

    fun onSleepConfirmButtonClicked(view: View){
        val intent = Intent(this,HealthActivity::class.java)
        startActivity(intent)
    }

    fun onModifiedButtonClicked(view: View){
        val intent2 = Intent(this,SettingActivity::class.java)
        startActivity(intent2)
    }
}