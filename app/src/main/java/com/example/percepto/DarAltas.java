package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DarAltas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_altas);
    }

    public void btnRegisterUser_Click(View view) {
        Intent newIntent2 = new Intent(DarAltas.this,Register.class);
        startActivity(newIntent2);
    }

    public void btnRegisterAdmin_Click(View view) {
        Intent newIntent2 = new Intent(DarAltas.this,Register.class);
        startActivity(newIntent2);
    }

    public void btnRegisterfac_Click(View view) {
        Intent newIntent = new Intent(DarAltas.this,Test_completed.class);
        startActivity(newIntent);


    }
}

