package com.saylonn.messenger.Comm;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.saylonn.messenger.Interfaces.CallbackInterface;
import com.saylonn.messenger.ui.LoginFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class VolleyRequest {
    private static final String TAG = "MyActivity";
    private List<CallbackInterface> callbackApps = new ArrayList<>();
    RequestQueue queue;
    String url = "https://www.api.caylonn.de:1337";

    public VolleyRequest(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void login(String email, String password){
        Log.d(TAG, "login called");
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
        }
        doStringRequest("login", "/auth/login", jsonBody, Request.Method.GET);
    }

    public void doStringRequest(String function, String urlExtension, JSONObject params, int methode){
        Log.d("debug", "method doStringRequestCalled");
        String custURL = url + urlExtension;
        StringRequest stringRequest = new StringRequest(methode, custURL,
                response -> {
                    for (CallbackInterface x : callbackApps) {
                        x.callbackFunction(function, response.toString());
                    }
                }, error -> {
                    for (CallbackInterface x : callbackApps){
                        x.callbackFunction(function, error.toString());
                    }
                }){
            @Override
             public String getBodyContentType(){
                return "application/json; charset=utf-8";
             }

            @Override
            public byte[] getBody() throws AuthFailureError {
                String mRequestBody = params.toString();
                return mRequestBody == null ? null: mRequestBody.getBytes(StandardCharsets.UTF_8);
            }
        };
        queue.add(stringRequest);
    }



    public void addCallbackListener(CallbackInterface ma){
        callbackApps.add(ma);
    }
}
