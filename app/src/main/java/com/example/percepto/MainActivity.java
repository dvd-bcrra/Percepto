package com.example.percepto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.percepto.Words.JsonWords;

public class MainActivity extends AppCompatActivity {

    EditText username1, pass;
    Button login;
    TextView regis;
    Cursor cursor;
    CheckBox show;
    DBHelper dbHelper;
    SQLiteDatabase db;
    JsonWords jw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        db = dbHelper.getReadableDatabase();
        jw = JsonWords.getInstance(this);

        regis = (TextView) findViewById(R.id.regis);

        username1 = (EditText) findViewById(R.id.luser);
        pass = (EditText) findViewById(R.id.lpass);
        show = (CheckBox) findViewById(R.id.showPass);

        login = (Button)findViewById(R.id.login);
        showPass();

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] datos = new String[]{
                        username1.getText().toString(),
                        pass.getText().toString()
                };

                cursor = db.rawQuery("SELECT * FROM " + DBHelper.USER_TABLE
                                + " WHERE " + DBHelper.COLUMN_USER_USERNAME + " =? AND "
                                + DBHelper.COLUMN_USER_PASSWORD + " =?",datos);


                if(username1.getText().toString().equals("")|| pass.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Username and Password can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        cursor.moveToFirst();

                        Toast.makeText(MainActivity.this, "Logged In succesfully!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, Phase1.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid username or password!",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }

    public void showPass(){
        show.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }


    public void onBackPressed()
    {

        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}