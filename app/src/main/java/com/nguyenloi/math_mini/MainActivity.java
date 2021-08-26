package com.nguyenloi.math_mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnSub, btnMul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();
        setControl();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_start.class);
                intent.putExtra("caculator","add");
                startActivity(intent);
                finish();
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_start.class);
                intent.putExtra("caculator","mul");
                startActivity(intent);
                finish();
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, game_start.class);
                intent.putExtra("caculator","sub");
                startActivity(intent);
                finish();
            }
        });
    }

    private void setControl() {
        btnAdd=findViewById(R.id.btnAdd);
        btnMul=findViewById(R.id.btnMul);
        btnSub=findViewById(R.id.btnSub);
    }
}