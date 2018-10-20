package com.example.lioncode.sec;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    SecDbHandler dh;
    EditText email;
    EditText pw;
    EditText cfpw;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dh = new SecDbHandler(this, null, null, 1);
        email = (EditText) findViewById(R.id.emailEditText);
        pw = (EditText) findViewById(R.id.pwEditText);
        cfpw = (EditText) findViewById(R.id.confirmpwEditText);
        register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SQLiteDatabase db = dh.getWritableDatabase();
                String luEmail = email.getText().toString();
                String pass = pw.getText().toString();
                String cfpass = cfpw.getText().toString();
                Student stu = new Student(luEmail, pass,null,null);
                if(pass.equals(cfpass)){
                    dh.addStudent(stu);
                   Toast.makeText(getApplicationContext(), "register successfully",Toast.LENGTH_LONG).show();
                }
                db.close();

            }//end of onClick
        });
    }
}
