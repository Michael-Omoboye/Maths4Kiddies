package com.example.simplequizapplication.model

import android.provider.BaseColumns

class Users {
    object UsersEntry : BaseColumns {
        const val TABLE_USER = "UsersProfile"
        // tasks Table Columns names
        const val KEY_USERID = "userid"
        const val KEY_NAME = "name"
        const val KEY_SCORE = "score"
    }
}

