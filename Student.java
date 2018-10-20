package com.example.lioncode.sec;

public class Student {
    private String _email;
    private String _password;
    private String _clockin;
    private String _clockout;

    public Student() {
    }
    public Student(String email, String password, String clockin, String clockout) {
        this._email = email;
        this._password = password;
        this._clockin = clockin;
        this._clockout = clockout;
    }
    public void setEmail(String email) {
        this._email = email;
    }
    public String getEmail() {
        return this._email;
    }
    public void setPassword(String password){
        this._password = password;
    }
    public String getPassword(){
        return this._password;
    }
    public void setClockin(String clockin){
        this._clockin = clockin;
    }
    public String getClockin(){
        return this._clockin;
    }
    public void setClockout(String clockout){
        this._clockout = clockout;
    }
    public String getClockout(){
        return this._clockout;
    }
}
