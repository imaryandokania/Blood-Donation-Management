package com.thedipeshpatil.blooddonationsys;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class BloodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper databaseHelper;
    EditText name, email, phone, id;
    Button insert, update, delete, view,er,dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood);

        databaseHelper = new DatabaseHelper(this);

        id = findViewById(R.id.donor_id);
        name = findViewById(R.id.donor_name);
        email = findViewById(R.id.donor_email);
        phone = findViewById(R.id.donor_phone);
        er=findViewById(R.id.ins);
       dr=findViewById(R.id.ins1);
        insert = findViewById(R.id.insert);
        view = findViewById(R.id.view);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        final Spinner blood_group_spinner = findViewById(R.id.spinner_bg);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_group_spinner.setAdapter(adapter);
        blood_group_spinner.setOnItemSelectedListener(this);
         er.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v)
             {
                 Intent bIntent = new Intent(BloodActivity.this, StaffActivity.class);
                 startActivity(bIntent);
             }
         });
         dr.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent dIntent = new Intent(BloodActivity.this, BloodInventory.class);
                 startActivity(dIntent);
             }
         });
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d_name = name.getText().toString();
                String d_email = email.getText().toString();
                String d_phone = phone.getText().toString();
                String d_blood = blood_group_spinner.getSelectedItem().toString();

                if( !TextUtils.isEmpty(d_blood) && !TextUtils.isEmpty(d_email) && !TextUtils.isEmpty(d_name) && !TextUtils.isEmpty(d_phone)){
                    boolean isInserted = databaseHelper.insertData(d_name,d_email,d_phone,d_blood);
                    if (isInserted)
                        Toast.makeText(BloodActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(BloodActivity.this, "Insertion Error", Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    Intent bloodIntent = new Intent(BloodActivity.this, BloodResults.class);
                    startActivity(bloodIntent);
                    CustomIntent.customType(BloodActivity.this, "right-to-left");
                }else{
                    Toast.makeText(BloodActivity.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData();
                if(result.getCount() == 0){
                    showMessage("Error", "No Data");
                    return;
                }else{
                    Intent bloodIntent = new Intent(BloodActivity.this, BloodResults.class);
                    startActivity(bloodIntent);
                    CustomIntent.customType(BloodActivity.this, "right-to-left");
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uID = id.getText().toString();
                if (!TextUtils.isEmpty(uID)){
                    String u_name = name.getText().toString();
                    String u_email = email.getText().toString();
                    String u_phone = phone.getText().toString();
                    String u_blood = blood_group_spinner.getSelectedItem().toString();

                    boolean isUpdate = databaseHelper.updateData(uID, u_name, u_email, u_phone, u_blood);

                    if(isUpdate){
                        Toast.makeText(BloodActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(BloodActivity.this, "Update Error!", Toast.LENGTH_LONG).show();
                    }
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    id.setText("");
                }else{
                    Toast.makeText(BloodActivity.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
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
