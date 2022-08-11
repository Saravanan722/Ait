package com.example.sharedpreferencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText name, age;
    private TextView textView;
    private Button saveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.edit_txt_name);
        age = findViewById(R.id.edit_txt_age);
        textView = findViewById(R.id.txt_view);


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String s1 = sh.getString(String.valueOf(name), "");
        int a = sh.getInt(String.valueOf(age), 0);
        name.setText(s1);
        age.setText(String.valueOf(a));

    }

    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("name", name.getText().toString());
        myEdit.putInt("age", Integer.parseInt(age.getText().toString()));
        myEdit.apply();
    }
}