package com.saylonn.messenger;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saylonn.messenger.Comm.VolleyRequest;

public class LoginFragment extends AppCompatActivity{
    TextView tv;
    EditText pt_username;
    EditText pt_password;
    Button login_btn;
    VolleyRequest vr;
    Boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv = findViewById(R.id.tv_login_error);
        login_btn = findViewById(R.id.btn_login_loginbtn);
        pt_username = findViewById(R.id.pt_login_username);
        pt_password = findViewById(R.id.pt_login_password);


        //Do login Request
        vr = new VolleyRequest(this.getApplicationContext());
        vr.addCallbackListener(this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vr.login(pt_username.getText().toString(), pt_password.getText().toString());
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