package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simplequizapplication.R
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.simplequizapplication.activity_login
import com.example.simplequizapplication.HomeActivity

class welcomeActivity : AppCompatActivity() {
   lateinit var admin: Button
   lateinit var user: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_welcome)
        admin = findViewById<Button>(R.id.btn_admin)
        user = findViewById<Button>(R.id.btn_user)
        admin.setOnClickListener {
            val intent = Intent(this@welcomeActivity, activity_login::class.java)
            startActivity(intent)
        }
        user.setOnClickListener {
            val intent = Intent(this@welcomeActivity, TakeTest_And_ViewResult::class.java)
            startActivity(intent)
        }
    }
}