package com.example.sleepdog

import Retrofit.RetrofitManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    val TAG : String = "MainActivity"
    var Gender : String? = null
    var Kind : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val items = resources.getStringArray(R.array.breeds) //견종 어레이를 불러온다.
        //어레이 어뎁터를 이용해서 스피너와 연결
        val kindAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        dog_kind.setAdapter(kindAdapter)


        //성별 버튼 클릭시 Gender변수에 성별 값 저장
        btn_female.setOnClickListener {
            btn_female.setSelected(true)
            btn_male.setSelected(false)
            Gender = "여"
        }
        btn_male.setOnClickListener {
            btn_male.setSelected(true)
            btn_female.setSelected(false)
            Gender = "남"
        }

        dog_kind.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2) {
                    0-> {Kind = ""}//견종 어레이에서 디폴트값 선택되어있는경우 아무것도 하지 않는다

                    else ->{ //그외경우에 어떤게 선택되어졌는지 String으로 불러와서 Kind에 저장
                        Kind = dog_kind.getSelectedItem().toString()
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        btn_save.setOnClickListener {

            RetrofitManager.instance.searchPetname("멍이", completion = {
                    responseState, responseBody ->
                when(responseState) {
                    1->{
                        Log.d(TAG, "api 겟 호출 성공 $responseBody" )}
                }
            })

        }

    }
}