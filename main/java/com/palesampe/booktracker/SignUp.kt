package com.palesampe.booktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        setTitle("BOOK WORM")

        var edtName = findViewById<EditText>(R.id.nameTxt)
        var edtPassword = findViewById<EditText>(R.id.passwordTxt)
        var edtEmail = findViewById<EditText>(R.id.emailTxt)
        var edtPhone = findViewById<EditText>(R.id.phoneTxt)
        var btnSignUp = findViewById<Button>(R.id.signupBtn)

        var loginTxt = findViewById<TextView>(R.id.loginTxt)
        loginTxt.setOnClickListener() {
            intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
        btnSignUp.setOnClickListener() {
            if (edtName.text.toString().isNotEmpty() &&
                edtPassword.text.toString().isNotEmpty() &&
                edtEmail.text.toString().isNotEmpty() &&
                edtPhone.text.toString().isNotEmpty()
            ) {
                val user = UserModel(edtName.text.toString(), edtPassword.text.toString(),
                    edtEmail.text.toString(), edtPhone.text.toString(),0,0)
                val db = DatabaseHandler(this)
                val status = db.addUser(user)
                if (status > -1){
                    Toast.makeText(applicationContext, "Record saved", Toast.LENGTH_LONG).show()
                   // etName.text.clear()
                   // etEmailId.text.clear()
                }
                intent = Intent(this, LogIn::class.java)
                startActivity(intent)
                } else {
                Toast.makeText(this, "Fill in information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}