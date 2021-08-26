package com.nguyenloi.math_mini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class game_start extends AppCompatActivity {
    EditText edtAnswer;
    Button btnSubmit, btnNext;
    TextView txtScore, txtLife, txtTime, txtDisplay;
    int number1, number2, userAnswer, realAnswer=0, score = 0, life = 3;
    Random random = new Random();
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILLS = 10000;
    Boolean timerRunning;
    long time_left_in_mills = START_TIMER_IN_MILLS;
    String checkCalucator = "add";
    String cal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);
        this.getSupportActionBar().hide();
        setControl();
        btnNext.setEnabled(false);
        Intent nIntent = getIntent();
        checkCalucator = nIntent.getStringExtra("caculator");

        gameContinute();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    userAnswer = Integer.valueOf(edtAnswer.getText() + "");
                    pauseTimer();
                    if (realAnswer == userAnswer) {
                        txtDisplay.setText(R.string.rightAnswer);


                        score += 10;

                        txtScore.setText(score + "");
                    } else {
                        life--;
                        txtLife.setText(life + "");
                        txtDisplay.setText(number1 + " " + cal + " " + number2 + " = " + realAnswer);
                        Toast.makeText(game_start.this, R.string.tWrong, Toast.LENGTH_SHORT).show();
                    }
                    btnSubmit.setEnabled(false);
                    btnNext.setEnabled(true);
                } catch (Exception e) {
                    Toast.makeText(game_start.this, R.string.invalid, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNext.setEnabled(false);
                btnSubmit.setEnabled(true);
                resetTimer();
                if (life <= 0) {
                    Toast.makeText(game_start.this, R.string.tGameOver, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(game_start.this, result.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    finish();
                } else {
                    edtAnswer.setText("");
                    gameContinute();
                }
            }
        });


    }

    private void setControl() {
        edtAnswer = findViewById(R.id.edtAnswer);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);
        txtDisplay = findViewById(R.id.txtDisplay);
        txtLife = findViewById(R.id.txtLife);
        txtScore = findViewById(R.id.txtScore);
        txtTime = findViewById(R.id.txtTime);
    }

    private void gameContinute() {
        number1 = random.nextInt(50);
        number2 = random.nextInt(50);
        if (checkCalucator.equals("add")) {
            realAnswer = number1 + number2;
            cal = "+";
        }
        if (checkCalucator.equals("mul")) {
            realAnswer = number1 * number2;
            cal = "*";
        }
        if (checkCalucator.equals("sub")) {
            realAnswer = number1 - number2;
            cal = "-";
        }

        txtDisplay.setText(number1 + " " + cal + " " + number2);
        startTimer();
    }

    private void startTimer() {
        timer = new CountDownTimer(time_left_in_mills, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_mills = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                pauseTimer();
                updateText();
                resetTimer();
                life = life - 1;
                txtDisplay.setText(R.string.timeUp);
                txtLife.setText(life + "");
                btnNext.setEnabled(true);
            }
        }.start();
        timerRunning = true;
    }

    private void resetTimer() {
        time_left_in_mills = START_TIMER_IN_MILLS;
        updateText();
    }

    private void pauseTimer() {
        timer.cancel();
        timerRunning = false;
    }

    private void updateText() {
        int second = (int) (time_left_in_mills / 1000) % 60;
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        txtTime.setText(time_left);
    }
}