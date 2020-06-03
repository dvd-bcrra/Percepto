package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class graph_data extends AppCompatActivity {
    private LineChart lineChart;
    private LineDataSet lineDataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_data);
        // Enlazamos al XML

        lineChart = findViewById(R.id.lineChart);
        ArrayList<Entry> lineEntries = new ArrayList<Entry>();
        for (int i = 0; i<11; i++){
            float y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new Entry((float) i,(float)y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Platzi");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineChart.setData(lineData);
    }
}
