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

public class StaffActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper databaseHelper;
    EditText empname, empemail, empphone, empid,empsalary;
    Button empinsert, update, delete, empview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        databaseHelper = new DatabaseHelper(this);

        empid = findViewById(R.id.emp_id);
        empname = findViewById(R.id.emp_name);
        empemail = findViewById(R.id.emp_email);
        empphone = findViewById(R.id.emp_phone);
        empsalary = findViewById(R.id.emp_salary);
        empinsert = findViewById(R.id.emp_insert);
        empview = findViewById(R.id.emp_view);

        empinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e_id=empid.getText().toString();
                String e_name = empname.getText().toString();
                String e_email = empemail.getText().toString();
                String e_phone = empphone.getText().toString();
                String e_salary=empsalary.getText().toString();

                if( !TextUtils.isEmpty(e_salary) && !TextUtils.isEmpty(e_email) && !TextUtils.isEmpty(e_name) && !TextUtils.isEmpty(e_phone)){
                    boolean isInserted = databaseHelper.insertData1(e_id,e_name,e_email,e_phone,e_salary);
                    if (isInserted)
                        Toast.makeText(StaffActivity.this, "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(StaffActivity.this, "Insertion Error", Toast.LENGTH_LONG).show();
                    empid.setText("");
                    empname.setText("");
                    empemail.setText("");
                    empphone.setText("");
                    empsalary.setText("");

                }else{
                    Toast.makeText(StaffActivity.this, "Required Fields Cannot Be Blank", Toast.LENGTH_LONG).show();
                }
            }
        });

        empview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = databaseHelper.getAllData1();
                if(result.getCount() == 0){
                    showMessage("Error", "No Data");
                    return;
                }else{
                    Intent bloodIntent = new Intent(StaffActivity.this, StaffResult.class);
                    startActivity(bloodIntent);
                    CustomIntent.customType(StaffActivity.this, "right-to-left");
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
