package com.alphaplusoftgmail.picabumobil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrar = (Button) this.findViewById(R.id.entrar);


        entrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                Intent Tab_activity = new Intent(getBaseContext(), contenido.class);
               // Tab_activity.putExtra("user", user); //Optional parameters
                startActivity(Tab_activity);

            }
        });
    }
}
