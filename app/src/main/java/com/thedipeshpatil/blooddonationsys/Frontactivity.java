package com.thedipeshpatil.blooddonationsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

public class Frontactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frontactivity);
        Button button=findViewById(R.id.admin);
        Button b1=findViewById(R.id.enroll);
        Button b2=findViewById(R.id.need);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adIntent = new Intent(Frontactivity.this, BloodActivity.class);
                startActivity(adIntent);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent enIntent = new Intent(Frontactivity.this, EnrollActivity.class);
                startActivity(enIntent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent needIntent = new Intent(Frontactivity.this, NeedResults.class);
                startActivity(needIntent);
            }
        });

    }

}