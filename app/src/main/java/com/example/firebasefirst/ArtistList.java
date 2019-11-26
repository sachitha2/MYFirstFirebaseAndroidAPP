package com.example.firebasefirst;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<Artits> {
    private Activity context;
    private List<Artits> artistList;

    public  ArtistList(Activity context,List<Artits> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context = context;
        this.artistList = artistList;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout,null,true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewGenre = listViewItem.findViewById(R.id.textViewGenre);

        Artits artits = artistList.get(position);

        textViewName.setText(artits.getName());
        textViewGenre.setText(artits.getGenre());

        return  listViewItem;
    }
}
