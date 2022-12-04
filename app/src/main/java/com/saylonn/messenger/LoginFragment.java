package com.saylonn.messenger;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.saylonn.messenger.Comm.VolleyRequest;

public class LoginFragment extends AppCompatActivity{
    TextView tv;
    EditText tf_username;
    EditText tf_password;
    Button login_btn;
    VolleyRequest vr;
    Boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv = findViewById(R.id.main_tv);
        login_btn = findViewById(R.id.login_btn);
        tf_username = findViewById(R.id.tf_username);
        tf_password = findViewById(R.id.tf_password);


        //Do login Request
        vr = new VolleyRequest(this.getApplicationContext());
        vr.addCallbackListener(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vr.login(tf_username.getText().toString(), tf_password.getText().toString());
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
