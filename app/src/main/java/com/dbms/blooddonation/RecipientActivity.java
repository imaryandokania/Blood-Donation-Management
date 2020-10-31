package com.dbms.blooddonation;

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
import android.widget.TextView;
import android.widget.Toast;

import maes.tech.intentanim.CustomIntent;

public class RecipientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper databaseHelper;
    EditText name, phone, id;
    TextView group;
    Button insert, update, delete, view,er;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient);

        databaseHelper = new DatabaseHelper(this);

        name = findViewById(R.id.rec_name);
        group = findViewById(R.id.group);
        phone = findViewById(R.id.rec_phno);
        insert = findViewById(R.id.blood_insert);
        view = findViewById(R.id.blood_view);

        final Spinner blood_group_spinner = findViewById(R.id.spinner_bg);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_groups,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_group_spinner.setAdapter(adapter);
        blood_group_spinner.setOnItemSelectedListener(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recName = name.getText().toString();
                String recGroup = group.getText().toString();
                String recPhone = phone.getText().toString();


                if(!TextUtils.isEmpty(recGroup) && !TextUtils.isEmpty(recName) && !TextUtils.isEmpty(recPhone)){
                    boolean isInserted = databaseHelper.insertData3(recName, recGroup, recPhone);
                    if (isInserted)
                        Toast.makeText(RecipientActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(RecipientActivity.this, "Insertion Error", Toast.LENGTH_LONG).show();
                    name.setText("");
                    group.setText("");
                    phone.setText("");
                }else{
                    Toast.makeText(RecipientActivity.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData3();
                if(result.getCount() == 0){
                    showMessage("Error", "No Data");
                    return;
                }else{
                    Intent bloodIntent = new Intent(RecipientActivity.this, RecipientResultsActivity.class);
                    startActivity(bloodIntent);
                    CustomIntent.customType(RecipientActivity.this, "right-to-left");
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
