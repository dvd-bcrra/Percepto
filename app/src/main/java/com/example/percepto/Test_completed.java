package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.percepto.model.Evaluation1;
import com.example.percepto.session.Session;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Objects;

public class Test_completed extends AppCompatActivity {

    private LineChart lineChart;
    private LineDataSet lineDataSet;

    DBHelper db;
    Session session;
    private Evaluation1 evaluation1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_completed);
        db = new DBHelper(this);
        session = new Session(this);

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
            float y = (int) eval.getRecords().get(i).getSCORE();
            lineEntries.add(new Entry((int) i, (float) y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Resultados");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);
    }

    public void btnCompletedNext_Click(View view) {
        Intent newIntent2 = new Intent(Test_completed.this,DarAltas.class);
        startActivity(newIntent2);
    }
}
