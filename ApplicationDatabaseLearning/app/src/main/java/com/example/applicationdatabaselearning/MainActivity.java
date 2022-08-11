package com.example.applicationdatabaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mSignupButtn;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mSignupButtn = (Button) findViewById(R.id.signup_button);
        mLoginButton = findViewById(R.id.login_button);

        mSignupButtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SQLiteDatabaseStore.class);
                startActivity(intent);
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, LoginApplication.class);
                startActivity(intent);
            }
        });
    }

    public void getUserEmail() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
    }
}