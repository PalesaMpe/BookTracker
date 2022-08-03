package com.palesampe.booktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class EditBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        setTitle("EDIT BOOK")
        var edtTitle = findViewById<EditText>(R.id.edtTitle)
        var edtAuthor = findViewById<EditText>(R.id.edtAuthor)
        var edtPage = findViewById<EditText>(R.id.edtPageNumber)
        var edtGenre = findViewById<EditText>(R.id.edtGenre)
        var edtPublisher = findViewById<EditText>(R.id.edtPublisher)
        var edtYear = findViewById<EditText>(R.id.edtYear)
        var btnUpdate = findViewById<Button>(R.id.btnUpdate)
        var checkRead = findViewById<CheckBox>(R.id.checkRead)

        val bundle: Bundle? = intent.extras
        val db = DatabaseHandler(this)
        var bookData = db.readBookData()
        var check = 0

        var index = bundle?.getInt("BookIndex") as Int
       // Toast.makeText(this, index.toString(), Toast.LENGTH_LONG).show()

        edtTitle.setText(bookData[index].title)
        edtAuthor.setText(bookData[index].author)
        edtPage.setText(bookData[index].pageNumber.toString())
        edtGenre.setText(bookData[index].genre)
        edtPublisher.setText(bookData[index].publisher)
        edtYear.setText(bookData[index].yearPublished.toString())

        checkRead.setOnClickListener{
            if (checkRead.isChecked){
                check = 1
            } else {
                check = 0
            }
        }

      btnUpdate.setOnClickListener{
          var status =
               db.updateBook(BookModel(1,bookData[index].ISBN,edtTitle.text.toString(), edtAuthor.text.toString(),edtPage.text.toString().toInt(),
                   edtGenre.text.toString(),edtPublisher.text.toString(),edtYear.text.toString().toInt(),1))
          Toast.makeText(applicationContext, status.toString(), Toast.LENGTH_LONG).show()
          if (status > -1) {
              Toast.makeText(applicationContext, "Record Updated.", Toast.LENGTH_LONG).show()
           }else{
               Toast.makeText(applicationContext, "Failed update.", Toast.LENGTH_LONG).show()
           }
          intent = Intent(this, Home::class.java)
          startActivity(intent)
       }

    }







}