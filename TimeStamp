package com.example.lioncode.sec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TimeStamp extends AppCompatActivity {

    SecDbHandler dh;
    Button clockin;
    Button clockout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_stamp);
        dh = new SecDbHandler(this, null, null, 1);
        clockin = (Button) findViewById(R.id.tsClockInBtn);
        clockout = (Button) findViewById(R.id.tsClockOutBtn);
        final String email = getIntent().getStringExtra("EML");
        final String password = getIntent().getStringExtra("PWD");

        clockin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Student istu = dh.findStudent(email);
                String icin = istu.getClockin();
                String icout = istu.getClockout();
                Boolean up = dh.updateTime(email, password, icin, icout);

                if(up){
                    Toast.makeText(getApplicationContext(), "Clocked in",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Did not work",Toast.LENGTH_SHORT).show();
                }


            }
        });//end of listener

        clockout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student ostu = dh.findStudent(email);
                String ocin = ostu.getClockin();
                String ocout = ostu.getClockout();
                Boolean up = dh.updateTime(email, password, ocin, ocout);

                if(up){
                    Toast.makeText(getApplicationContext(), "Clocked out",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Did not work",Toast.LENGTH_SHORT).show();
                }

            }
        });//end of listener
    }//end of oncreate

}
