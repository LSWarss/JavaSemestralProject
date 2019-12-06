package com.lukaszstachnik;

public class Main {

    public static void main(String[] args) throws Exception {
        Constellation constellation1 = new Constellation("Ryb");
        Star test = new Star("LAMBDA12345", constellation1,  "PN", new Declination(80, 30, 30.30), new Right_ascension(14, 30, 30),10,3,2500, 0.5);
        Star.SerialiseStar(test);


    }
}
