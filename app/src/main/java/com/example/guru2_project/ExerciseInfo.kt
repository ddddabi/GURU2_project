package com.example.guru2_project

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class ExerciseInfo : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var tvEDate: TextView
    lateinit var tvEName: TextView
    lateinit var tvStart: TextView
    lateinit var tvFinish: TextView

    lateinit var btnlist: ImageButton
    lateinit var btnDelete: ImageButton
    lateinit var btnMain: ImageButton

    lateinit var str_date: String
    lateinit var str_name: String
    lateinit var str_start: String
    lateinit var str_finish: String

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_info)

        tvEDate = findViewById(R.id.edtMDate)
        tvEName = findViewById(R.id.edtMFood)
        tvStart = findViewById(R.id.edtMTime)
        tvFinish = findViewById(R.id.edtFinish)

        btnlist = findViewById(R.id.btn_meal_list)
        btnMain = findViewById(R.id.btn_main)
        btnDelete = findViewById(R.id.btn_meal_delete)

        val intent = intent
        str_date = intent.getStringExtra("intent_date").toString()
        str_name = intent.getStringExtra("intent_name").toString()


        dbManager = DBManager(this, "guru2DB", null, 1)
        sqlitedb = dbManager.readableDatabase

        var cursor: Cursor
        cursor = sqlitedb.rawQuery("SELECT * FROM GURU2Project WHERE date = '"+str_date+"' and name = '"+str_name+"';", null)

        if(cursor.moveToNext()) {
            str_name = cursor.getString((cursor.getColumnIndex("name"))).toString()
            str_start = cursor.getString((cursor.getColumnIndex("startTime"))).toString()
            str_finish = cursor.getString((cursor.getColumnIndex("finishTime"))).toString()
        }

        cursor.close()
        sqlitedb.close()
        dbManager.close()

        tvEDate.text = str_date
        tvEName.text = str_name
        tvStart.text = str_start
        tvFinish.text = str_finish + "\n"

        btnlist.setOnClickListener {
            var intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            dbManager = DBManager(this, "guru2DB", null, 1)
            sqlitedb = dbManager.readableDatabase

            sqlitedb.execSQL("DELETE FROM GURU2Project WHERE date = '"+str_date+"' and name = '"+str_name+"';")
            sqlitedb.close()
            dbManager.close()

            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        btnMain.setOnClickListener {
            var intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}

