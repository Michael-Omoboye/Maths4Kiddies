package com.example.simplequizapplication

import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.RequiresApi
import android.os.Build
import android.os.Bundle
import android.widget.RatingBar
import android.widget.TextView
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.simplequizapplication.model.DbHelper
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ResultActivity : AppCompatActivity() {
    lateinit var dtf: DateTimeFormatter
  lateinit  var now: LocalDateTime
    lateinit var mainscreen: Button
    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val db = DbHelper(this)
        //get rating bar object
        val bar = findViewById<RatingBar>(R.id.ratingBar1)
        //get text view
        val t = findViewById<TextView>(R.id.textResult)
        //get score
        val b = intent.extras
        val score = b!!.getInt("score")
        val name = b.getString("name")


        //display score
        bar.rating = score.toFloat() * 0.41f
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            now = LocalDateTime.now()
        }


        val message = """
            Name:  $name
            QuizDate: ${dtf.format(now)}
            Remarks: 
            """.trimIndent()
        when (score) {
            0 -> t.text = message + "You scored 0%, keep learning"
            1 -> t.text = message + "You have 8.33%, keep learning"
            2 -> t.text = message + "You have 16.66%, keep learning"
            3 -> t.text = message + "You have 25%, keep learning and keep your head up!"
            4 -> t.text = message + "You have 33.33%"
            5 -> t.text = "$message Whao, you have 41.65%"
            6 -> t.text = "$message Whao, you have 50% "
            7 -> t.text = "$message Whao, you have 58.33%, Who are you? An Math Jet brain"
            8 -> t.text = message + "You have 66.66%, keep learning"
            9 -> t.text = message + "You have 75%"
            10 -> t.text = "$message Whao, you have 83.33%"
            11 -> t.text = "$message Whao, you have 91.36% "
            12 -> t.text = "$message Whao, you have 100%, Who are you? Now your smart"
        }

        mainscreen = findViewById<View>(R.id.btn_main_screen) as Button

        mainscreen.setOnClickListener {
            val intent = Intent(this@ResultActivity, welcomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_result, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            val settingsIntent = Intent(this, MainActivity::class.java)
            startActivity(settingsIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}