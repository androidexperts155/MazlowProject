package com.mazlow.customclasses;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    Context context;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    public Prefs(Context con) {
        this.context = con;
        prefs = con.getSharedPreferences("Prefs", Context.MODE_PRIVATE);
    }

    public void setString(String key, String value) {
        editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void setInt(String key1, int value) {
        editor = prefs.edit();
        editor.putInt(key1, value);
        editor.commit();
    }

    public Integer getInt(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }

    public String getString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public void clearPref(){
        editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
