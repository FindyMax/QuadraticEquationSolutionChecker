package com.example.quadraticequationsolutionchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private float a, b, c;
    private int x1, x2;

    private TextView output;
    private EditText input1, input2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        button = findViewById(R.id.button);

        randomCoefficient(100);

        output.setText(a + "x^2+" + b + "x+" + c + "=0");
        x1 = Math.round((-b-Math.round(Math.sqrt(b*b-4*a*c)))/(2*a));
        x2 = Math.round((-b+Math.round(Math.sqrt(b*b-4*a*c)))/(2*a));

        input1.setOnFocusChangeListener(focusListener);
        input2.setOnFocusChangeListener(focusListener);
        button.setOnClickListener(listener);
    }

    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            if (view.getId() == R.id.input1) {
                if (!b) {
                    int inX1 = Integer.parseInt(input1.getText().toString());
                    if (inX1 != x1) {
                        input1.setTextColor(Color.RED);
                        Toast.makeText(MainActivity.this, "Введено неправильное значение x1", Toast.LENGTH_SHORT).show();
                    } else {
                        input1.setTextColor(0xFF177C3A);
                    }
                }
            } else if (view.getId() == R.id.input2) {
                if (!b) {
                    int inX2 = Integer.parseInt(input2.getText().toString());
                    if (inX2 != x2) {
                        input2.setTextColor(Color.RED);
                        Toast.makeText(MainActivity.this, "Введено неправильное значение x2", Toast.LENGTH_SHORT).show();
                    } else {
                        input2.setTextColor(0xFF177C3A);
                    }
                }
            }
        }
    };

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int inX1 = Integer.parseInt(input1.getText().toString());
            int inX2 = Integer.parseInt(input2.getText().toString());

            if (inX1 == x1 && inX2 == x2) {
                randomCoefficient(100);

                output.setText(a + "x^2+" + b + "x" + c + "=0");
                x1 = Math.round((-b-Math.round(Math.sqrt(b*b-4*a*c)))/(2*a));
                x2 = Math.round((-b+Math.round(Math.sqrt(b*b-4*a*c)))/(2*a));
            } else {
                Toast.makeText(MainActivity.this, "Текущее решение не правильное", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void randomCoefficient (int limit) {
        Random random = new Random();
        a = random.nextInt(limit);
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }
}