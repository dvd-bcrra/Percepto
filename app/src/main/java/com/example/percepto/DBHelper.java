package com.example.percepto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.percepto.model.*;

import java.util.ArrayList;

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
    public static final String PARTICIPANT_TABLE = "participants";              //  participantes
    private static final String COLUMN_PARTICIPANT_CURP = "curp";                //  CURP - (PK)
    private static final String COLUMN_PARTICIPANT_FIRSTNAME = "firstname";      //  nombre
    private static final String COLUMN_PARTICIPANT_LASTNAME = "lastname";        //  apellido
    // private static final String COLUMN_PARTICIPANT_BIRTHDAY = "birthday";     //  fecha de nacimiento
    private static final String COLUMN_PARTICIPANT_AGE = "age";                  //  Edad
    private static final String COLUMN_PARTICIPANT_GROUP = "grupo";              //  grupo
    private static final String COLUMN_PARTICIPANT_TIME = "time";                //  tiempo en la institucion
    private static final String COLUMN_PARTICIPANT_LEVEL = "level";              //  Nivel educativo
    private static final String COLUMN_PARTICIPANT_IQ = "iq";                    //  Coeficeinte intelectual
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

    //TABLA REGISTROS 1
    private static final String RECORDS1_TABLE = "eval1Records";                 //  Registros de la evaluacion 1
    private static final String COLUMN_RECORDS1_EVAL1_ID = "eval1_id";           //  ID o clave de la evaluacion 1 (FK)
    private static final String COLUMN_RECORD1_WORD = "word";                    //  Palabra a evaluar
    private static final String COLUMN_RECORD1_SCORE = "score";                  //  calificacion dada

    //TABLA EVALUACIONES 2
    private static final String EVAL2_TABLE = "evals2";                          //  Evaluaciones 2
    private static final String COLUMN_EVAL2_ID = "id";                          //  ID o clave de evaluacion 2 (PK)
    private static final String COLUMN_EVAL2_CURP = "curp";                      //  Curp del participante
    private static final String COLUMN_EVAL2_DATE = "date";                      //  Fecha de la evaluacion 2

    //TABLA REGISTROS 2
    private static final String RECORDS2_TABLE = "eval2Records";                //  Registros de la evaluacion 2
    private static final String COLUMN_RECORDS2_EVAL2_ID = "eval2_id";          //  ID o clave de la evaluacion 2 (FK)
    private static final String COLUMN_RECORD2_WORD = "word";                   //  Palabra a evaluar
    private static final String COLUMN_RECORD2_SELECTED_WORD = "selected";      //  Palabra seleccionada
    private static final String COLUMN_RECORD2_TIME = "time";                   //  Tiempo transcurrido
    private static final String COLUMN_RECORD2_IS_CORRECT = "iscorrect";        //  Evaluacion de la palabra seleccionada con la palabra a evaluar

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
                // + COLUMN_PARTICIPANT_BIRTHDAY + " TEXT, "
                + COLUMN_PARTICIPANT_AGE + " INTEGER, "
                + COLUMN_PARTICIPANT_GROUP + " TEXT, "
                + COLUMN_PARTICIPANT_TIME + " TEXT, "
                + COLUMN_PARTICIPANT_LEVEL + " TEXT, "
                + COLUMN_PARTICIPANT_IQ + " TEXT, "
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
                + COLUMN_RECORD1_SCORE + " INTEGER" +");");

        //CREAR TABLA EVALUACIONES
        db.execSQL(" CREATE TABLE " + EVAL2_TABLE +
                "(" + COLUMN_EVAL2_ID + " TEXT, "
                + COLUMN_EVAL2_CURP + " TEXT, "
                + COLUMN_EVAL2_DATE + " TEXT " +");");

        //CREAR TABLA REGISTROS
        db.execSQL(" CREATE TABLE " + RECORDS2_TABLE +
                "(" + COLUMN_RECORDS2_EVAL2_ID + " TEXT, "
                + COLUMN_RECORD2_WORD + " TEXT, "
                + COLUMN_RECORD2_SELECTED_WORD + " TEXT,"
                + COLUMN_RECORD2_TIME + " TEXT,"
                + COLUMN_RECORD2_IS_CORRECT + " TEXT" + ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + PARTICIPANT_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + EVAL1_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + RECORDS1_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + EVAL2_TABLE + ";");
        db.execSQL("DROP TABLE IF EXISTS " + RECORDS2_TABLE + ";");
        onCreate(db);
    }

    //AGREGAR USUARIO
    void AddUser(User user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getNAME());
        values.put(COLUMN_USER_USERNAME, user.getUSERNAME());
        values.put(COLUMN_USER_PASSWORD, user.getPASSWORD());
        values.put(COLUMN_USER_ISADMIN, user.isADMIN());

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    //AGREGAR PARTICIPANTE
    void AddParticipant(Participant participant){
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_PARTICIPANT_CURP, participant.getCURP());
        values.put(COLUMN_PARTICIPANT_FIRSTNAME, participant.getFIRSTNAME());
        values.put(COLUMN_PARTICIPANT_LASTNAME, participant.getLASTNAME());
        values.put(COLUMN_PARTICIPANT_AGE, participant.getAGE());
        values.put(COLUMN_PARTICIPANT_GROUP, participant.getGROUP());
        values.put(COLUMN_PARTICIPANT_TIME, participant.getTIME());
        values.put(COLUMN_PARTICIPANT_LEVEL, participant.getLEVEL());
        values.put(COLUMN_PARTICIPANT_IQ, participant.getIQ());
        values.put(COLUMN_PARTICIPANT_REEXP, participant.getREEXP());
        values.put(COLUMN_PARTICIPANT_AVOID, participant.getAVOID());
        values.put(COLUMN_PARTICIPANT_ACTIVE, participant.getACTIVE());
        values.put(COLUMN_PARTICIPANT_ANX, participant.getANX());
        values.put(COLUMN_PARTICIPANT_DPRS, participant.getDPRS());

        db.insert(PARTICIPANT_TABLE,null,values);


        db.close();
    }

    //OBTENER PARTICIPANTE
    public Participant getParticipant(String curp){
        db = this.getReadableDatabase();
        Participant temp = new Participant();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + PARTICIPANT_TABLE + " WHERE " + COLUMN_PARTICIPANT_CURP + " = '" + curp + "';",null);
        if(cursor.moveToFirst()){
            temp.setFIRSTNAME(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_FIRSTNAME)));
            temp.setLASTNAME(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_LASTNAME)));
            temp.setCURP(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_CURP)));
            temp.setAGE(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_AGE))));
            temp.setGROUP(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_GROUP)));
            temp.setTIME(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_TIME)));
            temp.setLEVEL(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_LEVEL)));
            temp.setIQ(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_IQ)));
            temp.setREEXP(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_REEXP)));
            temp.setAVOID(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_AVOID)));
            temp.setACTIVE(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_ACTIVE)));
            temp.setANX(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_ANX)));
            temp.setDPRS(cursor.getString(cursor.getColumnIndex(COLUMN_PARTICIPANT_DPRS)));
        }

        cursor.close();
        db.close();
        return temp;
    }

    //AGREGAR EVALUACION 1
    void AddEvaluation1(Evaluation1 evaluation1){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EVAL1_ID, evaluation1.getID());
        values.put(COLUMN_EVAL1_CURP,evaluation1.getCURP());
        values.put(COLUMN_EVAL1_DATE,evaluation1.getDATE());

        db.insert(EVAL1_TABLE,null,values);
        db.close();

        for (Record1 record : evaluation1.getRecords()) {
            AddRecord1(record);
        }
    }

    //AGREGAR RECORD TIPO 1
    private void AddRecord1(Record1 record){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECORDS1_EVAL1_ID,record.getEVAL1ID());
        values.put(COLUMN_RECORD1_WORD,record.getWORD());
        values.put(COLUMN_RECORD1_SCORE, record.getSCORE());

        db.insert(RECORDS1_TABLE,null,values);
        db.close();
    }

    //OBTENER EVALUACION 1
    public Evaluation1 getEvaluation1(String id){
        db = this.getReadableDatabase();
        Evaluation1 temp = new Evaluation1();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + EVAL1_TABLE + " WHERE " + COLUMN_EVAL1_ID + " = '" + id + "';",null);
        if(cursor.moveToFirst()){
            temp.setID(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL1_ID)));
            temp.setCURP(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL1_CURP)));
            temp.setDATE(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL1_DATE)));
        }

        temp.setRecords(getRecords1(temp.getID()));

        cursor.close();
        db.close();
        return temp;
    }

    //OBTENER RECORD TIPO 1
    private ArrayList<Record1> getRecords1(String EvalID){
        db = this.getReadableDatabase();
        Cursor cursor;
        ArrayList<Record1> temp = new ArrayList<>();

        cursor = db.rawQuery("SELECT * FROM " + RECORDS1_TABLE + " WHERE " + COLUMN_RECORDS1_EVAL1_ID + " = '" + EvalID + "';",null);
        if(cursor.moveToFirst()){
            do{
                Record1 record = new Record1();
                record.setEVAL1ID(cursor.getString(cursor.getColumnIndex(COLUMN_RECORDS1_EVAL1_ID)));
                record.setWORD(cursor.getString(cursor.getColumnIndex(COLUMN_RECORD1_WORD)));
                record.setSCORE(cursor.getInt(cursor.getColumnIndex(COLUMN_RECORD1_SCORE)));
                temp.add(record);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return temp;
    }

    //AGREGAR EVALUACION 2
    void AddEvaluation2(Evaluation2 evaluation2){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_EVAL2_ID, evaluation2.getID());
        values.put(COLUMN_EVAL2_CURP,evaluation2.getCURP());
        values.put(COLUMN_EVAL2_DATE,evaluation2.getDATE());

        db.insert(EVAL2_TABLE,null,values);
        db.close();

        for (Record2 record : evaluation2.getRecords()) {
            AddRecord2(record);
        }
    }

    //AGREGAR RECORD TIPO 2
    private void AddRecord2(Record2 record){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_RECORDS2_EVAL2_ID,record.getEVAL2ID());
        values.put(COLUMN_RECORD2_WORD,record.getWORD());
        values.put(COLUMN_RECORD2_SELECTED_WORD, record.getSELECTED_WORD());
        values.put(COLUMN_RECORD2_TIME, record.getTIME());
        values.put(COLUMN_RECORD2_IS_CORRECT, record.getIS_CORRECT());

        db.insert(RECORDS2_TABLE,null,values);
        db.close();
    }

    //OBTENER EVALUACION 2
    public Evaluation2 getEvaluation2(String id){
        db = this.getReadableDatabase();
        Evaluation2 temp = new Evaluation2();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + EVAL2_TABLE + " WHERE " + COLUMN_EVAL2_ID + " = '" + id + "';",null);
        if(cursor.moveToFirst()){
            temp.setID(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL2_ID)));
            temp.setCURP(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL2_CURP)));
            temp.setDATE(cursor.getString(cursor.getColumnIndex(COLUMN_EVAL2_DATE)));
        }

        temp.setRecords(getRecords2(temp.getID()));

        cursor.close();
        db.close();
        return temp;
    }

    //OBTENER RECORD TIPO 2
    private ArrayList<Record2> getRecords2(String EvalID){
        db = this.getReadableDatabase();
        Cursor cursor;
        ArrayList<Record2> temp = new ArrayList<>();

        cursor = db.rawQuery("SELECT * FROM " + RECORDS2_TABLE + " WHERE " + COLUMN_RECORDS2_EVAL2_ID + " = '" + EvalID + "';",null);
        if(cursor.moveToFirst()){
            do{
                Record2 record = new Record2();
                record.setEVAL2ID(cursor.getString(cursor.getColumnIndex(COLUMN_RECORDS2_EVAL2_ID)));
                record.setWORD(cursor.getString(cursor.getColumnIndex(COLUMN_RECORD2_WORD)));
                record.setSELECTED_WORD(cursor.getString(cursor.getColumnIndex(COLUMN_RECORD2_SELECTED_WORD)));
                record.setTIME(cursor.getString(cursor.getColumnIndex(COLUMN_RECORD2_TIME)));
                record.setIS_CORRECT(cursor.getString(cursor.getColumnIndex(COLUMN_RECORD2_IS_CORRECT)));
                temp.add(record);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return temp;
    }
}
