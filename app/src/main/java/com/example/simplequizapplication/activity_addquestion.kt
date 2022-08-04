package com.example.simplequizapplication



import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import com.example.simplequizapplication.model.DbHelper
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import com.example.simplequizapplication.R
import com.example.simplequizapplication.welcomeActivity

class activity_addquestion : AppCompatActivity() {
    lateinit var back: Button
    lateinit var add: Button
    lateinit var q: EditText
    lateinit var opt1: EditText
    lateinit  var opt2: EditText
    lateinit var opt3: EditText
    lateinit var opt4: EditText
    lateinit var ans: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addquestion)
        back = findViewById<Button>(R.id.btn_back)
        add = findViewById<Button>(R.id.btn_add)
        q = findViewById<EditText>(R.id.etQuesion)
        opt1 = findViewById<EditText>(R.id.txtOption1)
        opt2 = findViewById<EditText>(R.id.txtOption2)
        opt3 = findViewById<EditText>(R.id.txtOption3)
        opt4 = findViewById<EditText>(R.id.txtOption4)
        ans = findViewById<EditText>(R.id.txt_ans)
        val db = DbHelper(this)
        back.setOnClickListener {
            val intent = Intent(this@activity_addquestion, welcomeActivity::class.java)
            startActivity(intent)
        }
        add.setOnClickListener {
            val ques = q.text.toString()
            val option1 = opt1.text.toString()
            val option2 = opt2.text.toString()
            val option3 = opt3.text.toString()
            val option4 = opt4.text.toString()
            val answer = ans.text.toString()
            if (ques.isEmpty() || option1.isEmpty() || option2.isEmpty() ||
                option3.isEmpty() || option4.isEmpty() || answer.isEmpty()) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Please Fill missing Field",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                db.addQuestions(ques, option1, option2, option3, option4, answer)
                val toast = Toast.makeText(
                    applicationContext,
                    "Question is saved Successfully!!",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                emptyfield()
            }
        }
    }
    fun emptyfield()
    {
        q.text=null
        opt1.text=null
        opt2.text=null
        opt3.text=null
        opt4.text=null
        ans.text=null
    }
}