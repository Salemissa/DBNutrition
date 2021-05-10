package com.example.myapp1.syn;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

public class Session {
    public static final String MODEL=Build.MODEL;

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
        removeCode();
        prefs.edit().remove("jwt");
    }


    public void setCodeSup(String codeSup){
        prefs.edit().putString("codeSup", codeSup).commit();
    }
    public String getCodeSup(){
        return prefs.getString("codeSup","");
    }
    public void removeCode(){
        prefs.edit().remove("codeSup");
    }
}

