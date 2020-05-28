package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Create_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

    }
    private final static String[] names = { "Ninguna", "TEPT-ASI", "CH Control Hogar",
            "CF Contol Familia" };

    public void signinBtn(View view) {
        Intent intent = new Intent(Create_account.this, Phase1Launcher.class);
        startActivity(intent);
    }
}