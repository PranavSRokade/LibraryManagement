package com.example.librarymanagement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;
import android.widget.Toast;

import java.util.ArrayList;

public class BookDatabase extends SQLiteOpenHelper{

    private static final String DB_NAME = "bookDatabase";
    private static final int DB_VERSION = 2;
    private static final String TABLE_NAME = "books";
    private static final String ID = "id";
    private static final String BOOK_NAME = "bookName";
    private static final String AUTHOR = "author";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";

    Context context;

    public BookDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOK_NAME + " TEXT,"
                + AUTHOR + " TEXT,"
                + DESCRIPTION + " TEXT,"
                + IMAGE + " BLOB)";
        db.execSQL(query);
    }

    public void addNewBook(Book book) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(BOOK_NAME, book.getBookName());
        values.put(AUTHOR, book.getAuthor());
        values.put(DESCRIPTION, book.getDescription());
        values.put(IMAGE, book.getImage());

        database.insert(TABLE_NAME, null, values);

        database.close();
    }

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> bookList = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String select = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Book book = new Book();

                book.setBookName(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setDescription(cursor.getString(3));
                book.setImage(cursor.getBlob(4));

                bookList.add(book);
            }
            while (cursor.moveToNext());
        }

        return bookList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public void borrow(String name){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " +  BOOK_NAME + "=\"" + name + "\";" );

        database.close();
    }

    public void deleteAllBooks( ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

        db.close();
    }
}

