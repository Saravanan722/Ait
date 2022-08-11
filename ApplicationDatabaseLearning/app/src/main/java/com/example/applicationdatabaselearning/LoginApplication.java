package com.example.applicationdatabaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;
import java.util.ArrayList;

import static com.example.applicationdatabaselearning.DatabaselearningApplication.getApplicationDatabase;

public class LoginApplication extends AppCompatActivity {
    private Button mButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_application);
        mButton = findViewById(R.id.button);
        mEmailEditText = findViewById(R.id.edittext1);
        mPasswordEditText = findViewById(R.id.edittext2);

        mEmailEditText.setText(getUserEmail());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                saveUserEmail(email);
                User user = getApplicationDatabase().getUserByEmail(email);
                if (user == null) {
                    Toast.makeText(LoginApplication.this, "No User Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                String emailDb = user.getEmail();
                String passwordDb = user.getPassword();

                if (email.equals(emailDb) && password.equals(passwordDb)) {
                    Toast.makeText(LoginApplication.this, "Successful", Toast.LENGTH_SHORT).show();
                        openNewActivity();

                } else {
                    Toast.makeText(LoginApplication.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void openNewActivity() {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
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