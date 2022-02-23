/* 

Purpose: Create a Login page for the user to login into thier account through the use of GOOGLE FIREBASE
System: Android Studio
Language: JAVA
Author: Austin Wang
Date 2/01/2022


*/




package com.example.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    protected EditText mEmail, mPassword;
    protected Button mLogin_Btn;
    protected TextView mCreate_Btn;
    protected FirebaseAuth mAuth;
    protected ProgressBar progress_Bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        progress_Bar = findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        mLogin_Btn = findViewById(R.id.loginBtn);
        mCreate_Btn = findViewById(R.id.createText);

        mLoginBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6){
                    mPassword.setError("Password must be >= 6 characters");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //authenticate the user

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){

                        if(task.isSuccessful()){

                            Toast.makeText(Login.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(Login.this, "Error." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}