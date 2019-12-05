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

public class login extends AppCompatActivity {

    TextView signup;
    private EditText user_name,user_password;
    private Button login;
    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener nAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signup = (TextView) findViewById(R.id.signup);
        login=(Button)findViewById(R.id.btnLogin);
        nAuth=FirebaseAuth.getInstance();
        user_name=(EditText)findViewById(R.id.login_email);
        user_password=(EditText)findViewById(R.id.login_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(user_name.getText().toString().isEmpty() || user_password.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"You missed something!",Toast.LENGTH_SHORT).show(); //If any field is left empty
                }
                else
                {
                    nAuth.signInWithEmailAndPassword(user_name.getText().toString(),user_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"You are logged in!",Toast.LENGTH_SHORT).show();
                                //Enter thr code if user fails to login
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Login unsuccessful",Toast.LENGTH_SHORT).show();
                                //Enter the code after user has logged in here
                            }
                        }
                    });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    public void openSignUp() {
        Intent intent = new Intent(this, Activity_SignUp.class);
        startActivity(intent);

    }
}
