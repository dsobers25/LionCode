package com.example.lioncode.sec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Admin extends AppCompatActivity {


    TextView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        lst = (TextView) findViewById(R.id.list);
    }
    public void loadStudents(View view) {
        SecDbHandler dbHandler = new SecDbHandler(this, null, null, 1);
        lst.setText(dbHandler.loadStudent());

    }
}
