package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListOfBooks extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView addBook;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addBook = findViewById(R.id.addBook);

        BookDatabase bookDatabase = new BookDatabase(ListOfBooks.this);

        ArrayList<Book> bookList = bookDatabase.getAllBooks();

        adapter = new Adapter(this, bookList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), MODE_PRIVATE);

                if(sharedPreferences.getBoolean("login", false)) {
                    Intent intent = new Intent(ListOfBooks.this, AddBook.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(ListOfBooks.this, AdministratorLogin.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
}