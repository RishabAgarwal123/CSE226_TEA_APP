package com.example.cse226_end_term;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.cse226_end_term.database.LoginDBHelper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    private EditText registerName ,registerPhone ,registerPassword,registerLocation;
    private ProgressDialog loadingBar;
    private ImageView locationImage;
    double dlatitude, dlongitude;
    Location lastLocation;
    LoginDBHelper openHelper;
    private SQLiteDatabase db;

    FusedLocationProviderClient myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName = findViewById(R.id.registerName);
        registerPassword = findViewById(R.id.registerPassword);
        registerPhone = findViewById(R.id.registerPhone);
        registerButton = findViewById(R.id.registerButton);
        registerLocation = findViewById(R.id.registerLocation);
        locationImage = findViewById(R.id.locationImage);

        openHelper = new LoginDBHelper(this);

        myClient = LocationServices.getFusedLocationProviderClient(this);

        locationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLocationPermission();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String fname = registerName.getText().toString().trim();
                String fPhone = registerPhone.getText().toString().trim();
                String fAddress = registerLocation.getText().toString().trim();
                String fPassword = registerPassword.getText().toString().trim();
                if (fname.isEmpty() || fPassword.isEmpty() || fAddress.isEmpty() || fPhone.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    insertData(fname,fPhone,fAddress,fPassword);
                    Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), ItemActivity.class));
                }
            }
        });


    }

    public void insertData(String fname, String fPhone, String address, String fPassword){
        ContentValues contentValues = new ContentValues();
        contentValues.put(LoginDBHelper.COL_2,fname);
        contentValues.put(LoginDBHelper.COL_3,fPhone);
        contentValues.put(LoginDBHelper.COL_4,address);
        contentValues.put(LoginDBHelper.COL_5,fPassword);

        db.insert(LoginDBHelper.TABLE_NAME,null,contentValues);
    }

    private void checkLocationPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            Toast.makeText(this, "Location Permission already granted", Toast.LENGTH_SHORT).show();

            myClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null) {
                        lastLocation = location;
                        dlatitude = lastLocation.getLatitude();
                        dlongitude = lastLocation.getLongitude();


                        Geocoder geocoder = new Geocoder(RegisterActivity.this, Locale.getDefault());
                        try {
                            List<Address> locationList  = geocoder.getFromLocation(dlatitude,dlongitude,1);
                            if(locationList.size() > 0) {
                                Address address = locationList.get(0);
                                registerLocation.setText("" + address);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Location Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
