package com.alphaplusoftgmail.picabumobil;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class crear extends AppCompatActivity {

    static String url;

    static EditText titulo;
    static EditText descripcion;
    static EditText etiquetas;
    static Button crear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        final RequestQueue queue = Volley.newRequestQueue(this);  // this = context

        titulo = (EditText) this.findViewById(R.id.titulo_edit);
        descripcion = (EditText) this.findViewById(R.id.descri_edit);
        etiquetas = (EditText) this.findViewById(R.id.eti_edit);
        crear = (Button) this.findViewById(R.id.add);



        crear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // crear(getBaseContext());
            }
        });

    }

    public void crear(Context context)
    {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            String URL = "http://localhost:4567/api/post/new";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("titulo",titulo.getText().toString());
            jsonBody.put("descripcion",descripcion.getText().toString());
            jsonBody.put("etiquetas", etiquetas.getText().toString());
            jsonBody.put("img", "https://deerhuntertrendblog.files.wordpress.com/2014/07/semestre.jpg");
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i("VOLLEY", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("VOLLEY", error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            AppController.getInstance().addToRequestQueue(stringRequest);
           // requestQueue.add(stringRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //----------------------------------------------
        //AppController.getInstance().addToRequestQueue(postRequest);
        // queue.add(postRequest);


    }
    }

