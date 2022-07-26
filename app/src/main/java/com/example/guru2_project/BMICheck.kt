package com.example.guru2_project

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class BMICheck : AppCompatActivity() {

    lateinit var edtCm: EditText
    lateinit var edtKg: EditText
    lateinit var button: Button
    lateinit var progressBar: ProgressBar
    lateinit var tvNum: TextView
    lateinit var ivResult: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicheck)

        edtCm = findViewById(R.id.edtCm)
        edtKg = findViewById(R.id.edtKg)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        tvNum = findViewById(R.id.tvNum)
        ivResult = findViewById(R.id.ivResult)

        edtKg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                button.isEnabled = !(edtCm.length() <= 0 || edtKg.length() <= 0)
            }
        })

        button.setOnClickListener {
            var cm = edtCm.text.toString().toInt()
            var kg = edtKg.text.toString().toInt()
            var bmi = kg!! / Math.pow(cm!!/100.0, 2.0)

            when {
                bmi <= 45 -> progressBar.setProgress(bmi.toInt())
                else -> progressBar.setProgress(45)
            }

            val df = DecimalFormat("##00.00")
            var bmiResult = df.format(bmi)
            tvNum.text = bmiResult.toString()

            when {
                bmi >= 30 ->
                    ivResult.setImageResource(
                        R.drawable.bmi_result5
                    )
                bmi >= 25 ->
                    ivResult.setImageResource(
                        R.drawable.bmi_result4
                    )
                bmi >= 23 ->
                    ivResult.setImageResource(
                        R.drawable.bmi_result3
                    )
                bmi >= 18.5 ->
                    ivResult.setImageResource(
                        R.drawable.bmi_result2
                    )
                else ->
                    ivResult.setImageResource(
                        R.drawable.bmi_result1
                    )
            }
        }
    }
}