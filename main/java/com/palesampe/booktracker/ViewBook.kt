package com.palesampe.booktracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class ViewBook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book)

        setTitle("VIEW BOOK")
        var btnEdit = findViewById<Button>(R.id.btnEdit)
        var btnDelete = findViewById<Button>(R.id.btnDelete)
        var checkRead = findViewById<CheckBox>(R.id.checkDisplay)
        var titleDisplay = findViewById<TextView>(R.id.titleDisplay)
        var authorDisplay = findViewById<TextView>(R.id.authorDisplay)
        var genreDisplay = findViewById<TextView>(R.id.genreDisplay)
        var publisherDisplay = findViewById<TextView>(R.id.publisherDisplay)
        var pagesDisplay = findViewById<TextView>(R.id.pagesDisplay)
        var yearDisplay = findViewById<TextView>(R.id.yearDisplay)


        var db = DatabaseHandler(this)
        var data = db.readBookData()

        val bundle: Bundle? = intent.extras
        val bookIndex = bundle?.get("bookIndex") as Int
        Toast.makeText(this,bookIndex.toString(),Toast.LENGTH_SHORT).show()

        var count = 0
        checkRead.setOnClickListener{
            if (checkRead.isChecked){
                count += 1
                intent = Intent(this, Home::class.java)
                val extras = Bundle()
                extras.putInt("checkBooks",count)
                intent.putExtras(extras)
                startActivity(intent)
            }
        }

        titleDisplay.text = data[bookIndex].title
        genreDisplay.text =  data[bookIndex].genre
        authorDisplay.text = data[bookIndex].author
        publisherDisplay.text = data[bookIndex].publisher
        pagesDisplay.text = data[bookIndex].pageNumber.toString()
        yearDisplay.text = data[bookIndex].yearPublished.toString()

        btnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes") { dialog, id ->
                   // var db = BookDBHandler(this)
                    val status = db.deleteBook(data[bookIndex].ISBN)
                    if (status > -1){
                        Toast.makeText(this, "Book has been Removed", Toast.LENGTH_LONG).show()
                    }
                    intent = Intent(this, Home::class.java)
                    startActivity(intent)
                }
                .setNegativeButton("No") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
        btnEdit.setOnClickListener{

            intent = Intent(this, EditBook::class.java)
            intent.putExtra("BookIndex",bookIndex)
            startActivity(intent)
        }
    }

}