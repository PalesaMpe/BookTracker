package com.palesampe.booktracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object{
        val DATABASE_NAME = "Database"
        val UserTBL_NAME = "UserTable"
        val BookTBL_NAME = "BookTable"

        val COL_ID = "UserID"
        val COL_NAME = "Name"
        val COL_PASSWORD = "Password"
        val COL_EMAIL = "Email"
        val COL_PHONE = "Phone"
        val COL_GOAL = "Goal"
        val COL_ACHIEVED = "Achieved"


        val COL_TITLE = "Title"
        val COL_AUTHOR = "Author"
        val COL_PAGE = "PageNumber"
        val COL_GENRE = "Genre"
        val COL_PUBLISHER = "Publisher"
        val COL_YEAR = "Year"
        val COL_ISBN = "ISBN"
        val COL_RATING = "UserRating"
        val COL_READ = "Read"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_BOOK_TABLE = ("CREATE TABLE " + BookTBL_NAME + " (" +
                COL_ID + " INTEGER," +
                COL_ISBN + " TEXT," +
                COL_TITLE + " TEXT," +
                COL_AUTHOR + " TEXT," +
                COL_PAGE + " TEXT," +
                COL_GENRE + " TEXT," +
                COL_PUBLISHER + " TEXT," +
                COL_YEAR + " INTEGER," +
                COL_RATING + " TEXT,"+
                COL_READ + " INTEGER)")
        db?.execSQL(CREATE_BOOK_TABLE)

        val CREATE_USER_TABLE = ("CREATE TABLE " + UserTBL_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_NAME + " TEXT," +
                COL_PASSWORD + " TEXT," +
                COL_EMAIL + " TEXT," +
                COL_PHONE + " TEXT," +
                COL_GOAL + " INTEGER," +
                COL_ACHIEVED + " INTEGER)")
        db?.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $UserTBL_NAME")
        db!!.execSQL("DROP TABLE IF EXISTS $BookTBL_NAME")
        onCreate(db)
    }

    fun readUserData(): MutableList<UserModel> {
        var list: MutableList<UserModel> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT * FROM $UserTBL_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var user = UserModel()
                user.id = result.getString(result.getColumnIndexOrThrow(COL_ID)).toInt()
                user.name = result.getString(result.getColumnIndexOrThrow(COL_NAME))
                user.password = result.getString(result.getColumnIndexOrThrow(COL_PASSWORD))
                user.email = result.getString(result.getColumnIndexOrThrow(COL_EMAIL))
                user.phone = result.getString(result.getColumnIndexOrThrow(COL_PHONE))
                user.yearGoal = result.getString(result.getColumnIndexOrThrow(COL_GOAL)).toInt()
                user.achieved = result.getString(result.getColumnIndexOrThrow(COL_ACHIEVED)).toInt()
                list.add(user)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun readBookData(): MutableList<BookModel> {
        val list: MutableList<BookModel> = ArrayList()
        val db = this.readableDatabase
        val query = "SELECT  * FROM $BookTBL_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                var book = BookModel()
                book.title = result.getString(result.getColumnIndexOrThrow(COL_TITLE))
                book.author = result.getString(result.getColumnIndexOrThrow(COL_AUTHOR))
                book.pageNumber = result.getString(result.getColumnIndexOrThrow(COL_PAGE)).toInt()
                book.genre = result.getString(result.getColumnIndexOrThrow(COL_GENRE))
                book.publisher = result.getString(result.getColumnIndexOrThrow(COL_PUBLISHER))
                book.yearPublished = result.getString(result.getColumnIndexOrThrow(COL_YEAR)).toInt()
                book.ISBN = result.getString(result.getColumnIndexOrThrow(COL_ISBN))
                book.read = result.getString(result.getColumnIndexOrThrow(COL_READ)).toInt()
                list.add(book)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }
    fun addUser(user: UserModel): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, user.name)
        values.put(COL_PASSWORD, user.password)
        values.put(COL_EMAIL, user.email)
        values.put(COL_PHONE, user.phone)
        values.put(COL_GOAL, user.yearGoal)
        values.put(COL_ACHIEVED, user.achieved)

        return db.insert(UserTBL_NAME, null, values)
    }
    fun addBook(book: BookModel): Long {
        val db = this.writableDatabase
        val values1 = ContentValues()
        values1.put(COL_ID, book.userId)
        values1.put(COL_TITLE, book.title)
        values1.put(COL_AUTHOR, book.author)
        values1.put(COL_PAGE, book.pageNumber)
        values1.put(COL_GENRE, book.genre)
        values1.put(COL_PUBLISHER, book.publisher)
        values1.put(COL_YEAR, book.yearPublished)
        values1.put(COL_ISBN, book.ISBN)
        values1.put(COL_READ, book.read)
        // values.put(COL_RATING,book.userRating)
        return db.insert(BookTBL_NAME, null, values1)
    }

    fun deleteUser(id: Int): Int {
        val db = this.writableDatabase
        var user= UserModel()
        val contentValues = ContentValues()
        contentValues.put(COL_ID, user.id)
        val success = db.delete(UserTBL_NAME, COL_ID + "=?", arrayOf(id.toString()))
        db.close()
        return success
    }
    fun deleteBook(id:String): Int {
        val db = this.writableDatabase
        val success = db.delete(BookTBL_NAME, COL_ISBN + "=?", arrayOf(id))
        db.close()
        return success
    }
    fun updateUser(id: Int,user: UserModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, user.name)
        contentValues.put(COL_EMAIL, user.email)
        contentValues.put(COL_PHONE, user.phone)
        contentValues.put(COL_PASSWORD, user.password)
        contentValues.put(COL_GOAL, user.yearGoal)
        contentValues.put(COL_ACHIEVED, user.achieved)
        var success = db.update(UserTBL_NAME, contentValues, COL_ID + "=?", arrayOf(id.toString()))

        db.close()
        return success
    }
    fun updateBook(book: BookModel): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TITLE, book.title)
        contentValues.put(COL_AUTHOR, book.author)
        contentValues.put(COL_PAGE, book.pageNumber)
        contentValues.put(COL_GENRE, book.genre)
        contentValues.put(COL_PUBLISHER, book.publisher)
        contentValues.put(COL_YEAR, book.yearPublished)
        contentValues.put(COL_READ, book.read)
        var success = db.update(BookTBL_NAME, contentValues, COL_ISBN + "=?", arrayOf(book.ISBN))

        db.close()
        return success
    }



}

