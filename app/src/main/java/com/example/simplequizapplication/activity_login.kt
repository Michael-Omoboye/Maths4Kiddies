package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.view.View
import android.widget.Button


class activity_login : AppCompatActivity() {
    var login: Button? = null
    var usernameEditText: EditText? = null
    var pass: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById<View>(R.id.btn_login) as Button
        usernameEditText = findViewById<View>(R.id.user_name) as EditText
        pass = findViewById<View>(R.id.pass) as EditText
        login!!.setOnClickListener {
            val sUsername = usernameEditText!!.text.toString()
            val password = pass!!.text.toString()
            if (sUsername.isEmpty() || password.isEmpty()) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Please enter missing name or password",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
            else if(sUsername != "Admin" && password != "Admin123")
            {
                val toast = Toast.makeText(
                    applicationContext,
                    "Username or Password is incorrect.",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
            else {
                val intent = Intent(this@activity_login, activity_addquestion::class.java)
                startActivity(intent)
            }
        }
    }
}

