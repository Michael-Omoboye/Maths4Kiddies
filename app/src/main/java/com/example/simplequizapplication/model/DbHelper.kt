package com.example.simplequizapplication.model

import android.database.sqlite.SQLiteOpenHelper
import com.example.simplequizapplication.model.QuizContract.MovieEntry
import com.example.simplequizapplication.model.Users.UsersEntry
import android.content.ContentValues
import android.content.Context
import java.util.ArrayList
import android.database.sqlite.SQLiteDatabase as SQLiteDatabase1


    private  val ver: Int = 1
    // Database Name
    private  val DataBaseName = "triviaQuiz22.db"

class DbHelper(context: Context?) :
    SQLiteOpenHelper(context, DataBaseName, null, ver) {
    // tasks table name
   private lateinit var dbase : SQLiteDatabase1

    override fun onCreate(db: SQLiteDatabase1) {
        dbase = db
        val sql = ("CREATE TABLE IF NOT EXISTS " + MovieEntry.TABLE_QUEST + " ( "
                + MovieEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MovieEntry.KEY_QUES
                + " TEXT, " + MovieEntry.KEY_ANSWER + " TEXT, " + MovieEntry.KEY_OPTA + " TEXT, "
                + MovieEntry.KEY_OPTB + " TEXT, " + MovieEntry.KEY_OPTC + " TEXT," + MovieEntry.KEY_OPTD + " TEXT)")
        db.execSQL(sql)
        //addQuestions()

        val Usersql = ("CREATE TABLE IF NOT EXISTS " + UsersEntry.TABLE_USER + " ( "
                + UsersEntry.KEY_USERID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + UsersEntry.KEY_NAME
                + " TEXT, " + UsersEntry.KEY_SCORE + " TEXT)")
        db.execSQL(Usersql)
       //adduser()
        //db.close();
    }

    fun addQuestions(
        ques: String,
        option1: String,
        option2: String,
        option3: String,
        option4: String,
        answer: String
    ) {
        dbase = this.writableDatabase
        val question = Question(ques, option1, option2, option3, option4, answer)
        addQuestion(question)
    }

    fun addUsers(
        name: String,
        score: String,
    ) {
        dbase = this.writableDatabase

        val user = UsersData(name, score)
        addUser(user)
    }

   fun addQuestions() {

 }

    override fun onUpgrade(db: SQLiteDatabase1, p1: Int, p2: Int) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_QUEST)

        db.execSQL("DROP TABLE IF EXISTS " + UsersEntry.TABLE_USER)
        // Create tables again
        onCreate(db)
    }

    // Adding new question
    fun addQuestion(quest: Question) {
        //SQLiteDatabase1 db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(MovieEntry.KEY_QUES, quest.qUESTION)
        values.put(MovieEntry.KEY_ANSWER, quest.aNSWER)
        values.put(MovieEntry.KEY_OPTA, quest.oPTA)
        values.put(MovieEntry.KEY_OPTB, quest.oPTB)
        values.put(MovieEntry.KEY_OPTC, quest.oPTC)
        values.put(MovieEntry.KEY_OPTD, quest.oPTD)
        // Inserting Row
        dbase.insert(MovieEntry.TABLE_QUEST, null, values)
    }

    fun addUser(user: UsersData) {
        //SQLiteDatabase1 db = this.getWritableDatabase();
        val values = ContentValues()
        values.put(UsersEntry.KEY_NAME, user.pName)
        values.put(UsersEntry.KEY_SCORE, user.pScore)
        // Inserting Row
        dbase.insert(UsersEntry.TABLE_USER, null, values)
    }



    // looping through all rows and adding to list
    // return quest list
    fun allQuestions(): List<Question> {
        val quesList: MutableList<Question> = ArrayList()
        // Select All Query
        val selectQuery = "SELECT  * FROM " + MovieEntry.TABLE_QUEST
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val quest = Question()
                quest.iD = cursor.getInt(0)
                quest.qUESTION = cursor.getString(1)
                quest.aNSWER = cursor.getString(2)
                quest.oPTA = cursor.getString(3)
                quest.oPTB = cursor.getString(4)
                quest.oPTC = cursor.getString(5)
                quest.oPTD = cursor.getString(6)
                quesList.add(quest)
            } while (cursor.moveToNext())
        }
        // return quest list
        return quesList
    }

    fun rowcount(): Int {
        var row = 0
        val selectQuery = "SELECT  * FROM " + MovieEntry.TABLE_QUEST
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        row = cursor.count
        return row
    }

//    companion object {
//        private const val DATABASE_VERSION = 1
//        // Database Name
//        private const val DATABASE_NAME = "triviaQuiz22.db"
//    }

    fun getUser(name: String): List<UsersData> {
        val userdata: MutableList<UsersData> = ArrayList()
        // Select All Query
        val selectQuery = "SELECT  * FROM " + UsersEntry.TABLE_USER + " Where name= '$name'"
        dbase = this.readableDatabase
        val cursor = dbase.rawQuery(selectQuery, null)
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val user = UsersData()
                user.pName = cursor.getString(1)
                user.pScore = cursor.getString(2)
                userdata.add(user)
            } while (cursor.moveToNext())
        }
        // return user data
        return userdata
    }
}