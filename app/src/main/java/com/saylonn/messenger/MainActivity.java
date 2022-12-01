package com.saylonn.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.saylonn.messenger.Comm.VolleyRequest;


public class MainActivity extends AppCompatActivity{
    TextView tv;
    Button login_btn;
    VolleyRequest vr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.main_tv);
        login_btn = findViewById(R.id.login_btn);


        //Do login Request
        vr = new VolleyRequest(this.getApplicationContext());
        vr.addCallbackListener(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vr.login("me", "ecretpassword");
            }
        });
    }

    public void getAnswer(String x){
        tv.setText(x);
    }



}