package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.percepto.Words.JsonWords;
import com.example.percepto.model.Evaluation1;
import com.example.percepto.session.Session;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.xml.transform.Templates;

public class Phase1 extends AppCompatActivity {

    private int numPregunta;
    Evaluation1 eval;
    Session session;
    ArrayList<String> palabras = Palabras();
    DBHelper db;

    //Views
    TextView txtNumPregunta;
    TextView txtPalabra;
    TextView txtContador;
    LinearLayout llreacciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase1);

        //Views
        txtNumPregunta = findViewById(R.id.txtNumPregunta);
        txtPalabra = findViewById(R.id.txtWord);
        txtContador =  findViewById(R.id.txtContador);
        llreacciones = findViewById(R.id.llreacciones);

        session = new Session(this);

        db = new DBHelper(this);

        numPregunta = 0;
        CargarEvaluacion();
        llreacciones.setVisibility(View.INVISIBLE);

        CargarContador();
        CargarPregunta();
    }


    public void btnScoreNext(View view) {
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.shake);
        view.startAnimation(shake);
        int score = Integer.parseInt(view.getTag().toString());
        eval.AddRecord(palabras.get(numPregunta),score);
        numPregunta++;
        CargarPregunta();
    }

    private void CargarContador(){
        new CountDownTimer(3000,1000){
            int cont = 3;

            @Override
            public void onTick(long millisUntilFinished) {
                txtContador.setText("iniciando en " + cont);
                cont--;
            }

            @Override
            public void onFinish() {
                txtContador.setText("");
                llreacciones.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void CargarPregunta(){
        if (numPregunta == 20){
            db.AddEvaluation1(eval);
            Intent TestCompleted = new Intent(Phase1.this,Test_completed.class);
            TestCompleted.putExtra("ID_EVAL1",eval.getID());
            startActivity(TestCompleted);
        }else{
            txtNumPregunta.setText(String.format("Pregunta %d",numPregunta + 1));
            txtPalabra.setText(palabras.get(numPregunta));
        }
    }

    public ArrayList<String> Palabras(){
        ArrayList<String> temp = new ArrayList<>();
        JsonWords words = JsonWords.getInstance();

        for(int i = 0; i < 7; i++){
            String palabraAlegre = words.PalabrasAlegres.get(i);
            temp.add(palabraAlegre);
        }

        for(int i = 0; i < 7; i++){
            String palabraMiedosa = words.PalabrasMiedosas.get(i);
            temp.add(palabraMiedosa);
        }

        for(int i = 0; i < 6; i++){
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

    private void CargarEvaluacion(){
        eval = new Evaluation1();
        eval.setCURP(session.getCurrentParticipantCurp());
        SimpleDateFormat id_formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        Date date = new Date();
        eval.setID(id_formatter.format(date));
        eval.setDATE((date_formatter.format(date)));
    }
}
