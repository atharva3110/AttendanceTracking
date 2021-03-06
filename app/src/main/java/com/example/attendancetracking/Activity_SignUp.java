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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_SignUp extends AppCompatActivity {

    TextView Login;
    Button signUp;
    EditText name,password,confirm_password,dob,email;

    private FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_up);

        Login = (TextView) findViewById(R.id.login);
        name = (EditText)findViewById(R.id.signup_name);
        password = (EditText)findViewById(R.id.login_password);
        confirm_password = (EditText)findViewById(R.id.signup_confm_password);
        email = (EditText)findViewById(R.id.signup_email);
        dob = (EditText)findViewById(R.id.signup_dob);
        signUp = (Button)findViewById(R.id.btnSignUp);
        nAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_name,user_password,user_confirm_password,user_dob,user_email;

                user_name=name.getText().toString().trim();
                user_password=password.getText().toString().trim();
                user_email=email.getText().toString().trim();
                user_dob=dob.getText().toString().trim();
                user_confirm_password=confirm_password.getText().toString().trim();

                signUp_user(user_name,user_password,user_confirm_password,user_dob,user_email);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_SignUp.this,login.class));
            }
        });
    }

    private void signUp_user(final String user_name, final String user_password, String user_confirm_password, final String user_dob, final String user_email) {
        if(user_name.isEmpty() || user_password.isEmpty() || user_confirm_password.isEmpty() || user_email.isEmpty() || user_dob.isEmpty()) {
            Toast toast=Toast.makeText(getApplicationContext(),"You missed something!",Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if(!user_password.equals(user_confirm_password)) {
                Toast toast=Toast.makeText(getApplicationContext(),"Passwords does not match",Toast.LENGTH_SHORT);
                toast.show();
            } else if(user_password.length()<6) {
                   Toast.makeText(getApplicationContext(),"Password should be atleast 6 characters long!",Toast.LENGTH_SHORT).show();
            } else {
                 //  ref.push().setValue(user);
                Intent intent = new Intent(Activity_SignUp.this, login.class);
                startActivity(intent);
                nAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               FirebaseDatabase database = FirebaseDatabase.getInstance();
                               DatabaseReference ref = database.getReference("User");
                               DatabaseReference root = ref.child(nAuth.getUid());
                               User user = new User(user_name, user_password, user_dob, user_email);
                               root.setValue(user);
                               Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT).show();
                           } else {
                               Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
            }
        }
    }
}
