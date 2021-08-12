package com.example.charitydonation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.charitydonation.Utility.SessionManager;

public class SplashActivity extends AppCompatActivity {
    String pkid = "", role = "", name = "";
    private SessionManager mSessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSessionManager = new SessionManager(this);
        pkid = mSessionManager.getStringData("pk_id");
        role = mSessionManager.getStringData("role");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (pkid.equals("")) {
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    finish();

                } else {

                    if (role.equalsIgnoreCase("Admin")){
                        Intent intent = new Intent(SplashActivity.this, AdminHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }else if (role.equalsIgnoreCase("User")){
                        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }


            }
        }, 4000);
    }
}