import models.Picture;
import models.RGBPixel;


public class PpmReader {


    public static Picture<RGBPixel> convertPPMStringToPicture(String ppm) {
        // Split the input string into lines
        String[] lines = ppm.split("\\n");


        int width = lines[0].split(" ").length / 3;
        int height = lines.length;

        Picture.PictureBuilder<RGBPixel> builder = new Picture.PictureBuilder<>(width, height, RGBPixel.class);

        RGBPixel[][] pixels =  new RGBPixel[width][height];

        // Iterate over the rest of the lines to get pixel data
        int pixelDataStartIndex = 3; // Adjust if more header lines
        for (int y = 0; y < height; y++) {
            String[] rgbValues = lines[pixelDataStartIndex + y].split(" ");
            for (int x = 0; x < width; x++) {
                // Extract RGB values from the string
                double r = Double.parseDouble(rgbValues[x * 3]);
                double g = Double.parseDouble(rgbValues[x * 3 + 1]);
                double b = Double.parseDouble(rgbValues[x * 3 + 2]);

                // Create a new RGBPixel and set it in the Picture object
                pixels[x][y] = new RGBPixel(r, g, b);
            }
        }

        return builder.withPixels(pixels).build();
    }

}
