package com.example.guru2_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MenuActivity : AppCompatActivity() {

    lateinit var btn_meal: ImageButton
    lateinit var btn_exercise: ImageButton
    lateinit var btn_bmi: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btn_meal = findViewById(R.id.btn_meal)
        btn_exercise = findViewById(R.id.btn_exercise)
        btn_bmi = findViewById(R.id.btn_bmi)

        // btn_meal 버튼 클릭시 식단 기록 화면으로 전환
//        btn_meal.setOnClickListener {
//            var intent = Intent(this, MealActivity::class.java)
//            startActivity(intent)
//        }

        // btn_exercise 버튼 클릭시 운동 기록 화면으로 전환
        btn_exercise.setOnClickListener {
            var intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        // btn_bmi 버튼 클릭시 bmi 측정 화면으로 전환
        btn_bmi.setOnClickListener {
            var intent = Intent(this, BMICheck::class.java)
            startActivity(intent)
        }
    }
}