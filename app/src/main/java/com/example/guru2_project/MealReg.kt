package com.example.guru2_project

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class MealReg : AppCompatActivity() {

    // DBManager2 연결
    lateinit var dbManager2: DBManager2
    lateinit var sqlitedb: SQLiteDatabase

    // 이미지 버튼에 대한 변수 선언
    lateinit var btn_meal_save:ImageButton
    lateinit var btn_meal_list:ImageButton
    lateinit var btn_main:ImageButton

    // edtMDate, edtMFood, edtMTime에 대한 EditText 만들기
    lateinit var edtMDate:EditText
    lateinit var edtMFood:EditText
    lateinit var edtMTime:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_reg)



        btn_meal_save = findViewById(R.id.btn_meal_save)
        btn_meal_list = findViewById(R.id.btn_meal_list)
        btn_main=findViewById(R.id.btn_main)

        //btn_main 클릭 시 메뉴 화면으로 이동
        btn_main.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        // btn_meal_list 클릭시 식단기록 화면으로 이동
        btn_meal_list.setOnClickListener {
            var intent = Intent(this, MealActivity::class.java)
            startActivity(intent)
        }

        edtMDate = findViewById(R.id.edtMDate)
        edtMFood = findViewById(R.id.edtMFood)
        edtMTime = findViewById(R.id.edtMTime)

        //DBMANAGER2 객체 받아오기 (데이터 베이스에 받아와서 전달하고 intent로 다음 화면으로 전달할 수 있도록)
        dbManager2 = DBManager2(this, "mealDB", null, 1)

        btn_meal_save.setOnClickListener {
            var str_date: String = edtMDate.text.toString()
            var str_food: String = edtMFood.text.toString()
            var str_time: String = edtMTime.text.toString()
            //데이터 저장
            sqlitedb = dbManager2.writableDatabase
            sqlitedb.execSQL("INSERT INTO meal VALUES('"+str_date+"', '"+str_food+"', '"+str_time+"')")

            //닫아주기
            sqlitedb.close()

            // 저장된 값 MealActivity로 넘겨주기

            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("intent_food", str_food)
            startActivity(intent)

        }

    }

}