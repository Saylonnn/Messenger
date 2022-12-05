package com.saylonn.messenger.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.saylonn.messenger.Comm.VolleyRequest;
import com.saylonn.messenger.Interfaces.CallbackInterface;
import com.saylonn.messenger.R;

public class LoginFragment extends AppCompatActivity implements CallbackInterface {
    private final String TAG = "MyActivity";
    TextView tv;
    EditText pt_username;
    EditText pt_password;
    Button login_btn;
    VolleyRequest vr;
    Boolean loggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv = findViewById(R.id.tv_login_error);
        login_btn = findViewById(R.id.login_btn);
        pt_username = findViewById(R.id.pt_login_username);
        pt_password = findViewById(R.id.pt_login_password);


        //Do login Request
        vr = new VolleyRequest(this.getApplicationContext());
        vr.addCallbackListener(this);
        login_btn.setOnClickListener(view -> {
            Log.d(TAG, "login onClick called");
            vr.login(pt_username.getText().toString(), pt_password.getText().toString());
            Log.d(TAG, "login onClick called");
        });
    }

    @Override
    public void callbackFunction(String targetFunc, String message){
        tv.setText(message);

    }
}