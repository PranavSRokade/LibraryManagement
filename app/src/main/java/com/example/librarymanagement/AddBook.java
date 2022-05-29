package com.example.librarymanagement;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddBook extends AppCompatActivity {
    Button add;
    EditText addBookName, addAuthor, addDescription;
    ImageView addImage, image;
    byte[] imageBytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        add = findViewById(R.id.add);
        addBookName = findViewById(R.id.addBookName);
        addAuthor = findViewById(R.id.addAuthor);
        addDescription = findViewById(R.id.addDescription);
        addImage = findViewById(R.id.addImage);
        image = findViewById(R.id.image);

        image.setVisibility(View.INVISIBLE);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddBook.this)
                        .galleryOnly()
                        .maxResultSize(512, 512)
                        .start();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookDatabase bookDatabase = new BookDatabase(AddBook.this);

                bookDatabase.addNewBook(new Book(String.valueOf(addBookName.getText()), String.valueOf(addAuthor.getText()), String.valueOf(addDescription.getText()), imageBytes));

                Toast.makeText(AddBook.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(AddBook.this, ListOfBooks.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmapImage = null;

        image.setVisibility(View.VISIBLE);
        addImage.setVisibility(View.INVISIBLE);

        Uri uri = data.getData();
        image.setImageURI(uri);

        try {
            bitmapImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        imageBytes = byteArrayOutputStream.toByteArray();
    }
}