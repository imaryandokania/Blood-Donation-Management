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

import java.util.Random;

import maes.tech.intentanim.CustomIntent;

public class EnrollActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper databaseHelper;
    EditText name, email, phone, id;
    Button insert, update, delete, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        databaseHelper = new DatabaseHelper(this);

        id = findViewById(R.id.donor_id);
        name = findViewById(R.id.donor_name);
        email = findViewById(R.id.donor_email);
        phone = findViewById(R.id.donor_phone);

        insert = findViewById(R.id.insert);

        final Spinner blood_group_spinner = findViewById(R.id.spinner_bg);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_group_spinner.setAdapter(adapter);
        blood_group_spinner.setOnItemSelectedListener(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i1 = r.nextInt(45 - 28) + 28;
                String d_name = name.getText().toString();
                String d_email = email.getText().toString();
                String d_phone = phone.getText().toString();
                String d_blood = blood_group_spinner.getSelectedItem().toString();

                if( !TextUtils.isEmpty(d_blood) && !TextUtils.isEmpty(d_email) && !TextUtils.isEmpty(d_name) && !TextUtils.isEmpty(d_phone)){
                    boolean isInserted = databaseHelper.insertData(d_name,d_email,d_phone,d_blood);
                    if (isInserted)
                        Toast.makeText(EnrollActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(EnrollActivity.this, "Insertion Error", Toast.LENGTH_LONG).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                }else{
                    Toast.makeText(EnrollActivity.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
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
