package com.example.percepto.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private static SharedPreferences prefs;
//
    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public static void setUser(String usename) {
        prefs.edit().putString("user", usename).apply();
    }

    public static String getUser() {
        return prefs.getString("user","");
    }

    public static void setCurrentParticipant(String curp){
        prefs.edit().putString("currentParticipant",curp).apply();
    }

    public static String getCurrentParticipant(){
        return prefs.getString("currentParticipant","");
    }
}