package MainClasses;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Constellation constellation1 = new Constellation("Ryb");
        Star test = new Star("LAMBDA12345", constellation1,  "PN", new Declination(80, 30, 30.30), new Right_ascension(14, 30, 30),10,3,2500, 0.5);
        Star test1 = new Star("RAMBON12345", constellation1,  "PN", new Declination(80, 30, 30.30), new Right_ascension(14, 30, 30),10,3,2500, 0.5);
        Star.SerialiseStar(test);
        Star.SerialiseStar(test1);

        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream("src\\Stars\\LAMBDA12345.obj"));
            Object obj = out.readObject();
            if(obj instanceof Star){
                System.out.println(((Star) obj).getName());
                System.out.println(((Star) obj).getConstellation().getNazwa());
                System.out.println(((Star) obj).getCatalogueName());
                System.out.println(((Star) obj).getAbsolute_magnitude());
                System.out.println(((Star) obj).getApperent_magnitude());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream("src\\Stars\\RAMBON12345.obj"));
            Object obj = out.readObject();
            if(obj instanceof Star){
                System.out.println(((Star) obj).getName());
                System.out.println(((Star) obj).getConstellation().getNazwa());
                System.out.println(((Star) obj).getCatalogueName());
                System.out.println(((Star) obj).getAbsolute_magnitude());
                System.out.println(((Star) obj).getApperent_magnitude());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }




    }
}
