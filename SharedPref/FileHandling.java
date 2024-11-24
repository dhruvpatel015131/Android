package com.example.pracsix;


import static com.example.pracsix.MainActivity.savedPref;
import static com.example.pracsix.MainActivity.savedName;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FileHandling extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_handling);
        getSharedRef();
        Button btnClear = findViewById(R.id.button);
        btnClear.setOnClickListener(view -> clearSharedRef());
    }

    @SuppressLint("SetTextI18n")
    private void getSharedRef() {
        TextView txtVwInfo = findViewById(R.id.txtVwInfo);
        sharedPreferences = getSharedPreferences(savedPref, MODE_PRIVATE);
        String name = sharedPreferences.getString(savedName,"No User Name");
      //  String password = sharedPreferences.getString(savedPass,"No Password");
        txtVwInfo.setText("Your Username is: "+name);
    }


    private void clearSharedRef() {
        sharedPreferences = getSharedPreferences(savedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit().clear();
        editor.apply();
        editor.commit();
        Toast.makeText(this, "Shared Preferences is deleted successfully",Toast.LENGTH_SHORT).show();
        getSharedRef();
    }



}
