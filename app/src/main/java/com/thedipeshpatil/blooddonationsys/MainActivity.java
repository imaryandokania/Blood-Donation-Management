package com.thedipeshpatil.blooddonationsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import maes.tech.intentanim.CustomIntent;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent bloodIntent = new Intent(MainActivity.this, BloodActivity.class);
                startActivity(bloodIntent);
                CustomIntent.customType(MainActivity.this, "right-to-left");
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
