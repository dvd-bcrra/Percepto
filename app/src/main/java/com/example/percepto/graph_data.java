package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

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
        for (int i = 0; i<=20; i++){
            int y = (int) (Math.random() * 8) + 1;
            lineEntries.add(new Entry(i,y));
        }

        // Unimos los datos al data set
        lineDataSet = new LineDataSet(lineEntries, "Platzi");

        // Asociamos al gráfico
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        lineData.setValueFormatter(new myFormatter());
        lineChart.setData(lineData);

    }

    private static class myFormatter implements IValueFormatter{
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            int intvalue = (int)value;
            return Integer.toString(intvalue);
        }
    }
}
