package com.example.percepto.model;

public class User {
    private String NAME;
    private String USERNAME;
    private String PASSWORD;
    private boolean ADMIN;

    public boolean isADMIN() {
        return ADMIN;
    }

    public void setADMIN(boolean ADMIN) {
        this.ADMIN = ADMIN;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
