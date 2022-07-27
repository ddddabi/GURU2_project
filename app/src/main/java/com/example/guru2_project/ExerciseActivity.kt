package com.example.guru2_project

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ExerciseActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout
    lateinit var btn_exer_list: Button
    lateinit var btn_home: Button

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        dbManager = DBManager(this, "guruDB", null, 1)
        sqlitedb = dbManager.readableDatabase

        layout = findViewById(R.id.ExerciseList)
        btn_exer_list = findViewById(R.id.btn_exer_list)
        btn_home = findViewById(R.id.btn_home)

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM GURU2Project", null)

        var num: Int = 0
        while (cursor.moveToNext()) {

            var str_name = cursor.getString((cursor.getColumnIndex("name"))).toString()
            var str_date = cursor.getString((cursor.getColumnIndex("date"))).toString()
            var str_start = cursor.getString((cursor.getColumnIndex("startTime"))).toString()
            var str_finish = cursor.getString((cursor.getColumnIndex("finishTime"))).toString()

            var layout_item:LinearLayout = LinearLayout(this)
            layout_item.orientation = LinearLayout.HORIZONTAL
            layout_item.id = num

            var tvEName: TextView = TextView(this)
            tvEName.text = str_name
            tvEName.textSize = 10f
            tvEName.setTextColor(Color.BLACK)
            layout_item.addView(tvEName)

            var tvEDate: TextView = TextView(this)
            tvEDate.text = str_name
            tvEDate.textSize = 15f
            tvEDate.setTextColor(Color.BLACK)
            layout_item.addView(tvEDate)

            var tvStart: TextView = TextView(this)
            tvStart.text = str_start
            tvStart.textSize = 10f
            tvStart.setTextColor(Color.BLACK)
            layout_item.addView(tvStart)

            var tvFinish: TextView = TextView(this)
            tvFinish.text = str_finish
            tvFinish.textSize = 10f
            tvFinish.setTextColor(Color.BLACK)
            layout_item.addView(tvFinish)

            layout_item.setOnClickListener() {
                val intent = Intent(this, ExerciseInfo::class.java)
                intent.putExtra("intent_name", str_name)
                startActivity(intent)
            }

            layout.addView(layout_item)
            num++;
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        btn_exer_list.setOnClickListener {
            var intent = Intent(this, ExerciseReg::class.java)
            startActivity(intent)
        }

        btn_home.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}