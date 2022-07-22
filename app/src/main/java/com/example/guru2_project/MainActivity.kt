package com.example.guru2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // 변수 선언
    lateinit var startButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 엑티비티에 추가한 위젯과 연결
        startButton = findViewById(R.id.startButton)

        // start 버튼 클릭시 캘린더 화면으로 전환
        startButton.setOnClickListener {
            var intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }
}