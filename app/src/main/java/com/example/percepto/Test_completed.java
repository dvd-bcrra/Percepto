package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class Test_completed extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_completed);
    }
    public void btnReportSkip_Click(View view) {
        Intent newIntent = new Intent(Test_completed.this,Phase1.class);
        startActivity(newIntent);
    }


    public void btnCompleted_click(View view) {
        Intent newIntent1 = new Intent(Test_completed.this,Phase1.class);
        startActivity(newIntent1);
    }

    public void btnCompletedNext_Click(View view) {
        Intent newIntent2 = new Intent(Test_completed.this,Phase1.class);
        startActivity(newIntent2);


    }
}
