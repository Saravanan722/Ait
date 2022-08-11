package com.example.applicationdatabaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class LoginScreen extends AppCompatActivity {
    private Button mLogoutButton;
    private TextView mTextView;
    private Button mDisplayButton;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_screen);
        mLogoutButton = findViewById(R.id.button1);
        mTextView = findViewById(R.id.textView);
        mDisplayButton = findViewById(R.id.button2);
        mTextView.setText(getUserEmail());
        saveUserEmail(email);

        mDisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<User> users = DatabaselearningApplication.getApplicationDatabase().getUsers();
                String userString = "";
                for (User user : users) {
                    userString = userString + "Email: " + user.getEmail() + "Name: " + user.getName() + "Mobile: " + user.getMobile();
                }

                mTextView.setText(userString);
                Toast.makeText(LoginScreen.this, "Successful..", Toast.LENGTH_SHORT).show();
            }
        });
        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                myEdit.clear();

                Intent intent = new Intent(LoginScreen.this, LoginApplication.class);
                startActivity(intent);

            }
        });
    }

    public void saveUserEmail(String email) {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("email", email);
        myEdit.apply();
    }
    public String getUserEmail() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        return sharedPreferences.getString("email", "");
    }
}


