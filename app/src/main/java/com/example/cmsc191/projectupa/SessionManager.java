package com.example.cmsc191.projectupa;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Kean on 5/19/2016.
 */
public class SessionManager {

    public void setPreferences(Context context, String key, String value){
        SharedPreferences.Editor editor = context
                .getSharedPreferences("LoginDeetz", Context.MODE_PRIVATE)
                .edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getPreferences(Context context, String key){
        SharedPreferences prefs = context
                .getSharedPreferences("LoginDeetz", Context.MODE_PRIVATE);
        String position = prefs.getString(key, "");
        return position;
    }
}
