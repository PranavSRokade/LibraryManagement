package com.example.librarymanagement;

public class Book {
    String bookName, author, description;
    byte[] image;

    public Book() {
    }

    public Book(String bookName, String author, String description, byte[] image) {
        this.bookName = bookName;
        this.author = author;
        this.description = description;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
