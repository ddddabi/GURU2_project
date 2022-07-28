package com.example.guru2_project

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ExerciseActivity : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase
    lateinit var layout: LinearLayout

    lateinit var btn_exer_list: ImageButton
    lateinit var btn_home: ImageButton

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        dbManager = DBManager(this, "guru2DB", null, 1)
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
            layout_item.setGravity(Gravity.CENTER)
            layout_item.id = num

            var tvEDate: TextView = TextView(this)
            tvEDate.text = str_date
            tvEDate.textSize = 17f
            tvEDate.setTextColor(Color.parseColor("#000000"))
            layout_item.addView(tvEDate)

            var tvEName: TextView = TextView(this)
            tvEName.text = str_name
            tvEName.textSize = 17f
            tvEName.setTextColor(Color.parseColor("#000000"))
            layout_item.addView(tvEName)

            var tvStart: TextView = TextView(this)
            tvStart.text = str_start
            tvStart.textSize = 17f
            tvStart.setTextColor(Color.parseColor("#000000"))
            layout_item.addView(tvStart)

            var tvFinish: TextView = TextView(this)
            tvFinish.text = str_finish
            tvFinish.textSize = 17f
            tvFinish.setTextColor(Color.parseColor("#000000"))
            layout_item.addView(tvFinish)

            layout_item.setOnClickListener() {
                val intent = Intent(this, ExerciseInfo::class.java)
                intent.putExtra("intent_date", str_date)
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

