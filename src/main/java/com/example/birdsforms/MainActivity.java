package com.example.birdsforms;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textViewLatitude, textViewLongtitude;
    private Button b;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLatitude = findViewById(R.id.latitude);
        textViewLongtitude = findViewById(R.id.longtitude);
        b = findViewById(R.id.button);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                textViewLongtitude.setText(String.valueOf(location.getLongitude()));
                textViewLatitude.setText(String.valueOf(location.getLatitude()));
            }
        });
        b.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                    LocationManager loc = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                    android.location.Location gpsLoc;
                    double lat, lng;

                if (
                        android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ||
                        (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                   ) {
                            Toast.makeText(MainActivity.this, "here it executes", Toast.LENGTH_SHORT).show();
                            gpsLoc = (Location) loc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            lat = gpsLoc.getLatitude();
                            lng = gpsLoc.getLongitude();
                            textViewLongtitude.setText(Double.toString(lng));
                            textViewLatitude.setText(Double.toString(lat));
                    }
            }
        });
    }

}