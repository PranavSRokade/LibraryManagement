//package com.example.librarymanagement;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class UserLogin extends AppCompatActivity {
//    TextView clickHere, userLogin;
//    Button loginUser;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_login);
//
//        clickHere = findViewById(R.id.clickHere);
//        userLogin = findViewById(R.id.userLogin);
//        loginUser = findViewById(R.id.loginUser);
//
//        clickHere.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UserLogin.this, AdministratorLogin.class));
//            }
//        });
//
//        userLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UserLogin.this, ListOfBooks.class));
//                finish();
//            }
//        });
//
//        loginUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(UserLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(UserLogin.this, ListOfBooks.class));
//                finish();
//            }
//        });
//    }
//}

package com.example.librarymanagement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserLogin extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
//    private EditText courseNameEdt, courseTracksEdt, courseDurationEdt, courseDescriptionEdt;
    private EditText courseNameEdt, coursePasswordEdt;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);   // check changes

        // initializing all our variables.
        courseNameEdt = findViewById(R.id.idEdtUserName);
        coursePasswordEdt = findViewById(R.id.idEdtPassword);
//        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
//        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        addCourseBtn = findViewById(R.id.idBtnAddCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(UserLogin.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String courseName = courseNameEdt.getText().toString();
                String courseTracks = coursePasswordEdt.getText().toString();
//                String courseDuration = courseDurationEdt.getText().toString();
//                String courseDescription = courseDescriptionEdt.getText().toString();

                // validating if the text fields are empty or not.
//                if (courseName.isEmpty() && courseTracks.isEmpty() && courseDuration.isEmpty() && courseDescription.isEmpty()) {
                if (courseName.isEmpty() && courseTracks.isEmpty()) {
                    Toast.makeText(UserLogin.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
//                dbHandler.addNewCourse(courseName, courseDuration, courseDescription, courseTracks);
                dbHandler.addNewCourse(courseName, courseTracks);

                // after adding the data we are displaying a toast message.
                Toast.makeText(UserLogin.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                courseNameEdt.setText("");
                coursePasswordEdt.setText("");
//                courseTracksEdt.setText("");
//                courseDescriptionEdt.setText("");
            }
        });
    }
}
