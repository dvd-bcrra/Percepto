package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Test_completed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_completed);
    }
    public void btnCompletedNext_Click(View view) {
        Intent newIntent = new Intent(Test_completed.this,Phase1.class);
        startActivity(newIntent);


        btnCompleted_click
                btnReportSkip_Click
    }
}
