package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.percepto.model.*;
import com.example.percepto.session.Session;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Objects;

public class Test2Completed extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineDataSet;

    DBHelper db;
    Session session;
    private Evaluation2 evaluation2;
    CheckBox generarCSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2_completed);

        db = new DBHelper(this);
        session = new Session(this);
        generarCSV = findViewById(R.id.chkExportar);

        String evalid = Objects.requireNonNull(getIntent().getExtras()).getString("ID_EVAL2");
        evaluation2 = db.getEvaluation2(evalid);
        CargarGrafica(evaluation2);

        if(session.UserIsAdmin()){
            generarCSV.setVisibility(View.VISIBLE);
        }
        else{
            generarCSV.setVisibility(View.INVISIBLE);
        }
    }

    private void CargarGrafica(Evaluation2 eval){
        lineChart = findViewById(R.id.lineChart);
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<eval.getRecords().size(); i++) {
            int y = onezero(eval.getRecords().get(i).getIS_CORRECT());
            lineEntries.add(new Entry(i+1,y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Resultados");
        lineDataSet.setDrawCircles(false);

        //Formatear las etiquetas
        LineData lineData = new LineData();
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setAvoidFirstLastClipping(true);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        YAxis yAxisRight = lineChart.getAxisRight();

        yAxisLeft.setValueFormatter(new YAxisValuesFormatter());
        yAxisRight.setValueFormatter(new YAxisValuesFormatter());

        // Asociamos al gráfico
        lineData.addDataSet(lineDataSet);
        lineData.setValueFormatter(new LineDataFormatter());
        lineChart.setData(lineData);
    }

    public void btnCompletedNext_Click(View view) {
        if(generarCSV.isChecked()){
            Participant participant = db.getParticipant(session.getCurrentParticipantCurp());
            String nombreArchivo = participant.getFIRSTNAME() + "-" + participant.getLASTNAME() + ".csv";
            CsvGenerator generator = new CsvGenerator(nombreArchivo,participant,evaluation2,this);
            generator.GenerarEvaluacion2();
            Toast.makeText(this, "Evaluacion guardada con exito", Toast.LENGTH_SHORT).show();
        }
        Intent newIntent = new Intent(Test2Completed.this,DarAltas.class);
        startActivity(newIntent);
    }

    private static class LineDataFormatter implements IValueFormatter {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            int intvalue = (int)value;
            return Integer.toString(intvalue);
        }
    }

    private static class YAxisValuesFormatter implements IAxisValueFormatter{

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return "";
        }
    }

    private int onezero(String is_correct){
        if(is_correct.equals("Incorrecta")){
            return 0;
        }
        else{
            return 1;
        }
    }
}
