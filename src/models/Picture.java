package models;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Picture<T extends Pixel> {
    private final T[][] pixels;
    private final int realX;
    private final int realY;

    public Picture(int realX, int realY, Class<T> pixelClass) {
        this.realX = realX;
        this.realY = realY;
        int x;
        int y;
        if (realX % 16 == 0) {
            x = realX;
        } else {
            x = 16 - (realX % 16) + realX;
        }
        if (realY % 16 == 0) {
            y = realY;
        } else y = 16 - (realX % 16) + realY;
        pixels = (T[][]) Array.newInstance(pixelClass, x, y);
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public int getRealX() {
        return realX;
    }

    public int getRealY() {
        return realY;
    }

    public T getPixel(int x, int y) {
        return this.pixels[x][y];
    }

    public void setPixel(T pixel, int x, int y) {
        this.pixels[x][y] = pixel;
    }

    public int[][] getFirstChannel() {
        return (int[][]) Arrays.stream(pixels).map(ts -> Arrays.stream(ts).map(Pixel::getFirst).toArray()).toArray();
    }

    public int[][] getSecondChannel() {
        return (int[][]) Arrays.stream(pixels).map(ts -> Arrays.stream(ts).map(Pixel::getSecond).toArray()).toArray();
    }

    public int[][] getThirdChannel() {
        return (int[][]) Arrays.stream(pixels).map(ts -> Arrays.stream(ts).map(Pixel::getThird).toArray()).toArray();
    }
}
