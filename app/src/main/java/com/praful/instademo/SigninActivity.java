package com.praful.instademo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    Button signup,signin;

    EditText usernameET, passwordET;

    String uname = "praful", pwd = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signup = (Button)findViewById(R.id.signup);
        signin = (Button)findViewById(R.id.signin);
        usernameET = (EditText)findViewById(R.id.username);
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

                String s1 = usernameET.getText().toString();
                String s2 = passwordET.getText().toString();

                if (s1.equals(uname) && s2.equals(pwd)){

                    Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SigninActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(SigninActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
