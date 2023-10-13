package models;

public class YCbCrPixel extends Pixel {
    double y;
    double cb;
    double cr;

    public YCbCrPixel(double y, double cb, double cr) {
        this.y = y;
        this.cb = cb;
        this.cr = cr;
    }

    public YCbCrPixel(RGBPixel rgbPixel) {
        this.y = 0 + (0.299 * rgbPixel.getR() + 0.587 * rgbPixel.getG() + 0.114 * rgbPixel.getB());
        this.cb = 0.5 + (-0.1687 * rgbPixel.getR() - 0.3312 * rgbPixel.getG() + 0.5 * rgbPixel.getB());
        this.cr = 0.5 + (0.5 * rgbPixel.getR() + -0.4186 * rgbPixel.getG() - 0.0813 * rgbPixel.getB());
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getCb() {
        return cb;
    }

    public void setCb(int cb) {
        this.cb = cb;
    }

    public double getCr() {
        return cr;
    }

    public void setCr(int cr) {
        this.cr = cr;
    }

    @Override
    double getFirst() {
        return getY();
    }

    @Override
    double getSecond() {
        return getCb();
    }

    @Override
    double getThird() {
        return getCr();
    }
}
