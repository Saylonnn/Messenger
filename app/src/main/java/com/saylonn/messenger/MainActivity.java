package com.saylonn.messenger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private NavController navController;
    //SharedPreferences sharedPreferences;
    boolean loggedIn = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //sharedPreferences.edit().putString(getString(R.string.token_key), "");
        Log.d("myapp", "main started");
        setContentView(R.layout.activity_main);


    }

}



