package com.example.percepto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.percepto.model.*;
import com.example.percepto.session.Session;

public class DarAltas extends AppCompatActivity {
    Session session;
    User user;
    Button btnRegisterUser;
    Button btnRegisterAdmin;
    Button btnRegisterFac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_altas);
        btnRegisterUser = findViewById(R.id.btnRegisterNext);
        btnRegisterAdmin = findViewById(R.id.btnRegisteradminNext);
        btnRegisterFac = findViewById(R.id.btnRegisterfaciNext);

        session = new Session(this);
        user = session.getUser();

        if (user == null){
            btnRegisterUser.setVisibility(View.INVISIBLE);
        }else{
            btnRegisterUser.setVisibility(View.VISIBLE);
        }
    }

    public void btnRegisterUser_Click(View view) {
        Intent newIntent2 = new Intent(DarAltas.this,Phase2.class);
        startActivity(newIntent2);
    }

    public void btnRegisterAdmin_Click(View view) {
        Intent newIntent2 = new Intent(DarAltas.this,Register.class);
        newIntent2.putExtra("IS_ADMIN",true);
        startActivity(newIntent2);
    }

    public void btnRegisterfac_Click(View view) {
        Intent newIntent = new Intent(DarAltas.this,Register.class);
        newIntent.putExtra("IS_ADMIN",false);
        startActivity(newIntent);


    }
}

