package com.example.lioncode.sec;

public class Student {
    private int _id;
    private String _studentname;
    private String _password;
    private String _clockin;
    private String _clockout;

    public Student() {
    }
    public Student(int id, String studentname, String password) {
        this._id = id;
        this._studentname = studentname;
        this._password = password;
    }
    public void setID(int id) {
        this._id = id;
    }
    public int getID() {
        return this._id;
    }
    public void setStudentName(String studentname) {
        this._studentname = studentname;
    }
    public String getStudentName() {
        return this._studentname;
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
