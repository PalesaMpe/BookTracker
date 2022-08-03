package com.palesampe.booktracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//val BookDB_NAME = "BookDatabase"
//val BookTBL_NAME = "BookTable"
//val COL_TITLE = "Title"
//val COL_AUTHOR = "Author"
//val COL_PAGE = "PageNumber"
//val COL_GENRE = "Genre"
//val COL_PUBLISHER = "Publisher"
//val COL_YEAR = "Year"
//val COL_ISBN = "ISBN"
//val COL_RATING = "UserRating"

//class BookDBHandler(var context: Context) :
 //   SQLiteOpenHelper(context, BookDB_NAME, null, 1) {
//    override fun onCreate(db: SQLiteDatabase?) {
//       val createTable = ("CREATE TABLE " + BookTBL_NAME + " (" +
//               COL_ISBN + " INTEGER," +
//               COL_TITLE + " TEXT," +
//               COL_AUTHOR + " TEXT," +
//               COL_PAGE + " TEXT," +
//               COL_GENRE + " TEXT," +
//               COL_PUBLISHER + " TEXT," +
//               COL_YEAR + " INTEGER," +
//               COL_RATING + " TEXT)")
//       db?.execSQL(createTable)
//    }


//    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
//        // db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
//        // onCreate(db)
//        TODO("not implemented")
//    }
//
//    fun addBook(book: BookModel) {
//        val db = this.writableDatabase
//        val values1 = ContentValues()
//        values1.put(COL_TITLE, book.title)
//        values1.put(COL_AUTHOR, book.author)
//        values1.put(COL_PAGE, book.pageNumber)
//        values1.put(COL_GENRE, book.genre)
//        values1.put(COL_PUBLISHER, book.publisher)
//        values1.put(COL_YEAR, book.yearPublished)
//        values1.put(COL_ISBN, book.ISBN)
//        // values.put(COL_RATING,book.userRating)
//        val result = db.insert(BookTBL_NAME, null, values1)
//        if (result == -1.toLong()) {
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(context, "Added Book", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    fun readBookData(): MutableList<BookModel> {
//        var list: MutableList<BookModel> = ArrayList()
//        val db = this.readableDatabase
//        val query = "SELECT * FROM $BookTBL_NAME"
//        val result = db.rawQuery(query, null)
//
//        if (result.moveToFirst()) {
//            do {
//                var book = BookModel()
//                book.title = result.getString(result.getColumnIndexOrThrow(COL_TITLE))
//                book.author = result.getString(result.getColumnIndexOrThrow(COL_AUTHOR))
//                book.pageNumber = result.getString(result.getColumnIndexOrThrow(COL_PAGE)).toInt()
//                book.genre = result.getString(result.getColumnIndexOrThrow(COL_GENRE))
//                book.publisher = result.getString(result.getColumnIndexOrThrow(COL_PUBLISHER))
//                book.yearPublished = result.getString(result.getColumnIndexOrThrow(COL_YEAR)).toInt()
//             //   book.ISBN = result.getString(result.getColumnIndexOrThrow(COL_ISBN)).toInt()
//                list.add(book)
//            } while (result.moveToNext())
//        }
//        result.close()
//        db.close()
//        return list
//    }
//    fun deleteBook(book: BookModel): Int {
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(COL_ISBN, book.ISBN) // EmpModelClass id
//        // Deleting Row
//        val success = db.delete(BookTBL_NAME, COL_ISBN + "=" + book.ISBN, null)
//
//        db.close()
//        return success
//    }
//    fun updateBook(book: BookModel): Int {
//        val db = this.writableDatabase
//        //var book = BookModel()
//        val contentValues = ContentValues()
//        contentValues.put(COL_TITLE, book.title)
//        contentValues.put(COL_AUTHOR, book.author)
//        contentValues.put(COL_PAGE, book.pageNumber)
//        contentValues.put(COL_GENRE, book.genre)
//        contentValues.put(COL_PUBLISHER, book.publisher)
//        contentValues.put(COL_YEAR, book.yearPublished)
//        val success = db.update(BookTBL_NAME, contentValues, COL_ISBN + "=" + book.ISBN, null)
//
//        db.close()
//        return success
//    }
//}
