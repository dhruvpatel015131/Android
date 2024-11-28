package com.example.practicalthree;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.practicalthree.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name, et_email, et_dob;
    private RadioGroup rg_gender;
    private RadioButton radioGenderButton;
    private CheckBox ck1, ck2, ck3;
    private Button submit_bt;
    private String nameStr, emailStr, genderStr, dobStr, techStr = "";
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_dob = findViewById(R.id.et_dob);
        rg_gender = findViewById(R.id.rg_gender);
        ck1 = findViewById(R.id.ck1);
        ck2 = findViewById(R.id.ck2);
        ck3 = findViewById(R.id.ck3);
        submit_bt = findViewById(R.id.submit_bt);


        submit_bt.setOnClickListener(this);
        ck1.setOnClickListener(this);
        ck2.setOnClickListener(this);
        ck3.setOnClickListener(this);
        et_dob.setOnClickListener(this);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.submit_bt) {
            try {
                // Validate name
                nameStr = et_name.getText().toString().trim();
                if (nameStr.isEmpty()) {
                    throw new Exception("Name is required");
                }

                // Validate email
                emailStr = et_email.getText().toString().trim();
                if (emailStr.isEmpty()) {
                    throw new Exception("Email is required");
                }

                // Validate gender
                int selectedId = rg_gender.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    throw new Exception("Gender is required");
                }
                radioGenderButton = findViewById(selectedId);
                genderStr = radioGenderButton.getText().toString();

                // Validate Date of Birth
                dobStr = et_dob.getText().toString().trim();
                if (dobStr.isEmpty()) {
                    throw new Exception("Date of Birth is required");
                }

                // Validate technology selection
                if (!ck1.isChecked() && !ck2.isChecked() && !ck3.isChecked()) {
                    throw new Exception("At least one technology must be selected");
                }

                // Show confirmation
                Toast.makeText(this, "Name: " + nameStr + " Email: " + emailStr + " Gender: " + genderStr + " Tech:" + techStr + " Date of Birth: " + dobStr, Toast.LENGTH_LONG).show();

                // Pass data to next activity
                Intent i = new Intent(this, OurResult.class);
                i.putExtra("name", nameStr);
                i.putExtra("email", emailStr);
                i.putExtra("gender", genderStr);
                i.putExtra("tech", techStr);
                i.putExtra("dob", dobStr);
                startActivity(i);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.ck1) {
            if (ck1.isChecked()) {
                techStr += "PHP ";
            } else {
                techStr = techStr.replace("PHP ", "");
            }
        } else if (id == R.id.ck2) {
            if (ck2.isChecked()) {
                techStr += "Android ";
            } else {
                techStr = techStr.replace("Android ", "");
            }
        } else if (id == R.id.ck3) {
            if (ck3.isChecked()) {
                techStr += "iOS ";
            } else {
                techStr = techStr.replace("iOS ", "");
            }
        } else if (id == R.id.et_dob) {
            Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
                dobStr = day + "-" + (month + 1) + "-" + year; // Set the selected date to dobStr
                et_dob.setText(dobStr); // Display the selected date in the EditText
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }
}
