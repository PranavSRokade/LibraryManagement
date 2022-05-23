package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class ListOfBooks extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView addBook;
    ArrayList<Book> bookList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addBook = findViewById(R.id.addBook);

        bookList = new ArrayList<>();

        bookList.add(new Book("Book 1", "Author 1", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 2", "Author 2", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 3", "Author 3", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 4", "Author 4", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 5", "Author 5", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 6", "Author 6", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 7", "Author 7", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 8", "Author 8", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 9", "Author 9", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));
        bookList.add(new Book("Book 10", "Author 10", "Swimming brings back a nightmare that Rebecca Fishburn would much rather forget. So when the gym teacher announces plans to take the class swimming for the next two weeks, Reb can think of nothing else…and she is terrified! She must do whatever it takes to stay out of the water.Reb is convinced that she must lie…or she just might drown!"));

        adapter = new Adapter(this, bookList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListOfBooks.this, AdministratorLogin.class);
                startActivity(intent);
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