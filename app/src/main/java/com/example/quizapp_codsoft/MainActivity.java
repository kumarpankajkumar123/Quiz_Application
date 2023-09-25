package com.example.quizapp_codsoft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler;

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView_splash);
        textView =(TextView) findViewById(R.id.textview_splash);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fedin);



        imageView.startAnimation(animation);
        textView.startAnimation(animation);



       handler = new Handler();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(MainActivity.this,Home_Activity.class);
               startActivity(intent);
               finish();
           }
       },2500);
    }
}