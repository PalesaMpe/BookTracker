package com.palesampe.booktracker

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*


class addBook : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        setTitle("ADD BOOK")

        val bundle: Bundle? = intent?.extras
        var index = bundle?.get("UserIndex") as Int
        Toast.makeText(this, index.toString(), Toast.LENGTH_SHORT).show()

        var edtTitle = findViewById<EditText>(R.id.edtTitle)
        var edtAuthor = findViewById<EditText>(R.id.edtAuthor)
        var edtPage = findViewById<EditText>(R.id.edtPageNumber)
        var edtGenre = findViewById<EditText>(R.id.edtGenre)
        var edtPublisher = findViewById<EditText>(R.id.edtPublisher)
        var edtYear = findViewById<EditText>(R.id.edtYear)
        var edtISBN = findViewById<EditText>(R.id.edtISBN)
        var ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        var btnAdd = findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener() {
            if ((edtTitle.text.toString().isNotEmpty() && edtAuthor.text.toString().isNotEmpty() &&
                edtPage.text.toString().isNotEmpty() && edtGenre.text.toString().isNotEmpty()) &&
                (edtPublisher.text.toString().isNotEmpty() && edtYear.text.toString().isNotEmpty() &&
                edtISBN.text.toString().isNotEmpty() && ratingBar.rating.toString().isNotEmpty())
            ) {
                val db = DatabaseHandler(this)
                db.addBook(BookModel(1,edtISBN.text.toString(), edtTitle.text.toString(), edtAuthor.text.toString(),
                    edtPage.text.toString().toInt(), edtGenre.text.toString(),
                    edtPublisher.text.toString(), edtYear.text.toString().toInt(), 0))
                intent = Intent(this, Home::class.java)
               startActivity(intent)
            } else {
                Toast.makeText(this, "Fill in information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}