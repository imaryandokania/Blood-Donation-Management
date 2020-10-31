package com.dbms.blooddonation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "BloodBank.db";
    public static final String TABLE = "blood_bank";
    public static final String TABLE1 = "staff";
    public static final String TABLE2 = "Blood_inventory";
    public static final String TABLE3 = "Recipient";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Email";
    public static final String COL_4 = "Phone";
    public static final String COL_5 = "Blood";
    public static final String COL_6 = "EID";
    public static final String COL_7 = "Name";
    public static final String COL_8 = "Email";
    public static final String COL_9 = "Phone";
    public static final String COL_10 = "Salary";
    public static final String COL_11 = "Bloodbag_Number";
    public static final String COL_12 = "BloodType";
    public static final String COL_13 = "Description";
    public static final String COL_14 = "Quantity";
    public static final String COL_15 = "Name";
    public static final String COL_16 = "PhoneNo";
    public static final String COL_17 = "DonorID";
    public static final String COL_18 = "BloodType";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PHONE TEXT, BLOOD TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE1 + "(EID TEXT , NAME TEXT, EMAIL TEXT, PHONE TEXT, SALARY TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE2 + "(BloodBag_Number TEXT , BloodType TEXT, Description TEXT, Quantity TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE3 + "(Name TEXT , BloodType TEXT, PhoneNo TEXT, ID INTEGER PRIMARY KEY AUTOINCREMENT, DonorID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1 );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE2 );
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData3(String name, String bloodGroup, String phno){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_15, name);
        contentValues.put(COL_18, bloodGroup);
        contentValues.put(COL_16, phno);
        long result = sqLiteDatabase.insert(TABLE3, COL_17, contentValues);
        if (result == -1){
            return false;
        }
        return true;
    }

    public boolean addDonorToRec(int donorID, int recID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_17, donorID);
        long result = sqLiteDatabase.update(TABLE3, contentValues,  "ID = ?", new String[]{String.valueOf(recID)});
        if (result == -1){
            Log.d("add", "addDonorToRec: ");
            return false;
        }
        Log.d("add", "addDonorToRec: " + donorID + "  " + recID);
        return true;
    }

    public boolean insertData2(String bloodid,String bloodname,String blooddesc, String quantity)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_11, bloodid);
        contentValues.put(COL_12, bloodname);
        contentValues.put(COL_13, blooddesc);
        contentValues.put(COL_14, quantity);
        long result = sqLiteDatabase.insert(TABLE2, null, contentValues);
        if (result == -1){
            return false;
        }
        return true;
    }
   public boolean insertData1(String eid,String name,String email, String phone, String salary )
   {
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(COL_6, eid);
       contentValues.put(COL_7, name);
       contentValues.put(COL_8, email);
       contentValues.put(COL_9, phone);
       contentValues.put(COL_10, salary);
       long result = sqLiteDatabase.insert(TABLE1, null, contentValues);
       if (result == -1){
           return false;
       }
       return true;
   }
    public boolean insertData(String name, String email, String phone, String blood){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, blood);
        long result = sqLiteDatabase.insert(TABLE, null, contentValues);

        if (result == -1){
            return false;
        }
        return true;
    }
    public Cursor getAllData1(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res1 = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE1, null);
        return res1;
    }
    public Cursor getAllData2(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res2 = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE2, null);
        return res2;
    }

    public Cursor getAllData3(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res3 = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE3 + " ORDER BY " + COL_17 + " ASC   ", null);
        return res3;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE, null);
        return res;
    }

    public Cursor getPossibleDonors(String blood){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE + " WHERE blood = ?", new String[]{blood});
        return res;
    }

    public boolean updateData(String id, String name, String email, String phone, String blood){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, blood);
        sqLiteDatabase.update(TABLE, contentValues, "ID = ?", new String[] {id} );
        return true;
    }

    public boolean deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE, "ID = ?", new String[] {id});
        return true;
    }
}