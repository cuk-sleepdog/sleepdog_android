package com.example.sleepdog

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_health.*
import java.io.FileInputStream
import java.io.FileOutputStream

class HealthActivity : AppCompatActivity() {
    var TAG: String = "로그"
    var fname1: String = ""
    var fname2: String = ""
    var fname3: String = ""
    var strTemp: String = ""
    var strBpm: String = ""
    var strSleep: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
// 달력 날짜가 선택되면
            input.visibility = View.VISIBLE // 입력할수 있는
            save_Btn.visibility = View.VISIBLE // 저장 버튼이 Visible
            info.visibility = View.INVISIBLE // 저장된

            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            // 날짜를 보여주는 텍스트에 해당 날짜를 넣는다.
           // contextEditText.setText("") // EditText에 공백값 넣기

            checkedDay(year, month, dayOfMonth) // checkedDay 메소드 호출

        }

        save_Btn.setOnClickListener { // 저장 Button이 클릭되면
            saveDiary1(fname1)
            saveDiary2(fname2)
            saveDiary3(fname3)// saveDiary 메소드 호출
            Log.d(TAG, fname1+fname2+fname3 + "데이터를 저장했습니다.")
            strTemp = avg_temp.getText().toString() // str 변수에 edittext내용을 toString
            strBpm = avg_bpm.getText().toString() // str 변수에 edittext내용을 toString
            strSleep = avg_time.getText().toString() // str 변수에 edittext내용을 toString형으로 저장

            avg_temp2.text = "${strTemp}" // textView에 str 출력
            avg_bpm2.text = "${strBpm}"
            avg_time2.text = "${strSleep}"

            save_Btn.visibility = View.INVISIBLE

            input.visibility = View.INVISIBLE
            info.visibility = View.VISIBLE
        }

    }

    fun checkedDay(cYear: Int, cMonth: Int, cDay: Int) {
        //fname = "" + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
        fname1= ""+"체온"+" "+ cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
        fname2= ""+"심박수"+" "+ cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
        fname3= ""+"시간"+" "+ cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"
       // 저장할 파일 이름 설정. Ex) 2019-01-20.txt

        var fis1: FileInputStream? = null // FileStream fis 변수 설정
        var fis2: FileInputStream? = null // FileStream fis 변수 설정
        var fis3: FileInputStream? = null // FileStream fis 변수 설정

        try {
            fis1 = openFileInput(fname1) // fname 파일 오픈!!
            fis2 = openFileInput(fname2) // fname 파일 오픈!!
            fis3 = openFileInput(fname3) // fname 파일 오픈!!


            val fileData1 = ByteArray(fis1.available()) // fileData에 파이트 형식으로 저장
            val fileData2 = ByteArray(fis2.available()) // fileData에 파이트 형식으로 저장
            val fileData3 = ByteArray(fis3.available()) // fileData에 파이트 형식으로 저장

            fis1.read(fileData1) // fileData를 읽음
            fis1.close()

            fis2.read(fileData2) // fileData를 읽음
            fis2.close()

            fis3.read(fileData3) // fileData를 읽음
            fis3.close()

            strTemp = String(fileData1) // str 변수에 fileData를 저장
            strBpm = String(fileData2) // str 변수에 fileData를 저장
            strSleep = String(fileData3) // str 변수에 fileData를 저장

            input.visibility = View.INVISIBLE
            info.visibility = View.VISIBLE
            avg_temp2.text = "${strTemp}" // textView에 str 출력
            avg_bpm2.text = "${strBpm}" // textView에 str 출력
            avg_time2.text = "${strSleep}" // textView에 str 출력

            save_Btn.visibility = View.INVISIBLE


            if(textView2.getText() == ""){
                textView2.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                save_Btn.visibility = View.VISIBLE
                input.visibility = View.VISIBLE
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }



    @SuppressLint("WrongConstant")
    fun saveDiary1(readyDay: String) {
        var fos1: FileOutputStream? = null


        try {
            fos1 = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)


            var content1: String = avg_temp.getText().toString()


            fos1.write(content1.toByteArray())

            fos1.close()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @SuppressLint("WrongConstant")
    fun saveDiary2(readyDay: String) {

        var fos2: FileOutputStream? = null


        try {

            fos2 = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)



            var content2: String = avg_bpm.getText().toString()



            fos2.write(content2.toByteArray())

            fos2.close()


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    @SuppressLint("WrongConstant")
    fun saveDiary3(readyDay: String) {

        var fos3: FileOutputStream? = null

        try {

            fos3 = openFileOutput(readyDay, MODE_NO_LOCALIZED_COLLATORS)


            var content3: String = avg_time.getText().toString()


            fos3.write(content3.toByteArray())

            fos3.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun onBackButtonClicked(view: View) {
        finish()
    }
}