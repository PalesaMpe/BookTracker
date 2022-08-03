package com.palesampe.booktracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class LogIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setTitle("BOOK WORM")
        var signupTxt = findViewById<TextView>(R.id.signupTxt)
        var loginBtn = findViewById<Button>(R.id.signupBtn)
        var edtName = findViewById<EditText>(R.id.edtName)
        var edtPassword = findViewById<EditText>(R.id.edtPassword)
        var name = edtName.text
        var password = edtPassword.text
        var index = -1

        var db = DatabaseHandler(this)
        var userData = db.readUserData()

        signupTxt.setOnClickListener() {
            intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        loginBtn.setOnClickListener() {
            if (name.toString().isNotEmpty() && password.toString().isNotEmpty()) {
                for (i in 0..(userData.size-1)){
                    if ((name.toString() == userData[i].name) && (password.toString() == userData[i].password)) {
                        index = i
                    }
                }
                if (index>-1){
                    intent = Intent(this, Home::class.java)
                    intent.putExtra("UserIndex",index)
                    startActivity(intent)
                    name.clear()
                    password.clear()
                } else{
                    Toast.makeText(this, "Invalid user details", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "FILL IN INFORMATION", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



