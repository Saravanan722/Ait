package com.example.applicationdatabaselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteDatabaseStore extends AppCompatActivity {
    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mMobileEditText;
    private Button mSignUpButton;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_q_lite_database_store);

        mNameEditText = findViewById(R.id.text1);
        mEmailEditText = findViewById(R.id.textA);
        mPasswordEditText = findViewById(R.id.textB);
        mMobileEditText = findViewById(R.id.textD);
        mSignUpButton = findViewById(R.id.signup_button);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(mNameEditText.getText().toString());
                user.setEmail(mEmailEditText.getText().toString());
                user.setPassword(mPasswordEditText.getText().toString());
                user.setMobile(mMobileEditText.getText().toString());
                DatabaselearningApplication.getApplicationDatabase().insertUser(user);
                Toast.makeText(SQLiteDatabaseStore.this, "Saved User Data..", Toast.LENGTH_SHORT).show();
                startAct();

            }
        });
    }

    private void startAct() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
