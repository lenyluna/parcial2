package com.alphaplusoftgmail.picabumobil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class contenido extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String inicio_url = "http://192.168.43.247:4567";
    private static final String url = inicio_url + "/api/post/all";
    private ProgressDialog pDialog;
    private ArrayList<Post> postlist = new ArrayList<Post>();
    private ListView listView;
    ArrayAdapter<Post> adapter;
    private static ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    static Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        makeJsonObjectRequest();

        listView = (ListView) findViewById(R.id.post_lista);
        adapter = new postArrayAdapter(getApplicationContext(),0, postlist);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color
       /* getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#1b1b1b")));*/

        // Creating volley request obj
        anadir = (Button) this.findViewById(R.id.add);

        anadir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent Tab_activity = new Intent(getBaseContext(), crear.class);
                // Tab_activity.putExtra("user", user); //Optional parameters
                startActivity(Tab_activity);

            }
        });
    }

    class postArrayAdapter extends ArrayAdapter<Post>{
        private  Context context;
        private List<Post> postss;

        public postArrayAdapter(Context context, int resource, ArrayList<Post> objects){
            super(context,resource,objects);

            this.context = context;
            this.postss = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Post informacion_actual = postss.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_view,null);

            if (imageLoader == null)
                imageLoader = AppController.getInstance().getImageLoader();
            NetworkImageView thumbNail = (NetworkImageView) view.findViewById(R.id.thumbnail);
            TextView title = (TextView) view.findViewById(R.id.titulo);
            TextView rating = (TextView) view.findViewById(R.id.descripcion);

            // thumbnail image
            thumbNail.setImageUrl(informacion_actual.getUrlimagen(), imageLoader);
            // title
            title.setText(informacion_actual.getTitulo());
            // rating
            rating.setText("Descripcion: " + String.valueOf(informacion_actual.getDescripcion()));

            return view;
        }
    }

    private void makeJsonObjectRequest() {

        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        try {
                            System.out.println(response.toString());

                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {


                                    JSONObject obj = response.getJSONObject(i);
                                    Post post = new Post();
                                    post.setTitulo(obj.getString("titulo"));
                                    post.setDescripcion(obj.getString("descripcion"));
                                    post.setUrlimagen(inicio_url + obj.getString("urlimagen"));


                                    // adding movie to movies array
                                    postlist.add(post);



                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println(e.getMessage());

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
