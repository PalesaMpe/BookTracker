package com.palesampe.booktracker

class BookModel{
    var title: String = ""
    var author: String = ""
    var pageNumber: Int = 0
    var genre: String = ""
    var publisher: String = ""
    var yearPublished: Int = 0
    var ISBN: String = ""
    var read: Int = 0
    var userId: Int = 0
    constructor(userId:Int, ISBN: String,title: String, author: String, pageNumber: Int,
                genre: String, publisher: String,
                yearPublished: Int, read: Int ){
        this.userId = userId
        this.title = title
        this.author = author
        this.pageNumber = pageNumber
        this.genre = genre
        this.publisher = publisher
        this.yearPublished = yearPublished
        this.ISBN = ISBN
        this.read = read
    }
    constructor(){

    }
}