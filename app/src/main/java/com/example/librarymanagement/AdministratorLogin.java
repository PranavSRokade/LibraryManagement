package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdministratorLogin extends AppCompatActivity {
    EditText username, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_login);

        username = findViewById(R.id.usernameAdministrator);
        password = findViewById(R.id.passwordAdministrator);
        login = findViewById(R.id.loginAdministrator);

        SharedPreferences sharedPreferences = getSharedPreferences(String.valueOf(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", "administrator");
        editor.putString("Password", "123456");
        editor.apply();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(username.getText()).equals(sharedPreferences.getString("Username", "")) && String.valueOf(password.getText()).equals(sharedPreferences.getString("Password", ""))){
                    Toast.makeText(AdministratorLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    editor.putBoolean("login", true);
                    editor.apply();

                    finish();
                    startActivity(new Intent(AdministratorLogin.this, ListOfBooks.class));
                }
                else{
                    String toast = "Username or Password is incorrect. \nTry Again";
                    Spannable centeredText = new SpannableString(toast);
                    centeredText.setSpan(
                            new AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER), 0, toast.length() - 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE
                    );
                    Toast.makeText(view.getContext(), centeredText, Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                }
            }
        });


    }
}