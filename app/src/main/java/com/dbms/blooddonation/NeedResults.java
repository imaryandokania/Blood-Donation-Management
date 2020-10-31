package com.dbms.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import maes.tech.intentanim.CustomIntent;

public class NeedResults extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText del_id_text;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_results);

        ListView listView = findViewById(R.id.listviewneed);
        databaseHelper = new DatabaseHelper(this);

        del_id_text = findViewById(R.id.need_id);
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = databaseHelper.getAllData();

        if (data.getCount() == 0){
            Toast.makeText(NeedResults.this, "Database Empty", Toast.LENGTH_LONG).show();
        } else{
            StringBuffer stringBuffer = new StringBuffer();
            while(data.moveToNext()){
                arrayList.add("\n" + "ID: " + data.getString(0) + "\n" + "Name: " + data.getString(1) + "\n" + "Email: " + data.getString(2) + "\n" + "Phone: " + data.getString(3) + "\n" + "Blood Group: " + data.getString(4) + "\n");
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter);
        }

    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(NeedResults.this, "left-to-right");
    }
}
