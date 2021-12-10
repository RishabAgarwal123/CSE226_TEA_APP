package com.example.cse226_end_term;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cse226_end_term.database.LoginDBHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText loginPassword,loginName;
    private Button loginButton;
    private ProgressDialog loadingBar;
    LoginDBHelper dbHelper;
    private SQLiteDatabase db;

    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        loginPassword =  findViewById(R.id.loginPassword);
        loginName = findViewById(R.id.loginName);
        loadingBar = new ProgressDialog(this);
        dbHelper = new LoginDBHelper(this);
        db = dbHelper.getReadableDatabase();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginName.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                if (name.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Enter your Name and Password to login", Toast.LENGTH_SHORT).show();
                } else {
                    cursor = db.rawQuery("SELECT * FROM " + LoginDBHelper.TABLE_NAME + " WHERE " + LoginDBHelper.COL_2 + "=? AND " + LoginDBHelper.COL_5 + "=?", new String[]{name, password});
                    if (cursor != null) {
                        if (cursor.getCount() > 0) {
                            Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ItemActivity.class));

                        } else {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    public static String validate(String userName, String password)
    {
        if(userName.equals("rishab") && password.equals("rishab"))
            return "Login was successful";
        else
            return "Invalid login!";
    }

}

