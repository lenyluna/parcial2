package com.alphaplusoftgmail.picabumobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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


                url = "http://localhost:4567/api/post/new";
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error

                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("titulo", titulo.getText().toString());
                        params.put("descripcion", descripcion.getText().toString());
                        params.put("etiqueta", etiquetas.getText().toString());
                        params.put("img", "https://deerhuntertrendblog.files.wordpress.com/2014/07/semestre.jpg");


                        return params;
                    }
                };
                queue.add(postRequest);


            }
        });

    }




}
