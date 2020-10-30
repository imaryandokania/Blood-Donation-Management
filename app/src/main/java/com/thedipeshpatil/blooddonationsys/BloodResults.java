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

public class BloodResults extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText del_id_text;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_results);

        ListView listView = findViewById(R.id.listview);
        databaseHelper = new DatabaseHelper(this);

        del_id_text = findViewById(R.id.del_id);
        delete = findViewById(R.id.delete);

        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = databaseHelper.getAllData();

        if (data.getCount() == 0){
            Toast.makeText(BloodResults.this, "Database Empty", Toast.LENGTH_LONG).show();
        } else{
            StringBuffer stringBuffer = new StringBuffer();
            while(data.moveToNext()){
                arrayList.add("\n" + "ID: " + data.getString(0) + "\n" + "Name: " + data.getString(1) + "\n" + "Email: " + data.getString(2) + "\n" + "Phone: " + data.getString(3) + "\n" + "Blood Group: " + data.getString(4) + "\n");
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter);
        }

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dID = del_id_text.getText().toString();
                if (!TextUtils.isEmpty(dID)){
                    boolean isDeleted = databaseHelper.deleteData(dID);
                    if(isDeleted){
                        Toast.makeText(BloodResults.this, "Data Deleted Successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(BloodResults.this, "Delete Error!", Toast.LENGTH_LONG).show();
                    }
                    del_id_text.setText("");
                }else{
                    Toast.makeText(BloodResults.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(BloodResults.this, "left-to-right");
    }
}
