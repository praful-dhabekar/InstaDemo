package com.praful.instademo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                int i = v.getId();
                if(i == R.id.reg_signup){
                    createAccount(emailET.getText().toString(),passwordET.getText().toString());
                }
                /*else if (i == R.id.signin){
                        signIn(emailET.getText().toString(), passwordET.getText().toString());
                }*/
            }
        });

    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    //passing email and password arguments
    private void createAccount(final String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        //Create user with email and passsword
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()){
                        //Sign up success
                        Log.d(TAG,"createUserWithEmailAndPassword: success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        String useremail = emailET.getText().toString();
                        Toast.makeText(SignupActivity.this, "User Successfully Registered with "+useremail, Toast.LENGTH_LONG).show();
                    }
                    else {
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(SignupActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    /*private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;



        }*/
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
