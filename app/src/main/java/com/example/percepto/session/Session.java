package com.example.percepto.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.percepto.model.User;

public class Session {

    private static SharedPreferences prefs;

    public Session(Context cntx) {
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setUser(User user) {
        prefs.edit().putString("name", user.getNAME()).apply();
        prefs.edit().putString("username", user.getUSERNAME()).apply();
        prefs.edit().putString("password", user.getPASSWORD()).apply();
        prefs.edit().putBoolean("admin", user.isADMIN()).apply();
    }

    public User getUser() {
        User user = new User();
        user.setNAME(prefs.getString("name",""));
        user.setUSERNAME(prefs.getString("username",""));
        user.setPASSWORD(prefs.getString("password",""));
        user.setADMIN(prefs.getBoolean("admin",false));

        if(user.getNAME().equals("")){
            return null;
        }
        else {
            return user;
        }
    }

    public void setCurrentParticipant(String curp){
        prefs.edit().putString("currentParticipant",curp).apply();
    }

    public String getCurrentParticipant(){
        return prefs.getString("currentParticipant","");
    }
}