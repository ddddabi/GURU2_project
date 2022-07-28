package com.example.guru2_project

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView


class MealActivity : AppCompatActivity() {

    // 식단 기록 버튼, 홈 버튼
    lateinit var btn_meal_record: ImageButton
    lateinit var btn_home: ImageButton

    lateinit var dbManager2: DBManager2
    lateinit var sqlitedb: SQLiteDatabase



    lateinit var layout5: LinearLayout
    lateinit var layout6: LinearLayout
    lateinit var layout7: LinearLayout


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        //데이터 읽기 전용으로 받기기
        dbManager2 = DBManager2(this, "mealDB", null,1)
        sqlitedb = dbManager2.readableDatabase


        layout5 = findViewById(R.id.MDate)
        layout6 = findViewById(R.id.MFood)
        layout7 = findViewById(R.id.MTime)

        btn_home = findViewById(R.id.btn_home)
        btn_meal_record = findViewById(R.id.btn_meal_record)


        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT*FROM meal", null)

        var num: Int = 0
        while (cursor.moveToNext()){

            var str_date = cursor.getString((cursor.getColumnIndex("date"))).toString()
            var str_food = cursor.getString((cursor.getColumnIndex("food"))).toString()
            var str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()

            var layout5_item:LinearLayout = LinearLayout(this)
            layout5_item.orientation = LinearLayout.HORIZONTAL
            layout5_item.setGravity(Gravity.CENTER);
            layout5_item.id = num

            var tvMDate: TextView = TextView(this)
            tvMDate.text = str_date
            tvMDate.textSize = 10f
            tvMDate.setTextColor(Color.parseColor("#000000"))
            layout5_item.addView(tvMDate)

            var layout6_item:LinearLayout = LinearLayout(this)
            layout6_item.orientation = LinearLayout.HORIZONTAL
            layout6_item.setGravity(Gravity.CENTER)
            layout6_item.id = num

            var tvMFood: TextView = TextView(this)
            tvMFood.text = str_food
            tvMFood.textSize = 10f
            tvMFood.setTextColor(Color.parseColor("#000000"))
            layout6_item.addView(tvMFood)

            var layout7_item:LinearLayout = LinearLayout(this)
            layout7_item.orientation = LinearLayout.HORIZONTAL
            layout7_item.setGravity(Gravity.CENTER);
            layout7_item.id = num

            var tvMTime: TextView = TextView(this)
            tvMTime.text = str_time
            tvMTime.textSize = 10f
            tvMTime.setTextColor(Color.parseColor("#000000"))
            layout7_item.addView(tvMTime)



            layout5.addView(layout5_item)
            layout6.addView(layout6_item)
            layout7.addView(layout7_item)
            num++;

        }

        cursor.close()
        sqlitedb.close()
        dbManager2.close()

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