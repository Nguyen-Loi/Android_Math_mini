package com.nguyenloi.math_mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class result extends AppCompatActivity {

    Button btnPlayAgain, btnExit;
    TextView tvTotal;
    int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        this.getSupportActionBar().hide();
        setControl();
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        tvTotal.setText(score+"");
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAgain = new Intent(result.this, MainActivity.class);
                startActivity(intentAgain);
                finish();
            }
        });
    }

    private void setControl() {
        btnPlayAgain=findViewById(R.id.btnPlayAgain);
        btnExit=findViewById(R.id.btnExit);
        tvTotal=findViewById(R.id.tvTotal);
    }
}