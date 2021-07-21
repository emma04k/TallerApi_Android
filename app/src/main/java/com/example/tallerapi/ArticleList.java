package com.example.tallerapi;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ArticleList extends AppCompatActivity {
    ArrayList<Article> art = new ArrayList<>();
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        Intent i = getIntent();
        art =  i.getParcelableArrayListExtra("articles");
        list = findViewById(R.id.list_article);
        if (art!=null && art.size()>0){
            ArticleAdapter adapter = new ArticleAdapter(this, art);
            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }

    }
}
