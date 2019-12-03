package com.lukaszstachnik;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Gwiazda implements Serializable {
    private String nazwa;
    private Gwiazdozbior gwiazdozbior;
    private String nazwaKatalogowa;
    private String polkula;
    private Deklinacja deklinacja;
    private Rektascensja rektascensja;
    private double obserwowanaWielkoscGwiazdowa;
    private double odleglosc;
    private double absolutnaWielkoscGwiazdowa;
    private double temperatura;
    private double masa;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        int upperCounter = 0;
        int numberCounter = 0;

        for(int i = 0; i <= nazwa.length()-1; i++){
            if(Character.isUpperCase(nazwa.charAt(i))){
                upperCounter++;
            }
            else if(Character.isDigit(nazwa.charAt(i))){
                numberCounter++;
            }
        }

        if(upperCounter < 3 && numberCounter < 4){
            throw new ArithmeticException("Nazwa musi mieć conajmniej 3 duże litery i 4 cyfry");
        }
        else{
            this.nazwa = nazwa;
        }
    }

    public Gwiazdozbior getGwiazdozbior() {
        return gwiazdozbior;
    }

    public void setGwiazdozbior(Gwiazdozbior gwiazdozbior) {
        this.gwiazdozbior = gwiazdozbior;
    }

    public String getNazwaKatalogowa() {
        return nazwaKatalogowa;
    }

    public void setNazwaKatalogowa(String nazwaKatalogowa) {
        this.nazwaKatalogowa = nazwaKatalogowa;
    }

    public String getPolkula() {
        return polkula;
    }

    public void setPolkula(String polkula) {
        this.polkula = polkula;
    }

    public Deklinacja getDeklinacja() {
        return deklinacja;
    }

    public void setDeklinacja(Deklinacja deklinacja) {
        this.deklinacja = deklinacja;
    }

    public Rektascensja getRektascensja() {
        return rektascensja;
    }

    public void setRektascensja(Rektascensja rektascensja) {
        this.rektascensja = rektascensja;
    }

    public double getObserwowanaWielkoscGwiazdowa() {
        return obserwowanaWielkoscGwiazdowa;
    }

    public void setObserwowanaWielkoscGwiazdowa(double obserwowanaWielkoscGwiazdowa) {
        this.obserwowanaWielkoscGwiazdowa = obserwowanaWielkoscGwiazdowa;
    }

    public double getOdleglosc() {
        return odleglosc;
    }

    public void setOdleglosc(double odleglosc) {
        this.odleglosc = odleglosc;
    }

    public double getAbsolutnaWielkoscGwiazdowa() {
        return absolutnaWielkoscGwiazdowa;
    }

    public void setAbsolutnaWielkoscGwiazdowa(double absolutnaWielkoscGwiazdowa) {
        this.absolutnaWielkoscGwiazdowa = absolutnaWielkoscGwiazdowa;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public static void czytajDane(String nazwaPliku){
        ObjectInputStream strumien_odczytu = null;

        try{
            strumien_odczytu = new ObjectInputStream(new FileInputStream(nazwaPliku));

            Object obj = null;

            Gwiazdozbior test = new Gwiazdozbior("Arama");
            while ((obj = strumien_odczytu.readObject()) != null){

                if(obj instanceof Gwiazdozbior){

                    test = (Gwiazdozbior)obj;
                    System.out.println(test.getNazwa());

                }


            }



        }
        catch (EOFException ex){
            System.out.println("Koniec pliku");
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


}
