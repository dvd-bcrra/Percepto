package com.example.percepto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.percepto.model.Participant;
import com.example.percepto.model.User;

public class DBHelper extends SQLiteOpenHelper {
    public static final String TAG = DBHelper.class.getSimpleName();
    private static final String DB_NAME = "singin.db";

    //TABLA USUARIOS
    public static final String USER_TABLE = "users";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_ISADMIN = "isadmin";

    //TABLA PARTICIPANTES
    private static final String PARTICIPANT_TABLE = "participants";              //  participantes
    private static final String COLUMN_PARTICIPANT_CURP = "curp";                //  CURP - (PK)
    private static final String COLUMN_PARTICIPANT_FIRSTNAME = "firstname";      //  nombre
    private static final String COLUMN_PARTICIPANT_LASTNAME = "lastname";        //  apellido
    private static final String COLUMN_PARTICIPANT_BIRTHDAY = "birthday";        //  fecha de nacimiento
    private static final String COLUMN_PARTICIPANT_AGE = "age";                  //  Edad
    private static final String COLUMN_PARTICIPANT_GROUP = "grupo";              //  grupo
    private static final String COLUMN_PARTICIPANT_TIME = "time";                //  tiempo en la institucion
    private static final String COLUMN_PARTICIPANT_LEVEL = "level";              //  Nivel educativo
    private static final String COLUMN_PARTICIPANT_IQ = "iq";                    //  Coeficeinte intelectual
    private static final String COLUMN_PARTICIPANT_TEPT = "tept";                //  Transtorno por estres postraumatico
    private static final String COLUMN_PARTICIPANT_REEXP = "reexp";              //  Reexperimentacion
    private static final String COLUMN_PARTICIPANT_AVOID = "avoid";              //  Evitacion
    private static final String COLUMN_PARTICIPANT_ACTIVE = "active";            //  Activacion
    private static final String COLUMN_PARTICIPANT_ANX = "anx";                  //  Ansiedad
    private static final String COLUMN_PARTICIPANT_DPRS = "dprs";                //  Depresion

    //TABLA EVALUACIONES 1
    private static final String EVAL1_TABLE = "evals1";                          //  Evaluaciones 1
    private static final String COLUMN_EVAL1_ID = "id";                          //  ID o clave de evaluacion 1 (PK)
    private static final String COLUMN_EVAL1_CURP = "curp";                      //  Curp del participante
    private static final String COLUMN_EVAL1_DATE = "date";                      //  Fecha de la evaluacion 1

    //TABLA REGISTROS1
    private static final String RECORDS1_TABLE = "eval1Records";                 //  Registros de la evaluacion 1
    private static final String COLUMN_RECORDS1_EVAL1_ID = "eval1_id";           //  ID o clave de la evaluacion 1 (FK)
    private static final String COLUMN_RECORD1_WORD = "word";                    //  Palabra a evaluar
    private static final String COLUMN_RECORD1_SCORE = "score";                  //  calificacion dada

    private SQLiteDatabase db ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //CREAR TABLA USER
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                 "(" + COLUMN_USER_NAME + " TEXT, "
                     + COLUMN_USER_USERNAME + " TEXT, "
                     + COLUMN_USER_PASSWORD + " TEXT, "
                     + COLUMN_USER_ISADMIN + " INTEGER " +");");

        //CREAR TABLA PARTICIPANTE
        db.execSQL(" CREATE TABLE " + PARTICIPANT_TABLE +
                "(" + COLUMN_PARTICIPANT_CURP + " TEXT, "
                + COLUMN_PARTICIPANT_FIRSTNAME + " TEXT, "
                + COLUMN_PARTICIPANT_LASTNAME + " TEXT, "
                + COLUMN_PARTICIPANT_BIRTHDAY + " TEXT, "
                + COLUMN_PARTICIPANT_AGE + " INTEGER, "
                + COLUMN_PARTICIPANT_GROUP + " TEXT, "
                + COLUMN_PARTICIPANT_TIME + " INTEGER, "
                + COLUMN_PARTICIPANT_LEVEL + " TEXT, "
                + COLUMN_PARTICIPANT_IQ + " TEXT, "
                + COLUMN_PARTICIPANT_TEPT + " TEXT, "
                + COLUMN_PARTICIPANT_REEXP + " TEXT, "
                + COLUMN_PARTICIPANT_AVOID + " TEXT, "
                + COLUMN_PARTICIPANT_ACTIVE + " TEXT, "
                + COLUMN_PARTICIPANT_ANX + " TEXT, "
                + COLUMN_PARTICIPANT_DPRS + " TEXT " +");");

        //CREAR TABLA EVALUACIONES
        db.execSQL(" CREATE TABLE " + EVAL1_TABLE +
                "(" + COLUMN_EVAL1_ID + " TEXT, "
                    + COLUMN_EVAL1_CURP + " TEXT, "
                    + COLUMN_EVAL1_DATE + " TEXT " +");");

        //CREAR TABLA REGISTROS
        db.execSQL(" CREATE TABLE " + RECORDS1_TABLE +
                "(" + COLUMN_RECORDS1_EVAL1_ID + " TEXT, "
                + COLUMN_RECORD1_WORD + " TEXT, "
                + COLUMN_RECORD1_SCORE + " INTEGER " +");");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + PARTICIPANT_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + EVAL1_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + RECORDS1_TABLE + ";");
        onCreate(db);
    }


    //AGREGAR USUARIO
    void AddUser(User user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNAME());
        values.put(COLUMN_USER_USERNAME, user.getPASSWORD());
        values.put(COLUMN_USER_PASSWORD, user.getPASSWORD());
        values.put(COLUMN_USER_ISADMIN,user.isADMIN());

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    //AGREGAR PARTICIPANTE
    void AddParticipant(Participant participant){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PARTICIPANT_CURP,participant.getCURP());
        values.put(COLUMN_PARTICIPANT_FIRSTNAME,participant.getFIRSTNAME());
        values.put(COLUMN_PARTICIPANT_LASTNAME,participant.getLASTNAME());
        values.put(COLUMN_PARTICIPANT_BIRTHDAY,participant.getBIRTHDAY());
        values.put(COLUMN_PARTICIPANT_AGE,participant.getAGE());
        values.put(COLUMN_PARTICIPANT_GROUP,participant.getGROUP());
        values.put(COLUMN_PARTICIPANT_TIME,participant.getTIME());
        values.put(COLUMN_PARTICIPANT_LEVEL,participant.getLEVEL());
        values.put(COLUMN_PARTICIPANT_IQ,participant.getIQ());
        values.put(COLUMN_PARTICIPANT_TEPT,participant.getTEPT());
        values.put(COLUMN_PARTICIPANT_REEXP,participant.getREEXP());
        values.put(COLUMN_PARTICIPANT_AVOID,participant.getAVOID());
        values.put(COLUMN_PARTICIPANT_ACTIVE,participant.getACTIVE());
        values.put(COLUMN_PARTICIPANT_ANX,participant.getANX());
        values.put(COLUMN_PARTICIPANT_DPRS,participant.getDPRS());

        db.insert(PARTICIPANT_TABLE,null,values);
        db.close();
    }

    public Cursor getUsersData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
    }
}
