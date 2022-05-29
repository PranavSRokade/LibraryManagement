package com.example.librarymanagement;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.EventLogTags;

import java.util.ArrayList;

public class DBHandlerBooks  extends SQLiteOpenHelper{

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "coursedb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "Books";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String BOOK_COL = "book_name";

    // below variable for our course description column.
    private static final String AUTHOR_COL = "author_name";

    private static final String DESCRIPTION_COL = "description";

//    private static final String BORROWED_COL = "borrowed_book";

    // creating a constructor for our database handler.
    public DBHandlerBooks(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOK_COL + " TEXT,"
                + DESCRIPTION_COL + "TEXT,"
//                + BORROWED_COL + "BOOL"
                + AUTHOR_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
//    public void addNewCourse(String courseName, String courseDuration, String courseDescription, String courseTracks) {
    public void addNewCourse(String BOOKName, String AuthorName, String Description , boolean Borrow) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(BOOK_COL, BOOKName);
        values.put(AUTHOR_COL, AuthorName);
        values.put(DESCRIPTION_COL,Description);
//        values.put(BORROWED_COL,Borrow);
        //courseDescription , courseName if not working


        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void onborrow(String book_name ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_NAME + " WHERE " +  BOOK_COL + "=\"" + book_name + "\";" );
//        String queryString = "SELECT 1 FROM " +TABLE_NAME+ " WHERE " + BOOK_COL + " = ?";
//        Cursor c = db.rawQuery(queryString, new String[]{book_name});
//        boolean result = c.getCount() > 0;
//        try{
//            while(c.moveToNext()){
//                String name= c.getString(c.getColumnIndex()) ;
//                if(book_name==name){
//                    db.delete(TABLE_NAME,  + "=" + book_name, null)
//                }
//            }
//        }
//        c.close();
        db.close();
        //return result
    }

    public void delete_all( ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

        db.close();
    }
}

