package com.example.practicaltwo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class myadpter extends ArrayAdapter<String> {
    private  final String[] arr;

//    @SuppressLint("ViewHolder")


    @SuppressLint("ViewHolder")
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.mylayout,parent, false);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(getItem(position));
        return convertView;
    }
    @NonNull
    @Override
    public String getItem(int position){return  arr[position];}

    public myadpter(@NonNull Context context, int resource, @NonNull String[] arr) {
        super(context, resource,arr);
        this.arr = arr;
    }



}
