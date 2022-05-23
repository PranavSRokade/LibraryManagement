package com.example.librarymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UserLogin extends AppCompatActivity {
    TextView clickHere, signUp;
    Button loginUser;
    EditText usernameUser, passwordUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        clickHere = findViewById(R.id.clickHere);
        signUp = findViewById(R.id.signUp);
        loginUser = findViewById(R.id.loginUser);
        usernameUser = findViewById(R.id.usernameUser);
        passwordUser = findViewById(R.id.passwordUser);

        clickHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, AdministratorLogin.class));
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, UserSignUp.class));
            }
        });

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database database = new Database(UserLogin.this);

                ArrayList<User> userList = database.getAllUsers();

                String username = String.valueOf(usernameUser.getText()), password = String.valueOf(passwordUser.getText());
                boolean usernameCheck = false, passwordCheck = false;
                for(User user : userList) {
                    if(user.getUsername().equals(username)){
                        usernameCheck = true;
                    }
                    if(user.getPassword().equals(password)){
                        passwordCheck = true;
                    }
                }

                if(usernameCheck && passwordCheck){
                    Toast.makeText(UserLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(UserLogin.this, ListOfBooks.class));
                    finish();
                }
                else{
                    Toast.makeText(UserLogin.this, "Invalid Credentials. Try Again.", Toast.LENGTH_SHORT).show();

                    usernameUser.setText("");
                    passwordUser.setText("");
                }

            }
        });
    }
}

//package com.example.librarymanagement;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class UserLogin extends AppCompatActivity {
//    private EditText courseNameEdt, coursePasswordEdt;
//    private Button addCourseBtn;
//    private Database dbHandler;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_login);
////        courseNameEdt = findViewById(R.id.usernameUser);
////        coursePasswordEdt = findViewById(R.id.passwordUser);
////        addCourseBtn = findViewById(R.id.loginUser);
////        dbHandler = new Database(UserLogin.this);
////
////        addCourseBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                String courseName = courseNameEdt.getText().toString();
////                String courseTracks = coursePasswordEdt.getText().toString();
////                if (courseName.isEmpty() && courseTracks.isEmpty()) {
////                    Toast.makeText(UserLogin.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
////                    return;
////                }
////                dbHandler.addNewCourse(courseName, courseTracks);
////                Toast.makeText(UserLogin.this, "Course has been added.", Toast.LENGTH_SHORT).show();
////                courseNameEdt.setText("");
////                coursePasswordEdt.setText("");
////            }
////        });
//    }
//}
