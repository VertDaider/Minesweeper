package ru.serioussem.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {
    public static final String PATH = "TanksWebinar/res/";

    public static BufferedImage loadImage(String name){
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(PATH + name.toLowerCase()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
