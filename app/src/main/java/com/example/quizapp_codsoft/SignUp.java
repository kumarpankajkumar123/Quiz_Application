package com.example.quizapp_codsoft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class SignUp extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://quizapp-codsoft-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        final EditText full_name = (EditText) findViewById(R.id.name);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText email_fire = (EditText) findViewById(R.id.email_2);
        final EditText password = (EditText) findViewById(R.id.password2);
        final EditText conPassword = (EditText) findViewById(R.id.confirm_password);
        final Button signupBtn = (Button) findViewById(R.id.button_signup);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fullName_text = full_name.getText().toString();
                final String phoneTxt = phone.getText().toString();
                final String emailTxt = email_fire.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String conPasswordTxt = conPassword.getText().toString();

                if(fullName_text.isEmpty() || phoneTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || conPasswordTxt.isEmpty()){
                    Toast.makeText(SignUp.this, "please fill all the details", Toast.LENGTH_SHORT).show();
                } else if (!passwordTxt.equals(conPasswordTxt)) {
                    Toast.makeText(SignUp.this, "password does not match with confirm password ", Toast.LENGTH_SHORT).show();
                }

                else{
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(SignUp.this, "phone number already registered please login ", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                databaseReference.child("user").child(phoneTxt).child("full name").setValue(fullName_text);
                                databaseReference.child("user").child(phoneTxt).child("email Address").setValue(emailTxt);
                                databaseReference.child("user").child(phoneTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(SignUp.this, "Signup Successfully ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUp.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

       final TextView bottom_signup_second = (TextView) findViewById(R.id.bottom_signup_second);
       bottom_signup_second.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(SignUp.this, Login.class);
               startActivity(intent);
               finish();
           }
       });

    }

}