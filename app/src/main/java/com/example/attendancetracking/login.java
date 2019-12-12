package com.example.attendancetracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    TextView signup;
    private EditText user_name,user_password;
    private Button login;
    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener nAuthListener;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (TextView) findViewById(R.id.signup);
        login=(Button)findViewById(R.id.btnLogin);
        user_name=(EditText)findViewById(R.id.login_email);
        user_password=(EditText)findViewById(R.id.login_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_name.getText().toString().isEmpty() || user_password.getText().toString().isEmpty()) {
                    Toast.makeText(login.this,"You missed something!",Toast.LENGTH_SHORT).show(); //If any field is left empty
                } else {
                    nAuth.signInWithEmailAndPassword(user_name.getText().toString(),user_password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"You are logged in!",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(login.this,Test_Activity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(login.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp(v);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        nAuth = FirebaseAuth.getInstance();
        user = nAuth.getCurrentUser();
        if(user!=null) {
            startActivity(new Intent(this,Test_Activity.class));
        }
    }

    public void openSignUp(View v) {
        Intent intent = new Intent(this, Activity_SignUp.class);
        startActivity(intent);
    }
}
