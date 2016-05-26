package com.example.cmsc191.projectupa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kean on 5/24/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TABLE CREATION
        db.execSQL(UserDatabaseAdapter.DATABASE_CREATE);
        db.execSQL(UserDatabaseAdapter.DATABASE2_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TABLE UPGRADE

        Log.w("TaskDBAdapter",
                "Upgrading from version " + oldVersion + " to " + newVersion
                        + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + "LOGIN");
        onCreate(db);
    }
}
