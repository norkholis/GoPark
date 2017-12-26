package com.example.kholis.smartparking;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent HomeIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(HomeIntent);
                finish();
            }
        }, 4000);

    }
}
