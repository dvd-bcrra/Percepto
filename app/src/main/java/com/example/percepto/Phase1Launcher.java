package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Phase1Launcher extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase1_launcher);
    }


    public void btnPhase1Next_Click(View view) {
        Intent newIntent = new Intent(Phase1Launcher.this,Phase1.class);
        startActivity(newIntent);
    }
}
