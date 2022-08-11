package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class LoginScreen2 extends AppCompatActivity implements View.OnClickListener{
    private EditText textName,textEmail,textPassword,textConfirmPassword,textMobile;
    private Button button;
    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;
    private Cursor cursor;
    private UserDatails userDatails;
    private InputValidation inputValidation;
    private final  AppCompatActivity activity = LoginScreen2.this;
    private Object UserDatails;
    private NestedScrollView nestedScrollView;
    private AppCompatTextView appCompatTextViewLoginLink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen2);
        final DatabaseHelper db = new DatabaseHelper(this);

initValues();
//emptyInputEditText();
initObjects();
    }
    private void initValues() {
        textName = findViewById(R.id.text);
        textEmail = findViewById(R.id.textA);
        textPassword = findViewById(R.id.textB);
        textConfirmPassword = findViewById(R.id.textC);
        textMobile = findViewById(R.id.textD);
        button = findViewById(R.id.buttonE);
    }
    private void initListeners() {
        button.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        UserDatails = new UserDatails();
    }

            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonE:
                //        postDatasToSQLite();
                        break;
                    case R.id.constarinlayout:
                        finish();
                        break;
                }

            }


           /* private void postDatasToSQLite() {
                if (!inputValidation.isInputEditTextFilled(textName, getString(R.string.error_message_name))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textEmail, getString(R.string.error_message_email))) {
                    return;
                }
                if (!inputValidation.isInputEditTextEmail(textPassword, getString(R.string.error_message_passwor))) {
                    return;
                }
                if (!inputValidation.isInputEditTextFilled(textConfirmPassword, getString(R.string.error_message_confirm_password))) {
                    return;
                }
                if (!inputValidation.isInputEditTextMatches(textMobile, getString(R.string.error_password_match))) {
                    return;
                }
                if (!databaseHelper.checkUser(textEmail.getText().toString().trim())) {
                    userDatails.setName(textName.getText().toString().trim());
                    userDatails.setEmail(textEmail.getText().toString().trim());
                    userDatails.setPassword(textPassword.getText().toString().trim());
                    userDatails.setConfirmpassword(textConfirmPassword.getText().toString().trim());
                    userDatails.getMobile(textMobile.getText().toString().trim());

                    databaseHelper.addUser(userDatails);
                    Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
                    emptyInputEditText();
                } else {
                    Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
                }
            }

            private void emptyInputEditText() {
                textName.setText(null);
                textEmail.setText(null);
                textPassword.setText(null);
                textConfirmPassword.setText(null);
                textMobile.setText(null);
            }
        */}