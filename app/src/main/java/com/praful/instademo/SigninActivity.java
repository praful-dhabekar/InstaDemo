package com.praful.instademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    Button signup,signin;

    EditText emailET, passwordET;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        signup = (Button)findViewById(R.id.signup);
        signin = (Button)findViewById(R.id.signin);
        emailET = (EditText)findViewById(R.id.username);
        passwordET = (EditText)findViewById(R.id.password);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });



        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    attemptSignin();

            }
        });
    }

    public void attemptSignin(){
        if (!validateForm()) {
            return;
        }
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Intent i1 = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(i1);
            }
        });

    }

    public boolean validateForm(){
        boolean valid = true;

        String email = emailET.getText().toString();
        if (TextUtils.isEmpty(email)){
            emailET.setError("Required");
            valid = false;
        }
        else{

            emailET.setError(null);
        }

        String password = passwordET.getText().toString();
        if (TextUtils.isEmpty(password)){
            passwordET.setError("Required");
            valid = false;
        }
        else{

            passwordET.setError(null);
        }

        return valid;
    }
}
