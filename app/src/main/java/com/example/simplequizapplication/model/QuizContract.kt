package com.example.simplequizapplication.model

import android.provider.BaseColumns

class QuizContract {
    object MovieEntry : BaseColumns {
        const val TABLE_QUEST = "quest"

        // tasks Table Columns names
        const val KEY_ID = "id"
        const val KEY_QUES = "question"
        const val KEY_ANSWER = "answer" //correct option
        const val KEY_OPTA = "opta" //option a
        const val KEY_OPTB = "optb" //option b
        const val KEY_OPTC = "optc" //option c
        const val KEY_OPTD = "optd" //option c
    }
}