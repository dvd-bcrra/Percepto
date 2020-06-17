package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Path;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.percepto.Words.JsonWords;
import com.example.percepto.Words.SeparadorSilabas;
import com.example.percepto.model.*;
import com.example.percepto.session.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Phase2 extends AppCompatActivity {

    int numPalabra = 0;
    int cantidadReactivos = 20;

    DBHelper db;
    Session session;
    Evaluation2 eval;
    ArrayList<String> Palabras;
    SeparadorSilabas separador;

    //Views
    LinearLayout llOpciones;
    TextView txtNumPregunta;
    TextView txtContador;
    TextView txtPalabra;
    TextView txtCronometro;
    Button btnOpcion1;
    Button btnOpcion2;
    Button btnOpcion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase2);

        //Algunos atributos
        db = new DBHelper(this);
        session = new Session(this);
        eval = new Evaluation2();
        Palabras = Palabras();
        separador = new SeparadorSilabas();

        //Views
        llOpciones = findViewById(R.id.llopciones);
        txtNumPregunta = findViewById(R.id.txtNumPregunta);
        txtContador = findViewById(R.id.txtContador);
        txtPalabra = findViewById(R.id.txtWord);
        txtCronometro = findViewById(R.id.txtCronometro);
        btnOpcion1 = findViewById(R.id.btnPhase2Opcion1);
        btnOpcion2 = findViewById(R.id.btnPhase2Opcion2);
        btnOpcion3 = findViewById(R.id.btnPhase2Opcion3);

        //Algunas inicializaciones
        txtPalabra.setText("");
        btnOpcion1.setText("");
        btnOpcion2.setText("");
        btnOpcion3.setText("");
        txtCronometro.setText("");

        //llOpciones.setVisibility(View.INVISIBLE);
        CargarEvaluacion();

        nextPriming();
    }

    public void btnPhase2Select(View view) {
        txtPalabra.setVisibility(View.VISIBLE);
        numPalabra++;
        nextPriming();
    }

    private void nextPriming(){

        String Palabra = Palabras.get(numPalabra);
        String OpcionIncorrecta1 = PalabraIncorrecta(Palabra,true);
        String OpcionIncorrecta2 = PalabraIncorrecta(Palabra,false);

        ArrayList<String> Opciones = new ArrayList<>();
        Opciones.add(Palabra);
        Opciones.add(OpcionIncorrecta1);
        Opciones.add(OpcionIncorrecta2);
        Shuffle(Opciones);

        btnOpcion1.setTag(Opciones.get(0));
        btnOpcion2.setTag(Opciones.get(1));
        btnOpcion3.setTag(Opciones.get(2));

        btnOpcion1.setText(Opciones.get(0).toUpperCase());
        btnOpcion2.setText(Opciones.get(1).toUpperCase());
        btnOpcion3.setText(Opciones.get(2).toUpperCase());

        txtPalabra.setText(getRareWord(Palabra));

        CargarContador();


    }

    private String getRareWord(String Word){
        String rareword = Word;
        int lenght = rareword.length();

        if(lenght >= 3 && lenght <=4){
            rareword = changeCharInPosition(2,'_',rareword);
        }
        else if(lenght >= 5 && lenght <= 6){
            rareword = changeCharInPosition(2,'_',rareword);
            rareword = changeCharInPosition(lenght - 1,'_',rareword);
        }
        else if(lenght >= 7 && lenght <= 8){
            rareword = changeCharInPosition(2,'_',rareword);
            rareword = changeCharInPosition(5,'_',rareword);
            rareword = changeCharInPosition(lenght,'_',rareword);
        }
        else{
            rareword = changeCharInPosition(3,'_',rareword);
            rareword = changeCharInPosition(5,'_',rareword);
            rareword = changeCharInPosition(7,'_',rareword);
            rareword = changeCharInPosition(9,'_',rareword);
        }

        return rareword.toUpperCase();
    }

    private void CargarEvaluacion(){
        eval.setCURP(session.getCurrentParticipantCurp());
        SimpleDateFormat id_formatter = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss", Locale.US);
        SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        Date date = new Date();
        eval.setID(id_formatter.format(date));
        eval.setDATE((date_formatter.format(date)));
    }

    private void CargarContador(){
        new CountDownTimer(3000,1000){
            int cont = 3;

            @Override
            public void onTick(long millisUntilFinished) {
                txtContador.setText("Siguiente palabra en " + cont);
                cont--;
            }

            @Override
            public void onFinish() {
                txtContador.setText("");
                //txtPalabra.setVisibility(View.INVISIBLE);
                //llOpciones.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public ArrayList<String> Palabras(){
        JsonWords words = JsonWords.getInstance();
        ArrayList<String> temp = new ArrayList<>(words.TodasLasPalabras);
        Shuffle(temp);
        return temp;
    }

    private String PalabraIncorrecta(String PalabraCorrecta, boolean inicial){
        String temp = "error 404";

        for (String item : Palabras) {
            if(!item.equals(PalabraCorrecta)){
                if(ContarSilabas(item) == ContarSilabas(PalabraCorrecta)){
                    if(inicial){
                        if(item.charAt(0) == PalabraCorrecta.charAt(0)){
                            temp = item;
                            break;
                        }
                    }else{
                        temp = item;
                        break;
                    }
                }
            }
        }

        return temp;
    }

    private int ContarSilabas(String Palabra){
        separador.setString(Palabra);
        return separador.ContarSilabas();
    }

    public String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position - 1] = ch;
        return new String(charArray);
    }

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
