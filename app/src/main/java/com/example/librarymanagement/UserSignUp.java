package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UserSignUp extends AppCompatActivity {
    EditText newUsername, newPassword;
    Button newSignUp;
    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);

        newUsername = findViewById(R.id.newUsername);
        newPassword = findViewById(R.id.newPassword);
        newSignUp = findViewById(R.id.newSignUp);

        newSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<User> userList = new Database(UserSignUp.this).getAllUsers();

//                for(User user : userList) {
//                    if (user.getUsername().equals(String.valueOf(newUsername.getText()))) {
//                        check = true;
//                    }
//                }
//
//                if(!check) {
                    User user = new User();

                    user.setUsername(String.valueOf(newUsername.getText()));
                    user.setPassword(String.valueOf(newPassword.getText()));

                    new Database(UserSignUp.this).addNewUser(user);

                    Toast.makeText(UserSignUp.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(UserSignUp.this, UserLogin.class));
                    finish();
                }
//                else{
//                    Toast.makeText(UserSignUp.this, "Username " + newUsername.getText() + " is already taken. Try again.", Toast.LENGTH_SHORT).show();
//
//                    newUsername.setText("");
//                    newPassword.setText("");
//                }
//            }
        });
    }
}