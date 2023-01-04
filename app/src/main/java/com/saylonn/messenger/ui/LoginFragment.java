package com.saylonn.messenger.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.saylonn.messenger.Comm.VolleyRequest;
import com.saylonn.messenger.FCM.MyFirebaseMessagingService;
import com.saylonn.messenger.Interfaces.CallbackInterface;
import com.saylonn.messenger.MainActivity;
import com.saylonn.messenger.R;

import java.util.Objects;
import java.util.prefs.PreferenceChangeEvent;

public class LoginFragment extends Fragment implements CallbackInterface {
    TextView errorTv;
    //String fcm_token;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        VolleyRequest vr = new VolleyRequest(requireActivity().getBaseContext());
        vr.addCallbackListener(this);


        errorTv = root.findViewById(R.id.errorTv);
        TextInputEditText email = root.findViewById(R.id.emailTE);
        TextInputEditText password = root.findViewById(R.id.passwordTE);
        Button loginBtn = root.findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(View -> {
            Log.d("myapp", "onClick");
            vr.login(email.getText().toString(), password.getText().toString());
            Log.d("myapp", "clicked");
        });
        return root;
    }

    @Override
    public void callbackFunction(String targetFunction, String message) {
        errorTv.setText(message);
    }
}