package com.example.practicalthree;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

public class OurResult extends AppCompatActivity {
    TextView tv_name, tv_email, tv_gender, tv_tech, tv_dob;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_gender = findViewById(R.id.tv_gender);
        tv_tech = findViewById(R.id.tv_tech);
        tv_dob = findViewById(R.id.tv_dob);

        tv_name.setText(getIntent().getStringExtra("name"));
        tv_email.setText(getIntent().getStringExtra("email"));
        tv_gender.setText(getIntent().getStringExtra("gender"));
        tv_tech.setText(getIntent().getStringExtra("tech"));
        tv_dob.setText(getIntent().getStringExtra("dob"));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id == android.R.id.home)
        {
            Toast.makeText(getApplicationContext(),"Back",Toast.LENGTH_SHORT).show();
            finish();
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"Setting Selected",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(id == R.id.home)
        {
            Toast.makeText(getApplicationContext(),"Home Selected",Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
