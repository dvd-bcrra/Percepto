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

import com.example.percepto.model.Evaluation1;
import com.example.percepto.model.Participant;
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

public class Test_completed extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineDataSet;

    DBHelper db;
    Session session;
    private Evaluation1 evaluation1;
    CheckBox generarCSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_completed);
        db = new DBHelper(this);
        session = new Session(this);
        generarCSV = findViewById(R.id.chkExportar);

        if(session.UserIsAdmin()){
            String evalid = Objects.requireNonNull(getIntent().getExtras()).getString("ID_EVAL1");
            evaluation1 = db.getEvaluation1(evalid);
            CargarGrafica(evaluation1);
        }
    }

    private void CargarGrafica(Evaluation1 eval){
        lineChart = findViewById(R.id.lineChart);
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<eval.getRecords().size(); i++) {
            int y = eval.getRecords().get(i).getSCORE();
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

        Participant participant = db.getParticipant(session.getCurrentParticipantCurp());
        String nombreArchivo = participant.getFIRSTNAME() + "-" + participant.getLASTNAME() + ".csv";
        if(generarCSV.isChecked()){
            CsvGenerator generator = new CsvGenerator(nombreArchivo,participant,evaluation1,this);
            generator.GenerarEvaluacion1();
            Toast.makeText(this, "Archivo Generado con exito", Toast.LENGTH_SHORT).show();
        }
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


}
