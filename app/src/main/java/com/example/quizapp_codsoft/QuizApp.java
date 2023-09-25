package com.example.quizapp_codsoft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizApp extends AppCompatActivity {

    CardView toc,computer,dsa,java;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_app);


        toc = (CardView) findViewById(R.id.toc_cardview);
        computer = (CardView) findViewById(R.id.computer_cardview);
        dsa = (CardView) findViewById(R.id.dsa_cardview);
        java = (CardView) findViewById(R.id.java_cardview);


        toc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizApp.this,SetsActivity.class);
                startActivity(intent);
            }
        });

    }
}