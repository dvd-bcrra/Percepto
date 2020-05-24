package com.example.percepto.model;

import java.util.List;

public class Evaluation1 {
    private String ID;
    private String CURP;
    private String DATE;

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

    public List<Record1> records;
}
