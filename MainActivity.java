package com.example.lioncode.sec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SecDbHandler dh;
    EditText email;
    EditText password;
    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new SecDbHandler(this, null, null, 1);
        email = (EditText) findViewById(R.id.loginEmailEditText);
        password = (EditText) findViewById(R.id.passwordEditText);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.liRegisterButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luEmail = email.getText().toString() ;
                String pass = password.getText().toString();
                Boolean chkempass = dh.checkStudent(luEmail, pass);
                if(!chkempass){
                    Toast.makeText(getApplicationContext(), "Wrong email or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Successfully Login",Toast.LENGTH_SHORT).show();
                    if(luEmail.equals("admin")){
                        Intent a = new Intent(MainActivity.this, Admin.class);
                       startActivity(a);
                    }
                    else{
                        Intent time = new Intent(MainActivity.this, TimeStamp.class);
                        time.putExtra("EML", luEmail);
                        time.putExtra("PWD", pass);
                        startActivity(time);
                    }
                }
            }
            });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent l = new Intent(MainActivity.this,Register.class);
                startActivity(l);
            }
        });
    }
}
