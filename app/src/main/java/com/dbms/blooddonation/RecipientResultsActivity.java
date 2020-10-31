package com.dbms.blooddonation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import maes.tech.intentanim.CustomIntent;

public class RecipientResultsActivity extends AppCompatActivity  {

    DatabaseHelper databaseHelper;
    EditText del_id_text;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_results);

        final ListView listView = findViewById(R.id.listviewneed);
        databaseHelper = new DatabaseHelper(this);

        final ArrayList<Spanned> arrayList = new ArrayList<>();
        final Cursor dataOuter = databaseHelper.getAllData3();

        if (dataOuter.getCount() == 0){
            Toast.makeText(RecipientResultsActivity.this, "Database Empty", Toast.LENGTH_LONG).show();
        } else{
            StringBuffer stringBuffer = new StringBuffer();
            while(dataOuter.moveToNext()){
                arrayList.add(parse(dataOuter));
            }
            final ArrayAdapter listAdapter2 = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(listAdapter2);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    dataOuter.moveToPosition(position);
                    final Cursor data = databaseHelper.getPossibleDonors(dataOuter.getString(1));
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipientResultsActivity.this);
                    builder.setTitle("Choose a donor");
                    ArrayList<String> donors = new ArrayList<>();

                    while(data.moveToNext()){
                        donors.add("\n" + "ID: " + data.getString(0) + "\n" + "Name: " + data.getString(1) + "\n" + "Email: " + data.getString(2) + "\n" + "Phone: " + data.getString(3) + "\n" + "Blood Group: " + data.getString(4) + "\n");
                    }
                    String[] donorsArray = donors.toArray(new String[0]);
                    Log.d("donors", "onItemClick: " + Arrays.toString(donorsArray));
                    builder.setItems(donorsArray, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            data.moveToPosition(which);
                            Log.d("gg", "onClick: " + dataOuter.moveToPosition(position));
                            Log.d("gg", "onClick: " + position);
                            databaseHelper.addDonorToRec(data.getInt(0), dataOuter.getInt(3));
                            arrayList.set(position, parse(dataOuter));
                            listAdapter2.notifyDataSetChanged();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }

    }
    private Spanned parse(Cursor dataOuter){
        String donorId = String.valueOf(dataOuter.getInt(4));
        if(dataOuter.getInt(4) == 0){
            donorId = "Not Assigned";
        }
        String str = "<br>" + "ID:" + dataOuter.getInt(3) + "<br>" + "Name: " + dataOuter.getString(0) + "<br>" + "Blood Type: " + dataOuter.getString(1) + "<br>" + "PhoneNo: " + dataOuter.getString(2) + "<br>" + "DonorID: " + donorId + "<br>";
        if(dataOuter.getInt(4) != 0){
            str = "<b>" + str + "</b>";
        }
        return Html.fromHtml(str);
    }

    @Override
    public void finish() {
        super.finish();
        CustomIntent.customType(RecipientResultsActivity.this, "left-to-right");
    }
}
