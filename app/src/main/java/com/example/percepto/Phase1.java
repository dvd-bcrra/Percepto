package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.percepto.Words.JsonWords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Phase1 extends AppCompatActivity {

    private int numPregunta;
    ArrayList<String> palabras = Palabras();

    //Views
    TextView txtNumPregunta;
    TextView txtPalabra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase1);

        //Views
        txtNumPregunta = findViewById(R.id.txtNumPregunta);
        txtPalabra = findViewById(R.id.txtWord);

        numPregunta = 0;
        CargarPregunta();
    }

    public void btnScoreNext(View view) {
        numPregunta++;
        CargarPregunta();
    }

    public void CargarPregunta(){
        txtNumPregunta.setText(String.format("Pregunta %d",numPregunta));
        txtPalabra.setText(palabras.get(numPregunta));
    }

    public ArrayList<String> Palabras(){
        ArrayList<String> temp = new ArrayList<>();
        JsonWords words = new JsonWords(getAssets());

        for(int i = 0; i < 20; i++){
            String palabraAlegre = words.PalabrasAlegres.get(i);
            temp.add(palabraAlegre);
        }

        for(int i = 0; i < 20; i++){
            String palabraMiedosa = words.PalabrasMiedosas.get(i);
            temp.add(palabraMiedosa);
        }

        for(int i = 0; i < 20; i++){
            String palabraNeutra = words.PalabrasNeutras.get(i);
            temp.add(palabraNeutra);
        }

        Shuffle(temp);
        return temp;
    }

    /*
        Funcion para desordenar elementos en una lista,
        basado en el algoritmo Fisher-Yater
     */
    public static<T> void Shuffle(List<T> list){
        Random rnd = new Random();
        for(int i = list.size() - 1; i >= 1; i--){
            int j = rnd.nextInt(i + 1);
            T obj = list.get(i);
            list.set(i,list.get(j));
            list.set(j,obj);
        }
    }
}
