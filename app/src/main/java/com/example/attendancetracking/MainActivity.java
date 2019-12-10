package com.example.attendancetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn = (Button) findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
    }
    

    public void openLogin() {
        startActivity(new Intent(this, login.class));
    }
}
