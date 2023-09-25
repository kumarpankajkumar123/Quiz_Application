package com.example.quizapp_codsoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quizapp-codsoft-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_design);

        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText password = (EditText) findViewById(R.id.password);

        final Button button_login = (Button) findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phone_text = phone.getText().toString();
                final String password_text = password.getText().toString();

                if(phone_text.isEmpty() || password_text.isEmpty()){
                    Toast.makeText(Login.this, "please enter email and password correctly ", Toast.LENGTH_SHORT).show();
                }
                else{

                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phone_text)) {
                                final String getPassword = snapshot.child(phone_text).child("password").getValue(String.class);

                                if (getPassword.equals(password_text)) {
                                    Toast.makeText(Login.this, "Login successfully ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this,QuizApp.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Login.this, " wrong password ", Toast.LENGTH_SHORT).show();
                                }
                            }
                                else{
                                Toast.makeText(Login.this, "wrong mobile number ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

        final TextView bottom_nav_second = (TextView) findViewById(R.id.bottom_nav_second);
        bottom_nav_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}