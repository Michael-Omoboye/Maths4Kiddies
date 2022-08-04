package com.example.simplequizapplication.model

class Question {
    var iD = 0
    var qUESTION: String
    var oPTA: String
    var oPTB: String
    var oPTC: String
    var oPTD: String
    var aNSWER: String
    var ischk: String
    constructor() {
        iD = 0
        qUESTION = ""
        oPTA = ""
        oPTB = ""
        oPTC = ""
        oPTD = ""
        aNSWER = ""
        ischk=""
    }

    constructor(
        qUESTION: String, oPTA: String, oPTB: String, oPTC: String, oPTD: String,
        aNSWER: String
    ) {
        this.qUESTION = qUESTION
        this.oPTA = oPTA
        this.oPTB = oPTB
        this.oPTC = oPTC
        this.oPTD = oPTD
        this.aNSWER = aNSWER
        this.ischk=""
    }
    fun setchecked(check : String) {
        this.ischk=check
    }
    fun getchecked(): String
    {
        return this.ischk
    }
}