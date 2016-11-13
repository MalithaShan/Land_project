package com.example.user.land_project;


import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String JSON_URL = "http://192.168.8.102:81/LandProject_serverSide/getData.php";

    private Button buttonGet;

    private TextView textViewShape;
    private TextView textViewColour;
    private TextView textViewX;
    private TextView textViewY;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        textViewShape = (TextView) findViewById(R.id.shapeText);
        textViewColour = (TextView) findViewById(R.id.colureText);
        textViewX = (TextView) findViewById(R.id.XText);
        textViewY = (TextView) findViewById(R.id.YText);

        int resID = getResources().getIdentifier("greenbox", "drawable",  getPackageName());
        ImageView iw= (ImageView)findViewById(R.id.imageView);
        iw.getLayoutParams().height = 200;
        iw.getLayoutParams().width = 200;

        iw.setImageResource(resID);



    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                            Toast.makeText(MainActivity.this, "Error when get data", Toast.LENGTH_SHORT).show();



                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        textViewShape.setText(pj.getDetails().Shape);
        textViewColour.setText(pj.getDetails().Colour);
        textViewX.setText(pj.getDetails().Position_X);
        textViewY.setText(pj.getDetails().Position_Y);
        String Name=pj.getDetails().Message;


        int resID = getResources().getIdentifier(Name, "drawable",  getPackageName());
        ImageView iw= (ImageView)findViewById(R.id.imageView);
        iw.getLayoutParams().height = 200;
        iw.getLayoutParams().width = 200;

        iw.setImageResource(resID);

    }

    @Override
    public void onClick(View v) {
        sendRequest();
    }




}
