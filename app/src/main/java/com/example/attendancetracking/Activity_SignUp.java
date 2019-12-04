package com.example.attendancetracking;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Activity_SignUp extends AppCompatActivity {

    TextView Login;
    Button signUp;
    EditText name,password,confirm_password,dob,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sign_up);

        Login = (TextView) findViewById(R.id.login);
        name=(EditText)findViewById(R.id.signup_name);
        password=(EditText)findViewById(R.id.login_password);
        confirm_password=(EditText)findViewById(R.id.signup_confm_password);
        email=(EditText)findViewById(R.id.signup_email);
        dob=(EditText)findViewById(R.id.signup_dob);
        signUp=(Button)findViewById(R.id.btnSignUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
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
            public void onClick(View v)
            {
                Intent intent=new Intent(Activity_SignUp.this,login.class);
                startActivity(intent);
            }
        });
    }

    private void signUp_user(String user_name, String user_password, String user_confirm_password, String user_dob, String user_email)
    {
        if(user_name==NULL || user_password==NULL || user_confirm_password==NULL || user_email==NULL || user_dob==NULL)
        {
            Toast toast=Toast.makeText(getApplicationContext(),"You missed something",Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("User");
            User user = new User(user_name, user_password, user_dob, user_email);
            ref.push().setValue(user);
            Toast toast = Toast.makeText(getApplicationContext(), "Signup Successful", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(Activity_SignUp.this, login.class);
            startActivity(intent);
        }
    }

    public void openLogin()
    {
        Intent intent=new Intent(Activity_SignUp.this,login.class);
        startActivity(intent);
    }

}
