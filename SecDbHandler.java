package com.example.lioncode.sec;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SecDbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "secDB.db";
    private static final String TABLE_STUDENTS = "students";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_CLOCKIN = "Clockin";
    private static final String COLUMN_CLOCKOUT = "Clockout";

    public SecDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        //super calls SQLiteOpenHelper
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " +
                TABLE_STUDENTS + "(" + COLUMN_EMAIL + " TEXT PRIMARY KEY," +
                COLUMN_PASSWORD + " TEXT, " + COLUMN_CLOCKIN + " TEXT, " + COLUMN_CLOCKOUT
                + " TEXT " + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_PASSWORD, student.getPassword());
        values.put(COLUMN_CLOCKIN, student.getClockin());
        values.put(COLUMN_CLOCKOUT, student.getClockout());

        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }//end of addHandler


    public Student findStudent(String password) {
        String query = "Select * FROM " + TABLE_STUDENTS + " WHERE " + COLUMN_PASSWORD + " = '" + password + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Student student = new Student();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            student.setEmail(cursor.getString(0));
            cursor.close();
        } else {
            student = null;
        }
        db.close();
        //return password and email
        return student;
    }

    public String loadStudent() {
        String result = "";
        String query = "Select*FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String email = cursor.getString(0);
            String password = cursor.getString(1);
            String clockin = cursor.getString(2);
            String clockout = cursor.getString(3);

            result += email + " " + password + " " + clockin + " " + clockout +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }//end of load

    public boolean updateTime(String email, String password, String clockin, String clockout) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_CLOCKIN, clockin);
        if(clockout != ""){
            clockout = "";
            cv.put(COLUMN_CLOCKOUT, clockout);
            return db.update(TABLE_STUDENTS, cv, COLUMN_CLOCKIN + "=" + clockin, null) > 0;
        }
        cv.put(COLUMN_CLOCKOUT, clockout);
        return db.update(TABLE_STUDENTS, cv, COLUMN_CLOCKOUT + "=" + clockout, null) > 0;
    }

    public String checkStudent(Student student) {
        String id = "";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COLUMN_EMAIL FROM TABLE_STUDENTS WHERE email=? " +
                "AND password=?",new String[]{student.getEmail(),student.getPassword()});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getString(0 );
            cursor.close();
        }
        return id;
    }

    public void deleteUser(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_STUDENTS, COLUMN_EMAIL + " = ?",
                new String[]{String.valueOf(student.getEmail())});
        db.close();
    }
}
