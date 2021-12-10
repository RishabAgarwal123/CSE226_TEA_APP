package com.example.cse226_end_term;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button mSignUpButton, mLoginButton;
    private ImageView mIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSignUpButton = (Button) findViewById(R.id.signup_button);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mIcon = (ImageView) findViewById(R.id.icon);







        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signupIntent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(signupIntent);
            }
        });
    }

}
