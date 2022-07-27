package com.example.guru2_project

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ExerciseReg : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var btnRegister: Button
    lateinit var edtEDate: EditText
    lateinit var edtEName: EditText
    lateinit var edtStart: EditText
    lateinit var edtFinish: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_reg)

        btnRegister = findViewById(R.id.btnRegister)
        edtEDate = findViewById(R.id.edtEDate)
        edtEName = findViewById(R.id.edtEName)
        edtStart = findViewById(R.id.edtStart)
        edtFinish = findViewById(R.id.edtFinish)


        dbManager = DBManager(this, "guruDB", null, 1)

        btnRegister.setOnClickListener {
            var str_name: String = edtEName.text.toString()
            var str_date: String = edtEDate.text.toString()
            var str_start: String = edtStart.text.toString()
            var str_finish: String = edtFinish.text.toString()

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO GURU2Project VALUES ('" + str_name + "','" + str_date + "','" + str_start + "','" + str_finish + "')")
            sqlitedb.close()

            val intent = Intent(this, ExerciseInfo::class.java)
            intent.putExtra("intent_name", str_name)
            startActivity(intent)
        }
    }
}