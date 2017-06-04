package com.example.etake.safealert;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;

import static com.example.etake.safealert.R.layout.options;

public class Options extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Options mContext;
    private Location lastLocation;
    public static final int REQ_PERMISSION = 1;
    private TextView textLat, textLong;
    private static final String HASH = Options.class.getSimpleName();
    private GoogleApiClient googleApiClient;
    String key;
    String key2;
    String key3;
    String key4;
    String key5;
    String key6;
    String key7;
    String key8;
    String key9;
    LocationData currentLocation;
    Intent next;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.options);
        Button one = (Button) findViewById(R.id.button);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) findViewById(R.id.button2);
        two.setOnClickListener(this);
        Button three = (Button) findViewById(R.id.button3);
        three.setOnClickListener(this);
        Button four = (Button) findViewById(R.id.button4);
        four.setOnClickListener(this);
        Button five = (Button) findViewById(R.id.button5);
        five.setOnClickListener(this);
        Button six = (Button) findViewById(R.id.button6);
        six.setOnClickListener(this);
        Button seven = (Button) findViewById(R.id.button7);
        seven.setOnClickListener(this);
        Button eight = (Button) findViewById(R.id.button8);
        eight.setOnClickListener(this);
        Button nine = (Button) findViewById(R.id.button9);
        nine.setOnClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent next = new Intent(Options.this, MainActivity.class);
                startActivity(next);
            }

        });
        createGoogleApi();
        connectGoogleApiClient();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();


        // test code
        //double longNow = 10; // replace with gps coord
        //double latNow = 10;
        //String dangerNow = "SAFE";  // replace with value obtain from button

        //LocationData currentLoad = new LocationData(longNow, latNow, dangerNow);
        //databaseReference.child("users").child("tyty").setValue(currentLoad);  // send to server

    }


    private void createGoogleApi() {
        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            Log.d(HASH, "Client is null");
        }
    }

    private void connectGoogleApiClient() {
        if (googleApiClient != null) {
            if (!(googleApiClient.isConnected() || googleApiClient.isConnecting())) {
                googleApiClient.connect();
            } else {
                Log.d(HASH, "Client is connected");
                startLocationUpdates();
            }
        } else {
            Log.d(HASH, "Client is null");
        }
    }

    @Override
    public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button:
                        if(key == null) {
                            getLastKnownLocation();
                            currentLocation = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Rape");
                            key = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key).setValue(currentLocation);


                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key).removeValue();
                                    key = null;
                                }

                            }, 10000);
                            /*new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    next = null;
                                    next = new Intent(Options.this, MainActivity.class);
                                    startActivity(next);
                                }

                            }, 5000);*/
                            //Intent next = new Intent(Options.this, MainActivity.class);
                            //startActivity(next);

                        }
                        break;

                    case R.id.button2:
                        if(key2 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation2 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Shooting");
                            key2 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key2).setValue(currentLocation2);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key2).removeValue();
                                    key2 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button3:
                        if(key3 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation3 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Robbery");
                            key3 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key3).setValue(currentLocation3);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key3).removeValue();
                                    key3 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button4:
                        if(key4 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation4 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Kidnapping");
                            key4 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key4).setValue(currentLocation4);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key4).removeValue();
                                    key4 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button5:
                        if(key5 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation5 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Fire");
                            key5 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key5).setValue(currentLocation5);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key5).removeValue();
                                    key5 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button6:
                        if(key6 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation6 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Stalker");
                            key6 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key6).setValue(currentLocation6);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key6).removeValue();
                                    key6 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button7:
                        if(key7 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation7 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Accident");
                            key7 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key7).setValue(currentLocation7);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key7).removeValue();
                                    key7 = null;
                                }

                            }, 10000);
                        }
                        break;

                    case R.id.button8:
                        if(key8 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation8 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Suicide");
                            key8 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key8).setValue(currentLocation8);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key8).removeValue();
                                    key8 = null;
                                }

                            }, 10000);
                        }

                        break;

                    case R.id.button9:
                        if(key9 == null) {
                            getLastKnownLocation();
                            LocationData currentLocation9 = new LocationData(lastLocation.getLongitude(), lastLocation.getLatitude(), "Other");
                            key9 = databaseReference.child("users").push().getKey();
                            databaseReference.child("users").child(key9).setValue(currentLocation9);  // send to server
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    databaseReference.child("users").child(key9).removeValue();
                                    key9 = null;
                                }

                            }, 10000);
                        }
                        break;

                    default:
                        break;
                }



    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        getLastKnownLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // Check for permission to access Location
    private boolean checkPermission() {
        // Ask for permission if it wasn't granted yet
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }

    // Asks for permission
    private void askPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION },
                REQ_PERMISSION
        );
    }

    // Verify user's response of the permission requested
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch ( requestCode ) {
            case REQ_PERMISSION: {
                if ( grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    // Permission granted
                    getLastKnownLocation();
                    // Add function to get server data and update map

                } else {
                    // Permission denied
                    permissionsDenied();
                }
                break;
            }
        }
    }

    // App cannot work without the permissions
    private void permissionsDenied() {
    }

    private void getLastKnownLocation() {
        if (checkPermission()) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            /*if (lastLocation != null) {
                startLocationUpdates();
            } else {

                startLocationUpdates();
            }*/
            Log.d(HASH, "wow");
        } else askPermission();

    }

    private LocationRequest locationRequest;
    // Defined in mili seconds.
    // This number in extremely low, and should be used only for debug
    private final int UPDATE_INTERVAL =  3 * 60 * 1000; // 5 seconds
    private final int FASTEST_INTERVAL = 5 * 1000;  // 5 secs
    private void startLocationUpdates(){
        locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL);




    }
    @Override
    protected void onStart() {
        super.onStart();

        // Call GoogleApiClient connection when starting the Activity
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Disconnect GoogleApiClient when stopping Activity
        googleApiClient.disconnect();
    }



}
