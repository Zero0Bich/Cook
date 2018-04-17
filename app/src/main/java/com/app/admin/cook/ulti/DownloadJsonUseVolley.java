package com.app.admin.cook.ulti;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.admin.cook.model.CallbackXuLyJson;
import com.app.admin.cook.view.ViewExpandMenu;

import java.util.Map;

/**
 * Created by Admin on 4/6/2018.
 */

public class DownloadJsonUseVolley {
    private Context context;
    private CallbackXuLyJson callbackXuLyJson;
    private String url;
    private Map<String, String> map;

    public DownloadJsonUseVolley(Context context, CallbackXuLyJson callbackXuLyJson, String url, Map<String, String> map) {
        this.context = context;
        this.callbackXuLyJson = callbackXuLyJson;
        this.url = url;
        this.map = map;
    }

    public DownloadJsonUseVolley(Context context, CallbackXuLyJson callbackXuLyJson, String url) {
        this.context = context;
        this.callbackXuLyJson = callbackXuLyJson;
        this.url = url;
    }

    public void downloadDataUsePost() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callbackXuLyJson.xuLyJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorPost", error.toString());
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void downloadJsonUseGet() {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callbackXuLyJson.xuLyJson(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ErrorGet", error.toString());
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}
