package com.example.quizapp_codsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView Total_Attempt,Right_Answer,Wrong_Answer;
    Button retry,quite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Total_Attempt = (TextView) findViewById(R.id.Total_Attempt);
        Right_Answer = (TextView) findViewById(R.id.Right_Answer);
        Wrong_Answer = (TextView) findViewById(R.id.Wrong_Answer);

        retry = (Button) findViewById(R.id.retry);
        quite  = (Button) findViewById(R.id.quite);

        int totalScore = getIntent().getIntExtra("total",0);
        int correctAns = getIntent().getIntExtra("score",0);

        int wrong = totalScore - correctAns;

        Total_Attempt.setText(String.valueOf(totalScore));
        Right_Answer.setText(String.valueOf(correctAns));
        Wrong_Answer.setText(String.valueOf(wrong));

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        quite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}