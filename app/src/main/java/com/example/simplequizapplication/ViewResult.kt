package com.example.simplequizapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.simplequizapplication.model.DbHelper
import com.example.simplequizapplication.model.UsersData

class ViewResult : AppCompatActivity() {
   lateinit var viewresult: Button
    lateinit var userList: List<UsersData>
    lateinit var usernameEditText :EditText
   lateinit var txtResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_result)
        usernameEditText= findViewById<EditText>(R.id.user_name)
        viewresult = findViewById<Button>(R.id.btn_viewresult)
        txtResult = findViewById<Button>(R.id.txtResult)
        val db = DbHelper(this)

        viewresult.setOnClickListener {

            var sUsername = usernameEditText.text
            userList = db.getUser(sUsername.toString())
            if (!userList.isEmpty()){
                val name = userList.get(0).pName
                val score = userList.get(0).pScore

                Log.d("name ", name)
                Log.d("score ", score)
                val message = "Name : $name  Score : $score"

                txtResult.text = message
            }
            else{
                txtResult.text = "Record Not Found"
            }
        }
    }
}