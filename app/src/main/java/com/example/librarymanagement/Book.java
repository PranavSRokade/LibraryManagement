package com.example.librarymanagement;

import android.widget.ImageView;

public class Book {
    String bookName;
    String author;
    String description;

    public Book(String bookName, String author, String description) {
        this.bookName = bookName;
        this.author = author;
        this.description = description;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
