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

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    EditText emailET,passwordET;
    Button signupBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        emailET = (EditText)findViewById(R.id.reg_email);
        passwordET = (EditText)findViewById(R.id.reg_password);

        signupBtn = (Button)findViewById(R.id.reg_signup);


        //set OnClickListener on signup button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                attemptSignup();
            }
        });

    }

    public  void attemptSignup(){
        if (!validateForm()) {
            return;
        }
        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Intent i1 = new Intent(SignupActivity.this, EditAccountActivity.class);
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
