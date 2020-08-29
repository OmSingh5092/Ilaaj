package com.codenite.ilaaj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.codenite.ilaaj.R;
import com.codenite.ilaaj.utils.SharedPrefs;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    SharedPrefs prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prefs = new SharedPrefs(this);

        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        if(true){
                            Log.i("SPLASH SCREEN INTENT", "Not logged in");
                            Intent login = new Intent(SplashActivity.this, SignInActivity.class);
                            startActivity(login);
                            finish();
                        }else{
                            if(prefs.isDoctor()){
                                if(prefs.getNewUser()){
                                    Log.i("SPLASH SCREEN INTENT", "Logged in");
                                    Intent main = new Intent(SplashActivity.this,RegisterDoctorActivity.class);
                                    startActivity(main);
                                    finish();
                                }else{
                                    Log.i("SPLASH SCREEN INTENT", "Logged in");
                                    Intent main = new Intent(SplashActivity.this, DoctorHomeActivity.class);
                                    startActivity(main);
                                    finish();
                                }

                            }else{
                                if(prefs.getNewUser()){
                                    Log.i("SPLASH SCREEN INTENT", "Logged in");
                                    Intent main = new Intent(SplashActivity.this, RegisterPatientActivity.class);
                                    startActivity(main);
                                    finish();
                                }else{
                                    Log.i("SPLASH SCREEN INTENT", "Logged in");
                                    Intent main = new Intent(SplashActivity.this, HomeActivity.class);
                                    startActivity(main);
                                    finish();
                                }
                            }

                        }
                    }
                },
                3000
        );
    }
}