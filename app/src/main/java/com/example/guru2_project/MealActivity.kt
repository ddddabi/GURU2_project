package com.example.guru2_project

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout


class MealActivity : AppCompatActivity() {

    // 식단 기록 버튼, 홈 버튼
    lateinit var btn_meal_record: ImageButton
    lateinit var btn_home: ImageButton

    lateinit var dbManager2: DBManager2
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        //데이터 읽기 전용으로 받기기
        dbManager2 = DBManager2(this, "mealDB", null,1)
        sqlitedb = dbManager2.readableDatabase

        btn_meal_record = findViewById(R.id.btn_meal_record)
        btn_home = findViewById(R.id.btn_home)

        // btn_meal_record 버튼 클릭시 기록화면으로 전환
        btn_meal_record.setOnClickListener {
            var intent = Intent(this, MealReg::class.java)
            startActivity(intent)
        }

        // btn_home 버튼 클릭시 선택메뉴로 화면으로 전환
        btn_home.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }
}