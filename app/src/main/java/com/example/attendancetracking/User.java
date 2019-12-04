package com.example.attendancetracking;

public class User
{
    public String name;
    public String email;
    public String dob;
    public String password;


    public User(String name,String password,String dob,String email)
    {
        this.name=name;
        this.password=password;

        this.dob=dob;
        this.email=email;
    }
}
