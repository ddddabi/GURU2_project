package com.example.guru2_project

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MealInfo: AppCompatActivity() {

    lateinit var dbManager2: DBManager2
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var tvMDate: TextView
    lateinit var tvMFood: TextView
    lateinit var tvMTime: TextView
    lateinit var tvMMemo: TextView



    lateinit var btn_meal_list: ImageButton
    lateinit var btn_meal_delete: ImageButton
    lateinit var btn_main: ImageButton

    lateinit var str_date: String
    lateinit var str_food: String
    lateinit var str_time: String
    lateinit var str_memo: String


    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_info)

        tvMDate = findViewById(R.id.edtMDate)
        tvMFood = findViewById(R.id.edtMFood)
        tvMTime = findViewById(R.id.edtMTime)
        tvMMemo= findViewById(R.id.edtMMemo)


        btn_meal_list = findViewById(R.id.btn_meal_list)
        btn_main = findViewById(R.id.btn_main)
        btn_meal_delete = findViewById(R.id.btn_meal_delete)

        val intent = intent
        str_food = intent.getStringExtra("intent_food").toString()

        dbManager2 = DBManager2(this, "mealDB", null, 1)
        sqlitedb = dbManager2.readableDatabase

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM meal WHERE food = '"+str_food+"';", null)

        if(cursor.moveToNext()) {
            str_date = cursor.getString((cursor.getColumnIndex("date"))).toString()
            str_food = cursor.getString((cursor.getColumnIndex("food"))).toString()
            str_time = cursor.getString((cursor.getColumnIndex("time"))).toString()
            str_memo = cursor.getString((cursor.getColumnIndex("memo"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager2.close()

        tvMDate.text = str_date
        tvMFood.text = str_food
        tvMTime.text = str_time
        tvMMemo.text = str_memo + "\n"

        btn_meal_list.setOnClickListener {
            var intent = Intent(this, MealActivity::class.java)
            startActivity(intent)
        }

        btn_meal_delete.setOnClickListener {
            dbManager2 = DBManager2(this, "mealDB", null, 1)
            sqlitedb = dbManager2.readableDatabase

            sqlitedb.execSQL("DELETE FROM meal WHERE food = '"+str_food+"';")
            sqlitedb.close()
            dbManager2.close()

            val intent = Intent(this, MealActivity::class.java)
            startActivity(intent)
        }

        btn_main.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}
