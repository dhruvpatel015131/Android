package com.example.fileoperations;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText edit = findViewById(R.id.et1);
        EditText edit2 = findViewById(R.id.et2);
        EditText edit3 = findViewById(R.id.et3);
        Button btnRead = findViewById(R.id.btnRead);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnWrite = findViewById(R.id.btnWrite);
        TextView output = findViewById(R.id.tvDisp);

        btnRead.setOnClickListener(view->{
            try {
                    StringBuilder result = new StringBuilder();
                    String line;
                    String folder = getApplication().getFilesDir().getAbsolutePath()+ File.separator+edit.getText().toString();
                    File subFolder = new File(folder);
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(folder,edit2.getText().toString())));
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    output.setText(result.toString());
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_LONG).show();
                output.setText(null);
            }
        });


        btnWrite.setOnClickListener(view->{
            try{
                String folder = getApplication().getFilesDir().getAbsolutePath()+ File.separator+edit.getText().toString();
                File subFolder = new File(folder);
                if(!subFolder.exists()){
                    subFolder.mkdir();
                }

                FileOutputStream outputStream = new FileOutputStream(new File(subFolder,edit2.getText().toString()));
                outputStream.write(edit3.getText().toString().getBytes());
                outputStream.close();
                Toast.makeText(getApplicationContext(), "Writing Successfully", Toast.LENGTH_LONG).show();
                output.setText(null);
                edit.setText(null);
                edit2.setText(null);
                edit3.setText(null);

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                output.setText(null);
            }
        });


        btnDelete.setOnClickListener(view->{
            try{
                String folder = getApplication().getFilesDir().getAbsolutePath()+ File.separator+edit.getText().toString();
                File subFolder = new File(folder);
                File file = new File(folder,edit2.getText().toString());
                while(file.exists()){
                    file.delete();
                }
                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_LONG).show();
                output.setText(null);
                edit.setText(null);
                edit2.setText(null);
                edit3.setText(null);

            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                output.setText(null);
            }
        });



    }
}