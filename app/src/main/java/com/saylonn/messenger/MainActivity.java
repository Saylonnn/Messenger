package com.saylonn.messenger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private NavController navController;
    SharedPreferences sharedPreferences;
    boolean loggedIn = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Log.d("myapp", "main started");
        setContentView(R.layout.activity_main);


    }
}



