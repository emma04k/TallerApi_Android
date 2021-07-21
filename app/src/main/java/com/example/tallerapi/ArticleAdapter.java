package com.example.tallerapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ArticleAdapter extends  ArrayAdapter<Article>{

    public ArticleAdapter(@NonNull Context context, @NonNull ArrayList<Article> articles) {
        super(context, 0, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article art = getItem(position);

        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_article, parent, false);
        }
        TextView title= (TextView)  convertView.findViewById(R.id.txtTitle);
        TextView date= (TextView)  convertView.findViewById(R.id.txtDate);
        TextView section= (TextView)  convertView.findViewById(R.id.txtSection);
        TextView subsection= (TextView)  convertView.findViewById(R.id.txtSubsection);
        TextView url = (TextView)  convertView.findViewById(R.id.txtUrl);

        title.setText("Titulo:  "+ art.getTitle());
        section.setText("Section:  "+ art.getSection());
        subsection.setText("Subsection:  "+ art.getSubSection());
        date.setText("Fecha publicacion:  "+ art.getDate());
        url.setText("Url: "+ art.getUrl());

        return convertView;
    }
}
