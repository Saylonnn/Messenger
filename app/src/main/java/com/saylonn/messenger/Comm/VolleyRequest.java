package com.saylonn.messenger.Comm;


import android.app.Application;
import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.saylonn.messenger.MainActivity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyRequest {

    private List<MainActivity> callbackApp = new ArrayList<>();
    RequestQueue queue;
    String url = "https://www.api.caylonn.de:1337";

    public VolleyRequest(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void login(String username, String hash){
        String custUrl = url + "/auth/login";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, custUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        for (MainActivity x : callbackApp) {
                            if(response.toString().equals("accepted")){
                                x.getAnswer("login", "accepted");
                            }
                            else{
                                x.getAnswer("login", "declined");
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        for(MainActivity x : callbackApp){

                            if(error.toString().equals("com.android.volley.AuthFailureError")){
                                x.getAnswer("login", "declined");
                            }
                            else if(error.getMessage().equals("java.net.ConnectException: Failed to connect to www.api.caylonn.de/116.203.144.88:1337")){
                                x.getAnswer("login", "service unreachable");
                            }
                            else{
                                x.getAnswer("login", error.getMessage());
                            }
                        }
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", hash);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void addCallbackListener(MainActivity ma){
        callbackApp.add(ma);
    }


}
