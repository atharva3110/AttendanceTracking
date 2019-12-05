package com.example.attendancetracking;

public class User
{
    public final String name;
    public final String email;
    public final String dob;
    public final String password;


    public User(String name,String password,String dob,String email)
    {
        this.name=name;
        this.password=password;

        this.dob=dob;
        this.email=email;
    }
}
