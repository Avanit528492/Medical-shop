package com.example.medicineshop;

public class User_Data {
    String ID;
    String FULL_NAME;
    String EMAIL;
    String MOBILE_NO;
    String PASSWORD;

    public User_Data(String ID, String FULL_NAME, String EMAIL, String MOBILE_NO, String PASSWORD) {
        this.ID = ID;
        this.FULL_NAME = FULL_NAME;
        this.EMAIL = EMAIL;
        this.MOBILE_NO = MOBILE_NO;
        this.PASSWORD = PASSWORD;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFULL_NAME() {
        return FULL_NAME;
    }

    public void setFULL_NAME(String FULL_NAME) {
        this.FULL_NAME = FULL_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
