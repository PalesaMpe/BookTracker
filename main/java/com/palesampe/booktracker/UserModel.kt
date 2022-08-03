package com.palesampe.booktracker

class UserModel{
    var id: Int = 0
    var name: String = ""
    var password: String = ""
    var email: String = ""
    var phone: String = ""
    var yearGoal: Int = 0
    var achieved: Int = 0

    constructor(name: String, password: String, email: String, phone: String, yearGoal: Int, achieved: Int){
        this.name=name
        this.password = password
        this.email = email
        this.phone = phone
        this.yearGoal = yearGoal
        this.achieved = achieved
    }
    constructor(){
    }
}
