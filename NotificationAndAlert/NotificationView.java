package com.example.notificationandalert;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationView extends AppCompatActivity {
    TextView textView;
    Button alert;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_view);

        textView = findViewById(R.id.textview);
        alert = findViewById(R.id.alt);

        // getting the notification message
        String message = getIntent().getStringExtra("message");
        textView.setText(message);

        // Initializing the AlertDialog.Builder
        builder = new AlertDialog.Builder(this);

        // Setting OnClickListener for the button
        alert.setOnClickListener(v -> {
            // Build and set up the dialog
            builder.setMessage("Do you want to close this message?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", (dialog, id) -> {
                        finish(); // Closes the activity
                        Toast.makeText(getApplicationContext(), "You chose Yes!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", (dialog, id) -> {
                        dialog.cancel(); // Dismiss the dialog
                        Toast.makeText(getApplicationContext(), "You chose No!", Toast.LENGTH_SHORT).show();
                    });

            // Create and show the dialog when the button is clicked
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle("EXIT");
            alertDialog.show();
        });
    }
}
