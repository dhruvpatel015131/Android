package com.example.calculator;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText num1;
    Button add;
    Button sub;
    Button mul;
    Button div;
    EditText num2;
    Button reset;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        num1 = findViewById(R.id.editText);
        num2 = findViewById(R.id.editText2);
        add = findViewById(R.id.button);
        sub = findViewById(R.id.button1);
        mul = findViewById(R.id.button2);
        div = findViewById(R.id.button4);
        textView = findViewById(R.id.textView);
        reset = findViewById(R.id.button5);

        num1.requestFocus();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }


    private void animateTextView(TextView textView) {
        // Scale animation
        textView.setScaleX(0);
        textView.setScaleY(0);
        textView.animate()
                .scaleX(1)
                .scaleY(1)
                .setDuration(300)
                .start();

        // Color change animation
        int colorFrom = getResources().getColor(R.color.default_text_color);
        int colorTo = getResources().getColor(R.color.highlight_text_color);
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(300); // duration in milliseconds
        colorAnimation.addUpdateListener(animator -> textView.setTextColor((int) animator.getAnimatedValue()));
        colorAnimation.start();
    }

    public void Add(View v) {
        try {

            String n1 = num1.getText().toString();
            double num1 = Double.parseDouble(n1);
            String n2 = num2.getText().toString();
            double num2 = Double.parseDouble(n2);
            double sum = num1 + num2;
            textView.setText("Addition is: " + sum);
            textView.setVisibility(View.VISIBLE);
            animateTextView(textView);

            Toast.makeText(this, "Addition performed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: Enter Values" , Toast.LENGTH_SHORT).show();
        }
    }

    public void Sub(View v) {

        try {
            String n1 = num1.getText().toString();
            double num1 = Double.parseDouble(n1);
            String n2 = num2.getText().toString();
            double num2 = Double.parseDouble(n2);
            double sub = num1 - num2;
            textView.setText("Subtraction is: " + sub);
            textView.setVisibility(View.VISIBLE);
            animateTextView(textView);
            Toast.makeText(this, "Subtraction performed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: Enter Values", Toast.LENGTH_SHORT).show();
        }
    }

    public void Multi(View v) {

        try {
            String n1 = num1.getText().toString();
            double num1 = Double.parseDouble(n1);
            String n2 = num2.getText().toString();
            double num2 = Double.parseDouble(n2);
            double multi = num1 * num2;
            textView.setText("Multiplication is: " + multi);
            textView.setVisibility(View.VISIBLE);
            animateTextView(textView);
            Toast.makeText(this, "Multiplication performed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: Enter Values" , Toast.LENGTH_SHORT).show();
        }
    }

    public void Div(View v) {

        try {
            String n1 = num1.getText().toString();
            double num1 = Double.parseDouble(n1);
            String n2 = num2.getText().toString();
            double num2 = Double.parseDouble(n2);
            double div = num1 / num2;
            textView.setText("Division is: " + div);
            textView.setVisibility(View.VISIBLE);
            animateTextView(textView);
            Toast.makeText(this, "Division performed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: Enter Values", Toast.LENGTH_SHORT).show();
        }
    }

    public void reset(View v) {

        try {
            num1.setText("");
            num2.setText("");
            textView.setText("");
            num1.requestFocus();
            Toast.makeText(this, "Reset performed", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: Cannot Reset", Toast.LENGTH_SHORT).show();
        }
    }


}
