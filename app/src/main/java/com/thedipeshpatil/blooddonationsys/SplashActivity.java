package com.thedipeshpatil.blooddonationsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import maes.tech.intentanim.CustomIntent;

public class SplashActivity extends AppCompatActivity {
    private static final long DELAY = 2000;
    private boolean scheduled = false;
    private Timer splashTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashTimer = new Timer();
        splashTimer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                SplashActivity.this.finish();
                startActivity(new Intent(SplashActivity.this, Frontactivity.class));

            }
        }, DELAY);
        scheduled = true;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (scheduled)
            splashTimer.cancel();
        splashTimer.purge();
    }
}