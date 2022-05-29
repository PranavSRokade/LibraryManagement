package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookInformation extends AppCompatActivity {
    TextView informationBookName, informationAuthor, descriptionOfBook;
    ImageView bookImage;
    Button borrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_information);
        getSupportActionBar().hide();

        informationBookName = findViewById(R.id.informationBookName);
        informationAuthor = findViewById(R.id.informationAuthor);
        descriptionOfBook = findViewById(R.id.descriptionOfBook);
        bookImage = findViewById(R.id.bookImage);
        borrow = findViewById(R.id.borrow);

        Intent intent = getIntent();
        informationBookName.setText(intent.getStringExtra("Book Name"));
        informationAuthor.setText("By " + intent.getStringExtra("Author"));
        descriptionOfBook.setText(intent.getStringExtra("Description"));

        byte[] bytesImage = intent.getByteArrayExtra("Image");
        Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytesImage, 0, bytesImage.length);
        bookImage.setImageBitmap(bitmapImage);

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDatabase bookDatabase = new BookDatabase(BookInformation.this);

                bookDatabase.borrow(intent.getStringExtra("Book Name"));

                Toast.makeText(BookInformation.this, "Borrowed Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(BookInformation.this, ListOfBooks.class));
                finish();
            }
        });
    }
}