package com.example.myapp1.syn;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    private SharedPreferences prefs;
    public Session(Context ctx){
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public void setJwt(String jwt){
        prefs.edit().putString("jwt", jwt).commit();
    }
    public String getjwt(){
        return prefs.getString("jwt","");
    }
    public void removejwt(){
        prefs.edit().remove("jwt");

    }
}

