package com.palesampe.booktracker

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*

class Profile : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("USER PROFILE")
        setContentView(R.layout.activity_profile)
        val bundle: Bundle? = intent?.extras
        var index = bundle?.getInt("UserIndex") as Int
        Toast.makeText(this, index.toString(), Toast.LENGTH_LONG).show()

        var nameText = findViewById<TextView>(R.id.nameText)
        var emailText = findViewById<TextView>(R.id.emailText)
        var phoneText = findViewById<TextView>(R.id.phoneText)
        var passwordText = findViewById<EditText>(R.id.passwordText)
        var nameIcon = findViewById<ImageView>(R.id.nameEdit)
        var emailIcon = findViewById<ImageView>(R.id.emailEdit)
        var phoneIcon = findViewById<ImageView>(R.id.phoneEdit)
        var passwordIcon = findViewById<ImageView>(R.id.passwordEdit)
        var eyeIcon = findViewById<ImageView>(R.id.EyeIcon)

        var btnSave = findViewById<Button>(R.id.btnSave)
        var btnDelete = findViewById<Button>(R.id.btnDeleteAccount)

        var db = DatabaseHandler(this)
        var userData = db.readUserData()

        nameText.text = userData[index].name
        emailText.text = userData[index].email
        phoneText.text = userData[index].phone
        passwordText.setText(userData[index].password)

        btnDelete.setOnClickListener {
            val saveUpdate = AlertDialog.Builder(this)
            saveUpdate.setTitle("CONFIRM?")
            saveUpdate.setMessage("Are you sure you would like to permanently delete your account?")
            saveUpdate.setPositiveButton("Yes") { dialog, id ->
                var status = db.deleteUser(2)
                if (status > -1) {
                    Toast.makeText(this, "ACCOUNT DELETED", Toast.LENGTH_SHORT).show()
                    intent = Intent(this, LogIn::class.java)
                    startActivity(intent)
                }
            }
                .setNegativeButton("NO", { dialog, id -> dialog.dismiss() })
            val alert = saveUpdate.create()
            alert.show()
        }

        btnSave.setOnClickListener {
            val saveUpdate = AlertDialog.Builder(this)
            saveUpdate.setTitle("Are you sure?")
            saveUpdate.setMessage("Are you sure you would like to change user information?")
            saveUpdate.setPositiveButton("Yes") { dialog, id ->
                db.updateUser(
                    index + 1, UserModel(
                        nameText.text.toString(), passwordText.toString(),
                        emailText.text.toString(), phoneText.text.toString(),
                        userData[index].yearGoal, userData[index].achieved
                    )
                )
                Toast.makeText(this, "USER ACCOUNT UPDATED", Toast.LENGTH_SHORT).show()
                intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
                .setNegativeButton("NO", { dialog, id -> dialog.dismiss() })
            val alert = saveUpdate.create()
            alert.show()
        }
        nameIcon.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("NAME")

            val input = EditText(this)
            input.setHint("Enter new username")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                var name = input.text.toString()
                //calling the deleteEmployee method of DatabaseHandler class to delete record

                //    val status = dbUser.updateUser(index,UserModel(userData[index].name,userData[index].password,
                //        userData[index].email,userData[index].email,20, userData[index].achieved))

                nameText.text = name

            })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }
        emailIcon.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("EMAIL")
            val input = EditText(this)
            input.setHint("Enter new email")
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                var email = input.text.toString()
                emailText.text = email
            })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }
        phoneIcon.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("PHONE NUMBER")
            val input = EditText(this)
            input.setHint("Enter new phone number")
            input.inputType = InputType.TYPE_CLASS_PHONE
            builder.setView(input)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                var phone = input.text.toString()
                phoneText.text = phone
            })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }
        passwordIcon.setOnClickListener {
            val builder: AlertDialog.Builder = android.app.AlertDialog.Builder(this)
            builder.setTitle("PASSWORD")
            val input = EditText(this)
            input.setHint("Enter new password")
            input.inputType = InputType.TYPE_MASK_CLASS
            builder.setView(input)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                var password = input.text.toString()
                passwordText.setText(password)
            })
            builder.setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
            builder.show()
        }

        eyeIcon.setOnClickListener {
            passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)

           // passwordText.setImageDrawable(getResources().getDrawable(R.id.emailEdit))
        }




    }
}