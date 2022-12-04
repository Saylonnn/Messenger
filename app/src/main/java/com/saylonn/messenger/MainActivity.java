package com.saylonn.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.saylonn.messenger.Comm.VolleyRequest;


public class MainActivity extends AppCompatActivity{
    TextView tv;
    EditText tf_username;
    EditText tf_password;
    EditText tf_email;
    Button login_btn;
    Button register_btn;
    VolleyRequest vr;
    Boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.main_tv);
        login_btn = findViewById(R.id.login_btn);
        register_btn = findViewById(R.id.register_btn);
        tf_username = findViewById(R.id.tf_username);
        tf_password = findViewById(R.id.tf_password);
        tf_email = findViewById(R.id.tf_email);



        //Do login Request
        vr = new VolleyRequest(this.getApplicationContext());
        vr.addCallbackListener(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vr.login(tf_username.getText().toString(), tf_password.getText().toString());
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                vr.register(tf_email.getText().toString(), tf_username.getText().toString(), tf_password.getText().toString());
            }
        });
    }

    public void getAnswer(String targetFunc, String x){
        if(targetFunc.equals("login")){
            if (x.equals("accepted")){
                loggedIn = true;
                tv.setText(x);
            }
        else{
            tv.setText(x);
            }
        }

    }



}