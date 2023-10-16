package models;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Picture<T extends Pixel> {
    private final T[][] pixels;
    private final int realX;
    private final int realY;

    private Picture(int realX, int realY, T[][] pixels) {
        this.realX = realX;
        this.realY = realY;
       this.pixels = pixels;
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



    public static class PictureBuilder <T extends Pixel> {

        private T[][] pixels;

        private int realX;

        private int realY;

        private int lastRealXCoord;

        private int lastRealYCoord;

        public PictureBuilder (int realX, int realY, Class<T> pixelClass) {
            this.realX = realX;
            this.realY = realY;
            lastRealXCoord = realX - 1;
            lastRealYCoord = realY - 1;
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

        public PictureBuilder withPixels(T[][] pixels){
            for(int x = 0; x < pixels.length; x++){
                for(int y = 0; y < pixels.length; y++){
                    this.pixels[x][y] = pixels[x][y];
                }
            }
            this.pixels = pixels;
            return this;
        }

        public Picture build(){


            for(int x = realX; x < pixels.length; x++){
                for(int y = 0; y < realY; y++){
                   pixels[x][y] = pixels[lastRealXCoord][y];
                }
            }

            for(int x = 0; x < realX; x++){
                for(int y = realY; y > pixels[x].length; y++){
                  pixels[x][y] = pixels[x][lastRealYCoord];
                }
            }

            for(int x = realX; x < pixels.length; x++){
                for(int y = realY; y < pixels.length; y++){
                    pixels[x][y] = pixels[lastRealXCoord][lastRealYCoord];
                }
            }


            return new Picture(realX, realY, pixels);
        }


    }
}
