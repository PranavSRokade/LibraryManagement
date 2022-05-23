package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookInformation extends AppCompatActivity {
    TextView informationBookName, informationAuthor, descriptionOfBook;
    Button borrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);
        getSupportActionBar().hide();

        informationBookName = findViewById(R.id.informationBookName);
        informationAuthor = findViewById(R.id.informationAuthor);
        descriptionOfBook = findViewById(R.id.descriptionOfBook);
        borrow = findViewById(R.id.borrow);

        Intent intent = getIntent();
        informationBookName.setText(intent.getStringExtra("Book Name"));
        informationAuthor.setText("By " + intent.getStringExtra("Author"));
        descriptionOfBook.setText(intent.getStringExtra("Description"));

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BookInformation.this, "Borrowed Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}