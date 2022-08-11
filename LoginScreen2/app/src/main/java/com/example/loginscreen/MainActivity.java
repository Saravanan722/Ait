package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    Button buttonB;
    Button buttonA;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, LoginScreen2.class);
        startActivity(intent);
    }


}