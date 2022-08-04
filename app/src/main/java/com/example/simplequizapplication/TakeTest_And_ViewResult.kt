package com.example.simplequizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class TakeTest_And_ViewResult : AppCompatActivity() {
    lateinit var taketest: Button
    lateinit var viewresult: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_test_and_view_result)

        taketest = findViewById<Button>(R.id.btn_take_test)
        viewresult = findViewById<Button>(R.id.btn_view_result)

        taketest.setOnClickListener {
            val intent = Intent(this@TakeTest_And_ViewResult, HomeActivity::class.java)
            startActivity(intent)
        }
        viewresult.setOnClickListener {
            val intent = Intent(this@TakeTest_And_ViewResult, ViewResult::class.java)
            startActivity(intent)
        }
    }
}