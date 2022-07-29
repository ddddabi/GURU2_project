package com.example.guru2_project

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton

class ExerciseReg : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var btn_reg: ImageButton
    lateinit var edtEDate: EditText
    lateinit var edtEName: EditText
    lateinit var edtStart: EditText
    lateinit var edtFinish: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_reg)

        btn_reg = findViewById(R.id.btn_reg)
        edtEDate = findViewById(R.id.edtMDate)
        edtEName = findViewById(R.id.edtMFood)
        edtStart = findViewById(R.id.edtMTime)
        edtFinish = findViewById(R.id.edtFinish)

        dbManager = DBManager(this, "guru2DB", null, 1)

        btn_reg.setOnClickListener {
            var str_date: String = edtEDate.text.toString()
            var str_name: String = edtEName.text.toString()
            var str_start: String = edtStart.text.toString()
            var str_finish: String = edtFinish.text.toString()

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO GURU2Project VALUES ('"+str_date+"','"+str_name+"','"+str_start+"','"+str_finish+"')")
            sqlitedb.close()

            val intent = Intent(this, ExerciseInfo::class.java)
            intent.putExtra("intent_date", str_date)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }
    }
}