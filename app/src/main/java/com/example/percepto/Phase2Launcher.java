package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Phase2Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phase2_launcher);
    }

    public void btnPhase2Next_Click(View view) {
        Intent newIntent = new Intent(Phase2Launcher.this,Phase2.class);
        startActivity(newIntent);
    }

    public void btnPhase2Back_Click(View view) {
    }
}
