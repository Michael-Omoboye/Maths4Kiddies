package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button

class HomeActivity : AppCompatActivity() {
    lateinit var btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btn = findViewById<Button>(R.id.btn_start)
        btn.setOnClickListener(View.OnClickListener {
            val usernameEditText = findViewById<View>(R.id.til_name) as EditText
            val sUsername = usernameEditText.text.toString()
            if (sUsername.isEmpty()) {
                val toast =
                    Toast.makeText(applicationContext, "Please enter your name", Toast.LENGTH_SHORT)
                toast.show()
                return@OnClickListener
            } else {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                val b = Bundle()
                b.putString("name", sUsername)
                intent.putExtras(b)
                startActivity(intent)
                finish()
            }
        })
    }
}