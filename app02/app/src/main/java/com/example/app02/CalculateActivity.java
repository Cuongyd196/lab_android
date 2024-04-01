package com.example.app02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculateActivity extends AppCompatActivity {

    private EditText editTextNumberA;
    private EditText editTextNumberB;
    private Button buttonAdd;
    private Button buttonSubtract;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonBack;
    private Button buttonReset;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculate);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        editTextNumberA = findViewById(R.id.editTextNumberA);
        editTextNumberB = findViewById(R.id.editTextNumberB);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide = findViewById(R.id.buttonReset);
        buttonBack = findViewById(R.id.buttonBack);
        textViewResult = findViewById(R.id.textViewResult);

        // Set click listeners for arithmetic buttons
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumberA.setText("");
                editTextNumberB.setText("");
                textViewResult.setText("Kết quả:");
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalculateActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close
            }
        });
    }
    private void calculate(char operator) {
        double numA = Double.parseDouble(editTextNumberA.getText().toString());
        double numB = Double.parseDouble(editTextNumberB.getText().toString());
        double result = 0;

        switch (operator) {
            case '+':
                result = numA + numB;
                break;
            case '-':
                result = numA - numB;
                break;
            case '*':
                result = numA * numB;
                break;
            case '/':
                if (numB != 0) {
                    result = numA / numB;
                } else {
                    textViewResult.setText("Không thể chia cho 0");
                    return;
                }
                break;
        }
        textViewResult.setText("Kết quả: " + result);
    }
}
