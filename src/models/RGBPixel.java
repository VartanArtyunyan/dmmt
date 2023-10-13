package models;

public class RGBPixel extends Pixel{
    double r;
    double g;
    double b;

    public RGBPixel(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public double getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public double getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    double getFirst() {
        return getR();
    }

    @Override
    double getSecond() {
        return getG();
    }

    @Override
    double getThird() {
        return getB();
    }
}
