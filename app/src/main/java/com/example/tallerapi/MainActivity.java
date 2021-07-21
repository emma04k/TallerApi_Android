package com.example.tallerapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    String url = "https://api.nytimes.com/svc/news/v3/content/nyt/world.json?api-key=CE7wC6QnriOA2mFmMXxOBda1IehLvgPJ";
    ArrayList<Article> cs=new ArrayList<>();

    Button btnConnect, btnList;
    TextView textShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConnect = findViewById(R.id.btnConnect);
        btnList = findViewById(R.id.btnList);
        textShow = findViewById(R.id.txtShow);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestApiData();
                textShow.setText("API connected");
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),ArticleList.class);
                i.putParcelableArrayListExtra("articles",cs);
                startActivity(i);
            }
        });


        requestApiData();
    }

    private void requestApiData(){
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            parserJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("api-key", "CE7wC6QnriOA2mFmMXxOBda1IehLvgPJ");
                return headers;
            }
        };

        queue.add(request);


    }

    public void parserJson(JSONObject response){
        try {
            String string = " ";
            JSONArray data = response.getJSONArray("results");
            int x = 0;
            for (int i = 0; i<data.length(); i++){
                JSONObject info = data.getJSONObject(i);
                String title = info.getString("title");
                String section = info.getString("section");
                String subsection = info.getString("subsection");
                String date = info.getString("published_date");
                String url = info.getString("url");

                string = string + title+ "," + section + "," + subsection +","+ date +"," + url + "\n";
                x=x+1;
                Article art = new Article(title,section,subsection,date,url);
                cs.add(art);


            }


        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}