package com.example.percepto.model;
import com.example.percepto.session.Session;

import java.util.ArrayList;

public class Evaluation1 {
    private Session session;
    private String ID;
    private String CURP;
    private String DATE;

    public Evaluation1(){
        records = new ArrayList<Record1>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    private ArrayList<Record1> records;

    public void AddRecord(String word, int score){
        Record1 record1 = new Record1();
        record1.setEVAL1ID(session.getCurrentParticipantCurp());
        record1.setWORD(word);
        record1.setSCORE(score);
        records.add(record1);
    }

    public ArrayList<Record1> getRecords(){
        return records;
    }
}
