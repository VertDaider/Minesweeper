package ru.serioussem.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public abstract class Display {
    private static int width = 1200;
    private static int height = 800;

    private static boolean created = false;
    private static Canvas content;

    private static BufferedImage buffer;
    private static int[] bufferData;
    private static Graphics bufferGraphics;
    private static int clearColor;


    public static void create(String title, int _clearColor) {
        if (created)
            return;

        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        content = new Canvas();

        Dimension size = new Dimension(width, height);
        content.setPreferredSize(size);

        window.setResizable(false);
        window.getContentPane().add(content);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        bufferData = ((DataBufferInt) buffer.getRaster().getDataBuffer()).getData();
        bufferGraphics = buffer.getGraphics();
        clearColor = _clearColor;

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }


    private static float delta = 0.75f;            //white
    private static float delta7 = 0.05f;       //orange
    private static float delta5 = 0.25f;        //green
    private static float delta6 = 0.325f;       //magenta
    private static float delta1 = 0.5f;         //blue
    private static float delta2 = 0.625f;       //yellow
    private static float delta4 = 0.75f;        //gray
    private static float delta3 = 1;            //cyan

    private static int getStartX(int radius) {
        return (width - radius) / 2;
    }

    private static int getStartY(int radius) {
        return (height - radius) / 2;
    }

    public static void render() {
        paintBigOval();
        paintSmallOvals();
    }

    private static void paintSmallOvals() {
        bufferGraphics.setColor(Color.WHITE);
        int radiusSmall = 50;
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta) * (300 - radiusSmall / 2))),
                getStartY(radiusSmall), radiusSmall, radiusSmall);
        delta += 0.02f;
        bufferGraphics.setColor(Color.ORANGE);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta7) * 246)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta7) * 246) / -2), radiusSmall, radiusSmall);
        delta7 += 0.02f;
        bufferGraphics.setColor(Color.GREEN);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (- Math.sin(delta5) * 195)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta5) * 195)), radiusSmall, radiusSmall);
        delta5 += 0.02f;
        bufferGraphics.setColor(Color.MAGENTA);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta6) * 246) / -2),
                (int) (getStartY(radiusSmall) + (Math.sin(delta6) * 246)), radiusSmall, radiusSmall);
        delta6 += 0.02f;
        bufferGraphics.setColor(Color.BLUE);
        bufferGraphics.fillOval(getStartX(radiusSmall),
                (int) (getStartY(radiusSmall) + (Math.sin(delta1) * (300 - radiusSmall / 2))), radiusSmall, radiusSmall);
        delta1 += 0.02f;
        bufferGraphics.setColor(Color.YELLOW);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta2) * 246) / 2),
                (int) (getStartY(radiusSmall) + (Math.sin(delta2) * 246)), radiusSmall, radiusSmall);
        delta2 += 0.02f;
        bufferGraphics.setColor(Color.GRAY);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta4) * 195)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta4) * 195)), radiusSmall, radiusSmall);
        delta4 += 0.02f;
        bufferGraphics.setColor(Color.CYAN);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta3) * 246)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta3) * 246) / 2), radiusSmall, radiusSmall);
        delta3 += 0.02f;
    }

    public static void paintBigOval() {
        bufferGraphics.setColor(Color.RED);
        int radiusBig = 600;
        bufferGraphics.fillOval(getStartX(radiusBig), getStartY(radiusBig), radiusBig, radiusBig);
    }


    public static void swapBuffers() {
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}