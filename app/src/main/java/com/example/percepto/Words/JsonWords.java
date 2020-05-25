package com.example.percepto.Words;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonWords{
    static Context context;

    private static JsonWords JsonWords_Instance = null;
    public ArrayList<String> TodasLasPalabras = new ArrayList<>();
    public ArrayList<String> PalabrasAlegres = new ArrayList<>();
    public ArrayList<String> PalabrasMiedosas = new ArrayList<>();
    public ArrayList<String> PalabrasNeutras = new ArrayList<>();

    private JsonWords(){
        getTodasLasPalabras();
    }

    public static JsonWords getInstance(Context context){
        if(JsonWords_Instance == null){
            JsonWords.context = context;
            JsonWords_Instance = new JsonWords();
        }

        return  JsonWords_Instance;
    }

    public static JsonWords getInstance(){
        return  JsonWords_Instance;
    }

    private void getTodasLasPalabras(){
        String Json;
        InputStream is;

        try {
            is = context.getAssets().open("words.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            Json = new String(buffer,"UTF-8");
            JSONArray ja = new JSONArray(Json);

            for(int i = 0; i <ja.length();i++){
                JSONObject jo = ja.getJSONObject(i);

                TodasLasPalabras.add(jo.getString("Palabra"));

                if (jo.getString("Estimulo").equals("Alegria")){
                    PalabrasAlegres.add(jo.getString("Palabra"));
                }

                else if (jo.getString("Estimulo").equals("Miedo")){
                    PalabrasMiedosas.add(jo.getString("Palabra"));
                }

                else if (jo.getString("Estimulo").equals("Neutro")){
                    PalabrasNeutras.add(jo.getString("Palabra"));
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
