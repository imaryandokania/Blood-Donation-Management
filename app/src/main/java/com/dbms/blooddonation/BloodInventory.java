package com.dbms.blooddonation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class BloodInventory extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper databaseHelper;
    EditText name, email, phone, id;
    Button insert, update, delete, view,er;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_inventory);

        databaseHelper = new DatabaseHelper(this);

        id = findViewById(R.id.bag_number);
        name = findViewById(R.id.blood_name);
        email = findViewById(R.id.blood_desc);
        phone = findViewById(R.id.blood_quantity);
        insert = findViewById(R.id.blood_insert);
        view = findViewById(R.id.blood_view);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent bIntent = new Intent(BloodInventory.this, StaffActivity.class);
                startActivity(bIntent);
            }
        });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b_id = id.getText().toString();
                String b_name = name.getText().toString();
                String b_desc = email.getText().toString();
                String b_quantity = phone.getText().toString();


                if(!TextUtils.isEmpty(b_desc) && !TextUtils.isEmpty(b_name) && !TextUtils.isEmpty(b_quantity)){
                    boolean isInserted = databaseHelper.insertData2(b_id,b_name,b_desc,b_quantity);
                    if (isInserted)
                        Toast.makeText(BloodInventory.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(BloodInventory.this, "Insertion Error", Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }else{
                    Toast.makeText(BloodInventory.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData2();
                if(result.getCount() == 0){
                    showMessage("Error", "No Data");
                    return;
                }else{
                    Intent bloodIntent = new Intent(BloodInventory.this, InventoryResults.class);
                    startActivity(bloodIntent);
                    CustomIntent.customType(BloodInventory.this, "right-to-left");
                }

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
