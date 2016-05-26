package com.example.cmsc191.projectupa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

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
        Toast.makeText(context, "Unit " + codename + " successfully added!", Toast.LENGTH_LONG).show();
    }

    public int deleteEntry(String username) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{username}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
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
    public void  updateEntry(String username,String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", username);
        updatedValues.put("PASSWORD",password);

        String where = "USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{username});
    }
}
