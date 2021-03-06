package com.example.cmsc191.projectupa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kean on 5/25/2016.
 */
public class UserDatabaseAdapter {
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table "+"LOGIN( "
            + "ID"+" integer primary key autoincrement,"
            + "FULLNAME text,"
            + "USERNAME text,"
            + "PASSWORD text,"
            + "ADDRESS text); ";

    static final String DATABASE2_CREATE = "create table " + "UNIT( "
            + "ID" + " integer primary key autoincrement,"
            + "USERNAME text,"
            + "CODENAME text,"
            + "WATER_BILL real,"
            + "ELECTRIC_BILL real,"
            + "RENT real,"
            + "TENANT text,"
            + " FOREIGN KEY(USERNAME) REFERENCES LOGIN(USERNAME));";

    public SQLiteDatabase db;
    private final Context context;
    private DatabaseHelper dbHelper;

    public UserDatabaseAdapter (Context _context) {
        context = _context;
        dbHelper = new DatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  UserDatabaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertEntry(String fullname, String username, String password, String address) {
        //USER (FULLNAME, USERNAME, PASSWORD, ADDRESS)
        ContentValues newValues = new ContentValues();
        newValues.put("FULLNAME", fullname);
        newValues.put("USERNAME", username);
        newValues.put("PASSWORD",password);
        newValues.put("ADDRESS", address);
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show()
    }

    public void insertUnitEntry(String username, String codename, Float waterBill, Float electricBill, Float rent, String tenant){
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", username);
        newValues.put("CODENAME", codename);
        newValues.put("WATER_BILL", waterBill);
        newValues.put("ELECTRIC_BILL", electricBill);
        newValues.put("RENT", rent);
        newValues.put("TENANT", tenant);
        db.insert("UNIT", null, newValues);
        Toast.makeText(context, codename + " successfully added!", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String username) {
        String where = "USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{username}) ;
        return numberOFEntriesDeleted;
    }
    public String getSingleEntry(String username) {
        Cursor cursor = db.query(
                "LOGIN",
                null,
                " USERNAME=?",
                new String[]{username}, null, null, null);
        if(cursor.getCount()<1) {
            //If not exist
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public ArrayList<String> getUnitDetails(int position){
        ArrayList<String> unitDetails = new ArrayList<String>();
        String codename, water_bill, electric_bill, rent, tenant;
        Cursor cursor = db.query(
                "UNIT",
                new String[]{"CODENAME", "WATER_BILL", "ELECTRIC_BILL", "RENT", "TENANT"},
                "ID = " + position,
                null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            codename = cursor.getString(cursor.getColumnIndex("CODENAME"));
            water_bill = cursor.getString(cursor.getColumnIndex("WATER_BILL"));
            electric_bill = cursor.getString(cursor.getColumnIndex("ELECTRIC_BILL"));
            rent = cursor.getString(cursor.getColumnIndex("RENT"));
            tenant = cursor.getString(cursor.getColumnIndex("TENANT"));
            unitDetails.add(codename);
            unitDetails.add(water_bill);
            unitDetails.add(electric_bill);
            unitDetails.add(rent);
            unitDetails.add(tenant);
        }
        cursor.close();
        return unitDetails;
    }

    public ArrayList<String> selectAll(){
        ArrayList<String> unitNames = new ArrayList<String>();
        String unit;
        Cursor cursor = db.query("UNIT",
                new String[] {"CODENAME"},
                //"USERNAME = " + username,
                null, null, null, null, null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            for(int i=0; i<cursor.getCount(); i++) {
                unit = cursor.getString(cursor.getColumnIndex("CODENAME"));
                unitNames.add(unit);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return unitNames;
    }
    
    public String[] getUserEntries(String username){
        String entries[] = new String[4];
        Cursor cursor = db.query(
                "LOGIN",
                new String[] {"FULLNAME","USERNAME","PASSWORD","ADDRESS"},
                "USERNAME=?",
                new String[]{username}, null, null, null);
        cursor.moveToFirst();
        entries[0] = cursor.getString(cursor.getColumnIndex("FULLNAME"));
        entries[1] = cursor.getString(cursor.getColumnIndex("ADDRESS"));
        entries[2] = cursor.getString(cursor.getColumnIndex("USERNAME"));
        entries[3] = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return entries;
    }

    public void  updateEntry(String user, String username,String password, String addr, String fullname) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", username);
        updatedValues.put("PASSWORD",password);
        updatedValues.put("ADDRESS",addr);
        updatedValues.put("FULLNAME",fullname);

        String where = "USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{user});
    }
}
