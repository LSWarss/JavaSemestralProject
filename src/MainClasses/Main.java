package MainClasses;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
//        Constellation constellation1 = new Constellation("Ryb");
//        Star test = new Star("LAMBDA12345", constellation1,  "PN", new Declination(80, 30, 30.30), new Right_ascension(14, 30, 30),10,3,2500, 0.5);
//        Star test1 = new Star("RAMBON12345", constellation1,  "PN", new Declination(80, 30, 30.30), new Right_ascension(14, 30, 30),10,3,2500, 0.5);
//        Star.SerialiseStar(test);
//        Star.SerialiseStar(test1);
        Star.DeserializeStar("LAMBDA12345");
        Star.DeserializeStar("RAMBON12345");
        Star.DeleteStar("betaRyb");




    }
}
