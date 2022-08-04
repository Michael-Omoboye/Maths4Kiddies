package com.example.simplequizapplication.model

class UsersData {
        var userID = 0
        var pName: String
        var pScore: String

    constructor() {
        userID = 0
        pName = ""
        pScore = ""
    }

    constructor(
        pName: String, pScore: String
    ) {
        this.pName = pName
        this.pScore = pScore
    }
}