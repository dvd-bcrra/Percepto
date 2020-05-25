package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Phase1 extends AppCompatActivity {

    private int numPregunta;
    Cursor cursor;
    DBHelper helper;
    SQLiteDatabase db;

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

        //Database
        helper = new DBHelper(this);
        db = helper.getReadableDatabase();
    }

    public void btnScoreNext(View view) {

    }

    public void CargarPregunta(){
        txtNumPregunta.setText(String.format("Pregunta %d",numPregunta));

    }

    public ArrayList<String> Palabras(){
        ArrayList<String> temp = new ArrayList<>();






        return temp;
    }
}
