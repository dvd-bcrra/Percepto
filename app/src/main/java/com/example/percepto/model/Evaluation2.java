package com.example.percepto.model;

import java.util.ArrayList;

public class Evaluation2 {
    private String ID;
    private String CURP;
    private String DATE;

    public Evaluation2(){
        records = new ArrayList<Record2>();
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

    private ArrayList<Record2> records;

    public void AddRecord(String word, String selected_word, String time, String is_correct){
        Record2 record2 = new Record2();
        record2.setEVAL2ID(getID());
        record2.setWORD(word);
        record2.setSELECTED_WORD(selected_word);
        record2.setTIME(time);
        record2.setIS_CORRECT(is_correct);
        records.add(record2);
    }

    public ArrayList<Record2> getRecords(){
        return records;
    }

    public void setRecords(ArrayList<Record2> records) { this.records = records; }
}
