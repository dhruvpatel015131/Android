package com.example.pracsix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {
    public static final String savedPref = "myPref";
    public static final String savedName = "UsrName";
    public static final String savedPass = "UsrPass";
    SharedPreferences sharedPreferences;
    EditText txtUsrNm, txtPassword;

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
        txtUsrNm = findViewById(R.id.txtUsrNm);
        txtPassword = findViewById(R.id.txtPassword);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(view -> {
            CheckBox chkBxRemMe = findViewById(R.id.chkBxRemMe);
            if(chkBxRemMe.isChecked()){
                saveSharedPref(view);
            }
            Intent intent = new Intent(MainActivity.this,FileHandling.class);
            MainActivity.this.startActivity(intent);
        });
    }
    public void saveSharedPref(View view){
      String nm = txtUsrNm.getText().toString();
      String pass = txtPassword.getText().toString();
      sharedPreferences = getSharedPreferences(savedPref, MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString(savedName,nm);
      editor.putString(savedPass,pass);
      editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(savedPref, MODE_PRIVATE);
        String nm = sharedPreferences.getString(savedName,"");
        String pass = sharedPreferences.getString(savedPass,"");
        txtUsrNm.setText(nm);
        txtPassword.setText(pass);
    }

    @Override
    protected void onDestroy() {
        sharedPreferences = getSharedPreferences(savedPref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        finish();
        super.onDestroy();
    }
    
}