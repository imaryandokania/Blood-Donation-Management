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

public class InventoryResults extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText del_id_text;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_results);

        ListView listView = findViewById(R.id.listview3);
        databaseHelper = new DatabaseHelper(this);

        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = databaseHelper.getAllData2();

        if (data.getCount() == 0){
            Toast.makeText(InventoryResults.this, "Database Empty", Toast.LENGTH_LONG).show();
        } else{
            StringBuffer stringBuffer = new StringBuffer();
            while(data.moveToNext()){
                arrayList.add("\n" + "BloodBag Number: " + data.getString(0) + "\n" + "Blood Type: " + data.getString(1) + "\n" + "Description: " + data.getString(2) + "\n" + "Quantity: " + data.getString(3) + "\n");
            }
            ListAdapter listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter2);
        }

    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(InventoryResults.this, "left-to-right");
    }
}
