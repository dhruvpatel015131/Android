package com.example.database;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText rno, name, email, contact, course, address;
    TextView output;
    MyDBHandler dbHandler;
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
        rno = findViewById(R.id.rollNo);
        name = findViewById(R.id.txtName);
        email = findViewById(R.id.txtEmail);
        contact = findViewById(R.id.txtcontact);
        course = findViewById(R.id.txtCourse);
        address = findViewById(R.id.txtAddress);
        output = findViewById(R.id.result);
        dbHandler = new MyDBHandler(this);
    }

    public void loadStudents(View view){
        output.setText(dbHandler.loadHandler());
        rno.setText("");
        name.setText("");
        email.setText("");
        contact.setText("");
        course.setText("");
        address.setText("");
    }

    //insert method
    public void addStudents(View view){
        if(!rno.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !contact.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !address.getText().toString().isEmpty()){
            int id = Integer.parseInt(rno.getText().toString());
            String nm = name.getText().toString();
            String em = email.getText().toString();
            String ad = address.getText().toString();
            String cr = course.getText().toString();
            int cn = Integer.parseInt(contact.getText().toString());
            Student student = new Student(id, nm, em, cn, cr, ad);
            long insertId = dbHandler.addHandler(student);
            output.setText(R.string.record_inserted_sucessfully);
            if(insertId == -1){
                output.setText(R.string.record_already_exists);
            }
            else{
                rno.setText("");
                name.setText("");
                email.setText("");
                contact.setText("");
                course.setText("");
                address.setText("");
            }
        }
        else{
            output.setText(R.string.please_enter_all_feilds);

        }
    }
    //update method
    public void updateStudents(View view){
        if(!rno.getText().toString().isEmpty() && !name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !contact.getText().toString().isEmpty() && !course.getText().toString().isEmpty() && !address.getText().toString().isEmpty()) {
            boolean result = dbHandler.updateHandler(Integer.parseInt(rno.getText().toString()), name.getText().toString(), email.getText().toString(),course.getText().toString(), Integer.parseInt(contact.getText().toString()) , address.getText().toString());
            if(result){
                rno.setText("");
                name.setText("");
                email.setText("");
                contact.setText("");
                course.setText("");
                address.setText("");
                output.setText(R.string.record_updated_sucessfully);

            }
            else{
                output.setText(R.string.record_not_found);
            }
        }
        else{
            output.setText(R.string.please_enter_all_feilds);
        }
    }

    //delete record
    public void deleteStudents(View view){
        if(!rno.getText().toString().isEmpty()){
            boolean result = dbHandler.deleteHandler(Integer.parseInt(rno.getText().toString()));
            if(result){
                rno.setText("");
                name.setText("");
                email.setText("");
                contact.setText("");
                course.setText("");
                address.setText("");
                output.setText(R.string.record_deleted_sucessfully);
            }
            else{
                output.setText(R.string.record_not_found);
            }
        }
        else{
            output.setText(R.string.please_enter_all_feilds);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dbHandler.close();
    }


}