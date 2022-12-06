package com.saylonn.messenger.Comm;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.saylonn.messenger.Interfaces.CallbackInterface;
import com.saylonn.messenger.ui.LoginFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VolleyRequest {
    private static final String TAG = "myapp";
    private List<CallbackInterface> callbackApps = new ArrayList<>();
    RequestQueue queue;
    String url = "https://www.api.caylonn.de:1337";

    public VolleyRequest(Context context){
        queue = Volley.newRequestQueue(context);
    }

    public void login(String email, String password){
        Log.d(TAG, "login called with "+ email + " " + password);

        Map<String, String> headerParams = new HashMap<String, String>();
        headerParams.put("email", email);
        headerParams.put("password", password);
        doStringRequest("login", "/auth/login", headerParams, Request.Method.GET);
    }

    public void doStringRequest(String function, String urlExtension, Map<String, String> headerParams, int methode){
        Log.d(TAG, "method doStringRequestCalled");
        String custURL = url + urlExtension;
        StringRequest stringRequest = new StringRequest(methode, custURL,
                response -> {
                    for (CallbackInterface x : callbackApps) {
                        x.callbackFunction(function, response.toString());
                    }
                }, error -> {
                    for (CallbackInterface x : callbackApps){
                        x.callbackFunction(function, error.getMessage());
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                return headerParams;
            }
        };
        queue.add(stringRequest);
    }



    public void addCallbackListener(CallbackInterface ma){
        callbackApps.add(ma);
    }
}
