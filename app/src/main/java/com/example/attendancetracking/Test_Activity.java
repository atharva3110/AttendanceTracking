package com.example.attendancetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Test_Activity extends AppCompatActivity
{
    private Button logout;
    private TextView user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_);
        logout=(Button)findViewById(R.id.btnLogout);
        user_id=(TextView)findViewById(R.id.txtId);
        String id=getIntent().getStringExtra("Current_logged");
        user_id.setText(id);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth nAuth;
                nAuth=FirebaseAuth.getInstance();
                nAuth.signOut();
                startActivity(new Intent(Test_Activity.this,login.class));
            }
        });
    }
}
