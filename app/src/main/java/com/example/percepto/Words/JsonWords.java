package com.example.percepto.Words;

import android.content.res.AssetManager;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JsonWords{
    AssetManager as;
    public ArrayList<String> TodasLasPalabras = new ArrayList<>();
    public ArrayList<String> PalabrasAlegres = new ArrayList<>();
    public ArrayList<String> PalabrasMiedosas = new ArrayList<>();
    public ArrayList<String> PalabrasNeutras = new ArrayList<>();

    public JsonWords(AssetManager as){
        this.as = as;
        getTodasLasPalabras();
    }

    private void getTodasLasPalabras(){
        String Json;
        InputStream is;

        try {
            is = as.open("words.json");
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
