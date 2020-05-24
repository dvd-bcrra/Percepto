package com.example.percepto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "singin.db";

    //TABLA USUARIOS
    public static final String USER_TABLE = "users";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_CNFRMPASS = "cnfrmpassword";

    //TABLA PARTICIPANTES
    public static final String PARTICIPANT_TABLE = "participants";              //  participantes
    public static final String COLUMN_PARTICIPANT_CURP = "curp";                //  CURP - Primary key
    public static final String COLUMN_PARTICIPANT_FIRSTNAME = "firstname";      //  nombre
    public static final String COLUMN_PARTICIPANT_LASTNAME = "lastname";        //  apellido
    public static final String COLUMN_PARTICIPANT_BIRTHDAY = "birthday";        //  fecha de nacimiento
    public static final String COLUMN_PARTICIPANT_AGE = "age";                  //  Edad
    public static final String COLUMN_PARTICIPANT_GROUP = "group";              //  grupo
    public static final String COLUMN_PARTICIPANT_TIME = "time";                //  tiempo en la institucion
    public static final String COLUMN_PARTICIPANT_LEVEL = "level";              //  Nivel educativo
    public static final String COLUMN_PARTICIPANT_IQ = "iq";                    //  Coeficeinte intelectual
    public static final String COLUMN_PARTICIPANT_TEPT = "tept";                //  Transtorno por estres postraumatico
    public static final String COLUMN_PARTICIPANT_REEXP = "reexp";              //  Reexperimentacion
    public static final String COLUMN_PARTICIPANT_AVOID = "avoid";              //  Evitacion
    public static final String COLUMN_PARTICIPANT_ACTIVE = "active";            //  Activacion
    public static final String COLUMN_PARTICIPANT_ANX = "anx";                  //  Ansiedad
    public static final String COLUMNS_PARTICIPANT_DPRS = "dprs";               //  Depresion


    public SQLiteDatabase db ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                 "(" + COLUMN_USER_NAME + " TEXT, "
                     + COLUMN_USER_USERNAME + " TEXT, "
                     + COLUMN_USER_PASSWORD + " TEXT, "
                     + COLUMN_USER_CNFRMPASS + " TEXT " +")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /* Storing User details*/

    void addUser(String name, String username, String password, String cnfrmpassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name);
        values.put(COLUMN_USER_USERNAME, username);
        values.put(COLUMN_USER_PASSWORD, password);
        values.put(COLUMN_USER_CNFRMPASS, cnfrmpassword);

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
    }
}
