package com.lukaszstachnik;

public class Constellation {
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    private String nazwa;
    public Constellation(String nazwa){
        this.nazwa = nazwa;
    }
}
