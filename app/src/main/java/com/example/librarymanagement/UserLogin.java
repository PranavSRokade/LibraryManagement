package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserLogin extends AppCompatActivity {
    TextView clickHere, userLogin;
    Button loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        clickHere = findViewById(R.id.clickHere);
        userLogin = findViewById(R.id.userLogin);
        loginUser = findViewById(R.id.loginUser);

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, AdministratorLogin.class));
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, ListOfBooks.class));
                finish();
            }
        });

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UserLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserLogin.this, ListOfBooks.class));
                finish();
            }
        });
    }
}