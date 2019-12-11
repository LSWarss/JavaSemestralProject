package MainClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Star implements Serializable {
    private String name;
    private Constellation constellation;
    private String catalogueName;
    private String hemisphere;
    private Declination declination;
    private Right_ascension right_ascension;
    private double apperent_magnitude;
    private double distance;
    private double absolute_magnitude;
    private double temperature;
    private double mass;

    private static List<GreekAlphabet> greekLetters = Arrays.asList(GreekAlphabet.values());

    public String getName() {
        return name;
    }

    private void setName(String name) throws Exception {
        int upperCounter = 0;
        int numberCounter = 0;

        for(int i = 0; i <= name.length()-1; i++){
            if(Character.isUpperCase(name.charAt(i))){
                upperCounter++;
            }
            else if(Character.isDigit(name.charAt(i))){
                numberCounter++;
            }
        }

        if(upperCounter < 3 && numberCounter < 4){
            throw new Exception("Nazwa musi mieć conajmniej 3 duże litery i 4 cyfry");
        }
        else{
            this.name = name;
        }
    }

    public Constellation getConstellation() {
        return constellation;
    }

    private void setConstellation(Constellation constellation) {
        Object obj;
        File constellationFile = new File("src\\Constellations\\" + constellation.getNazwa() + ".obj");
        boolean constellationExist = constellationFile.exists();

        if(constellationExist == true) {
            try {
                ObjectInputStream loadStream = new ObjectInputStream(new FileInputStream("src\\Constellations\\" + constellation.getNazwa() + ".obj"));
                while ((obj = loadStream.readObject()) != null) {
                    if (obj instanceof Constellation && ((Constellation) obj).getNazwa().equals(constellation.getNazwa())) {
                        this.constellation = constellation;
                    }
                }
            } catch (EOFException ex) {
                System.out.println("End of file");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(constellationExist == false){
            try{
                ObjectOutputStream saveStream = new ObjectOutputStream(new FileOutputStream("src\\Constellations\\" + constellation.getNazwa() + ".obj"));
                saveStream.writeObject(constellation);
                this.constellation = constellation;
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String getCatalogueName() {
        return catalogueName;
    }

    public void setCatalogueName() {
        StringBuffer catalogueTry = new StringBuffer();
        catalogueName = greekLetters.get(0) + constellation.getNazwa();
        Object obj;
        try {
            File baseFolder = new File("src\\Stars"); //Method for checking if the files with catalogueName exist, and if not it will create one
            File[] files = baseFolder.listFiles();
            for (File f : files) {
                if (!f.isDirectory()) {
                    ObjectInputStream fileLoading = new ObjectInputStream(new FileInputStream(f));
                    obj = fileLoading.readObject();
                        if(obj instanceof Star){
                            if(((Star) obj).getConstellation().getNazwa().equals(getConstellation().getNazwa())){
                                   for(int i = 0; i < greekLetters.size(); i++){
                                       if(((Star) obj).getName().equals(this.name) && ((Star) obj).getCatalogueName() != null && ((Star) obj).getCatalogueName().equals(greekLetters.get(i) + constellation.getNazwa())){
                                           catalogueTry = new StringBuffer(((Star) obj).getCatalogueName());
                                           this.catalogueName = catalogueTry.toString();
                                           break;
                                       }
                                       else if(((Star) obj).getCatalogueName() != null && ((Star) obj).getCatalogueName().equals(greekLetters.get(i) + constellation.getNazwa())){
                                           catalogueTry = new StringBuffer(greekLetters.get(i + 1) + constellation.getNazwa());
                                           this.catalogueName = catalogueTry.toString();
                                           break;
                                       }
//                                       else if(((Star) obj).getCatalogueName() == null){
//                                           catalogueTry = new StringBuffer(greekLetters.get(i) + constellation.getNazwa());
//                                           this.catalogueName = catalogueTry.toString();
//                                           break;
//                                       }
                                       else{
                                           catalogueTry = new StringBuffer(greekLetters.get(i) + constellation.getNazwa());
                                           this.catalogueName = catalogueTry.toString();
                                           break;
                                       }
                                   }
                            }
                            else {
                                catalogueTry.append(greekLetters.get(0)).append(constellation.getNazwa());
                                this.catalogueName = catalogueTry.toString();
                            }

                        }
                    }

                }

        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    public String getHemisphere() {
        return hemisphere;
    }

    private void setHemisphere(String hemisphere) throws Exception {
        if(hemisphere.equals("PD") || hemisphere.equals("PN")){
            this.hemisphere = hemisphere;
        }
        else{
            throw new Exception("There is not such hemisphere on our planet");
        }
    }

    public Declination getDeclination() {
        return declination;
    }

    private void setDeclination(Declination declination) throws Exception {
        if(getHemisphere().equals("PN")){
            if(declination.getXx() > 90 || declination.getXx() < 0){
                throw new Exception("Wrong degree for this hemisphere");
            }
            else if(declination.getYy() > 60){
                declination.setXx(declination.getXx() + 1);
            }
            else if(declination.getZz() > 60.00){
                declination.setYy(declination.getYy() + 1);
            }
            else if(declination.getXx() < 90 || declination.getXx() > 0) {
                this.declination = declination;
            }
        }

        if(getHemisphere().equals("PD")) {
            if (declination.getXx() < -90 || declination.getXx() > 0) {
                throw new Exception("Wrong degree for this hemisphere");
            } else if (declination.getYy() > 60) {
                declination.setXx(declination.getXx() + 1);
            } else if (declination.getZz() > 60.00) {
                declination.setYy(declination.getYy() + 1);
            } else if(declination.getXx() > -90 || declination.getXx() < 0){
                this.declination = declination;
            }
        }
    }

    public Right_ascension getRight_ascension() {
        return right_ascension;
    }

    private void setRight_ascension(Right_ascension right_ascension) throws Exception {
        if(right_ascension.getXx() < 0 || right_ascension.getXx() > 24){
            throw new Exception("Right ascension is taking only hours between 0 and 24");
        }
        else if(right_ascension.getYy() > 60){
            right_ascension.setXx(right_ascension.getXx() + 1);
        }
        else if(right_ascension.getZz() > 60){
            right_ascension.setYy(right_ascension.getYy() + 1);
        }
        else{
            this.right_ascension = right_ascension;
        }

    }

    public double getApperent_magnitude() {
        return apperent_magnitude;
    }

    private void setApperent_magnitude(double apperent_magnitude) throws Exception {
        if(apperent_magnitude > -26.74 && apperent_magnitude < 15.0){
            this.apperent_magnitude = apperent_magnitude;
        }
        else{
            throw new Exception("Wrong value for the apperent_magnitude (it takes only values between -26.74 and 15.0");
        }
    }

    public double getDistance() {
        return distance;
    }

    private void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAbsolute_magnitude() {
        return absolute_magnitude;
    }

    private void setAbsolute_magnitude() {
        this.absolute_magnitude = getAbsolute_magnitude() - (5 * Math.log10(distance * 3.26));
    }

    public double getTemperature() {
        return temperature;
    }

    private void setTemperature(double temperature) throws Exception {
        if(temperature < 2000.0){
            throw new Exception("To low temperature for a star");
        }
        else{
            this.temperature = temperature;
        }
    }

    public double getMass() {
        return mass;
    }

    private void setMass(double mass) throws Exception {
        if(mass >= 0.1 && mass <= 50.0){
            this.mass = mass;
        }
        else{
            throw new Exception("Wrong mass ( mass value can be in range from 0.1 to 50 in reference to sun");
        }
    }

    public Star(String name, Constellation constellation, String hemisphere, Declination declination, Right_ascension right_ascension, double apperent_magnitude, double distance, double temperature, double mass) throws Exception {
        setName(name);
        setConstellation(constellation);
        setCatalogueName();
        setHemisphere(hemisphere);
        setDeclination(declination);
        setRight_ascension(right_ascension);
        setApperent_magnitude(apperent_magnitude);
        setDistance(distance);
        setAbsolute_magnitude();
        setTemperature(temperature);
        setMass(mass);
    }

    public static void SerialiseStar(Star star) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream( "src\\Stars\\" + star.getName() + ".obj"));
            outputStream.writeObject(star);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void DeserializeStar(String starName){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src\\Stars\\" + starName + ".obj"));
            Object obj = inputStream.readObject();
            if(obj instanceof Star){
                System.out.println(((Star) obj).getName());
                System.out.println(((Star) obj).getConstellation().getNazwa());
                System.out.println(((Star) obj).getCatalogueName());
                System.out.println(((Star) obj).getHemisphere());

            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
