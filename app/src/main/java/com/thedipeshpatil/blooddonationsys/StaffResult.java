package com.thedipeshpatil.blooddonationsys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class StaffResult extends AppCompatActivity {

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_result);

        ListView listView = findViewById(R.id.listview1);
        databaseHelper = new DatabaseHelper(this);

        ArrayList<String> arrayList1 = new ArrayList<>();
        Cursor data = databaseHelper.getAllData1();

        if (data.getCount() == 0){
            Toast.makeText(StaffResult.this, "Database Empty", Toast.LENGTH_LONG).show();
        } else{
            StringBuffer stringBuffer = new StringBuffer();
            while(data.moveToNext()){
                arrayList1.add("\n" + "ID: " + data.getString(0) + "\n" + "Name: " + data.getString(1) + "\n" + "Email: " + data.getString(2) + "\n" + "Phone: " + data.getString(3) + "\n" + "Salary: " + data.getString(4) + "\n");
            }
            ListAdapter listAdapter1 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList1);
            listView.setAdapter(listAdapter1);
        }


    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(StaffResult.this, "left-to-right");
    }
}
