package com.lukaszstachnik;

public class Declination {
    public int getXx() {
        return xx;
    }

    public int getYy() {
        return yy;
    }

    public double getZz() {
        return zz;
    }

    public void setXx(int xx) {
        this.xx = xx;
    }

    public void setYy(int yy) {
        this.yy = yy;
    }

    public void setZz(double zz) {
        this.zz = zz;
    }

    private int xx;
    private int yy;
    private double zz;
    public Declination(int xx, int yy, double zz){
        this.xx = xx;
        this.yy = yy;
        this.zz = zz;
    }


}
