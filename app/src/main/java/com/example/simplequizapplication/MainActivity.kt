package com.example.simplequizapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.simplequizapplication.model.DbHelper
import com.example.simplequizapplication.model.Question
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var quesList: List<Question>
    var score = 0
    var qid = 0
    var count=0

    //var Progress = 0
   lateinit var currentQ: Question
    lateinit var previousQ: Question
    lateinit var txtQuestion: TextView
    lateinit var rda: RadioButton
    lateinit var rdb: RadioButton
   lateinit var rdc: RadioButton
   lateinit var rdd: RadioButton
    lateinit var butNext: Button
    lateinit var butPre: Button
   lateinit var simpleProgressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = DbHelper(this)
        quesList = db.allQuestions()
        Collections.shuffle(quesList)
        currentQ = quesList[qid]
        txtQuestion = findViewById<TextView>(R.id.textView1)
        rda = findViewById<RadioButton>(R.id.radio0)
        rdb = findViewById<RadioButton>(R.id.radio1)
        rdc = findViewById<RadioButton>(R.id.radio2)
        rdd = findViewById<RadioButton>(R.id.radio3)
        butNext = findViewById<Button>(R.id.buttonNext)
        butPre = findViewById<Button>(R.id.buttonPrevious)
        butPre.isEnabled = false
        simpleProgressBar = findViewById<ProgressBar>(R.id.progressBar)
        simpleProgressBar.visibility = View.VISIBLE
        setQuestionView()
        butNext.setOnClickListener {

            qid++
            val grp = findViewById<View>(R.id.radioGroup1) as RadioGroup
            val flag =grp.checkedRadioButtonId

            Log.d("Total Rows", flag.toString())

            if (flag ==-1) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Please select atleast one option",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val answer = findViewById<View>(grp.checkedRadioButtonId) as RadioButton
                grp.clearCheck()
                count++
                simpleProgressBar.incrementProgressBy(1)
                Log.d("myanswer", answer.text.toString())
                if (currentQ.aNSWER == answer.text.toString()) {
                    score++
                    Log.d("score", "Your score$score")
                }
                if (qid < 11) {
                    quesList[qid-1].setchecked(answer.text.toString())
                    currentQ = quesList[qid]
                    setNextQuestionView()
                } else {
                    val intent = Intent(this@MainActivity, ResultActivity::class.java)
                    val b = Bundle()
                    val b1 = getIntent().extras
                    Log.d("checkscore",score.toString())
                    b.putInt("score", score) //Your score
                    b.putString("name", b1!!.getString("name"))

                    db.addUsers(b1!!.getString("name").toString(), score.toString())

                    intent.putExtras(b) //Put your score to your next Intent
                    startActivity(intent)
                    finish()
                }

            }
        }
        butPre.setOnClickListener {
            qid--
            Log.d("Checked",quesList[0].getchecked())
            this.previousQ=quesList[qid]
            Log.d("TAG",qid.toString())

            setPreQuestionView()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_quiz, menu)
        return true
    }

    private fun setQuestionView() {
        txtQuestion.text = currentQ.qUESTION
        rda.text = currentQ.oPTA
        rdb.text = currentQ.oPTB
        rdc.text = currentQ.oPTC
        rdd.text = currentQ.oPTD
    }
    private fun setNextQuestionView() {

        txtQuestion.text = currentQ.qUESTION
        rda.text = currentQ.oPTA
        rdb.text = currentQ.oPTB
        rdc.text = currentQ.oPTC
        rdd.text = currentQ.oPTD
        if(rda.text==currentQ.getchecked())
            rda.isChecked=true
        else  if(rdb.text==currentQ.getchecked())
            rdb.isChecked=true
        else if(rdc.text==currentQ.getchecked())
            rdc.isChecked=true
        else if(rdd.text==currentQ.getchecked())
            rdd.isChecked=true
        if(qid>0)
            butPre.isEnabled =true

        else if (qid == 11)
            butNext.isEnabled = false

    }
    private fun setPreQuestionView() {
        txtQuestion.text = previousQ.qUESTION
        rda.text = previousQ.oPTA
        rdb.text = previousQ.oPTB
        rdc.text = previousQ.oPTC
        rdd.text = previousQ.oPTD
        if(rda.text==previousQ.getchecked())
            rda.isChecked=true
       else  if(rdb.text==previousQ.getchecked())
            rdb.isChecked=true
       else if(rdc.text==previousQ.getchecked())
            rdc.isChecked=true
        else if(rdd.text==previousQ.getchecked())
            rdd.isChecked=true

        simpleProgressBar.progress=--count
        if(qid==0)
            butPre.isEnabled =false

    }
}