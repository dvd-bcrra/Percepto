package com.example.percepto.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUser(String usename) {
        prefs.edit().putString("user", usename).apply();
    }

    public String getUser() {
        return prefs.getString("user","");
    }

    public void setCurrentParticipant(String curp){
        prefs.edit().putString("currentParticipant",curp).apply();
    }

    public String getCurrentParticipant(){
        return prefs.getString("currentParticipant","");
    }
}