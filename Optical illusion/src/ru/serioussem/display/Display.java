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
        ((Graphics2D) bufferGraphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        clearColor = _clearColor;

        created = true;
    }

    public static void clear() {
        Arrays.fill(bufferData, clearColor);
    }


    private static float delta8 = 1.3f;            //white
    private static float delta7 = 1.75f;       //orange
    private static float delta5 = -1.0f;        //green
    private static float delta6 = -0.5f;       //magenta
    private static float delta1 = 0.0f;         //blue
    private static float delta2 = 0.3f;       //yellow
    private static float delta4 = 0.6f;        //gray
    private static float delta3 = 1f;            //cyan
    private static Color color = Color.WHITE;

    private static int getStartX(int radius) {
        return (width - radius) / 2;
    }

    private static int getStartY(int radius) {
        return (height - radius) / 2;
    }

    public static void update() {
        delta8 += 0.02f;
        delta7 += 0.02f;
        delta5 += 0.02f;
        delta1 += 0.02f;
        delta6 += 0.02f;
        delta2 += 0.02f;
        delta4 += 0.02f;
        delta3 += 0.02f;
    }

    public static void render() {
        paintBigOval();
        paintSmallOvals();
        update();
    }

    private static void paintSmallOvals() {
        int radiusSmall = 50;
        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta8) * (300 - radiusSmall / 2))),
                getStartY(radiusSmall), radiusSmall, radiusSmall);

        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta7) * 246)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta7) * 246) / -2), radiusSmall, radiusSmall);

        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (- Math.sin(delta5) * 195)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta5) * 195)), radiusSmall, radiusSmall);


        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta6) * 246) / -2),
                (int) (getStartY(radiusSmall) + (Math.sin(delta6) * 246)), radiusSmall, radiusSmall);


        bufferGraphics.setColor(color);
        bufferGraphics.fillOval(getStartX(radiusSmall),
                (int) (getStartY(radiusSmall) + (Math.sin(delta1) * (300 - radiusSmall / 2))), radiusSmall, radiusSmall);


        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta2) * 246) / 2),
                (int) (getStartY(radiusSmall) + (Math.sin(delta2) * 246)), radiusSmall, radiusSmall);


        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta4) * 195)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta4) * 195)), radiusSmall, radiusSmall);


        bufferGraphics.setColor(color);
        bufferGraphics.fillOval((int) (getStartX(radiusSmall) + (Math.sin(delta3) * 246)),
                (int) (getStartY(radiusSmall) + (Math.sin(delta3) * 246) / 2), radiusSmall, radiusSmall);

    }

    public static void paintBigOval() {
        bufferGraphics.setColor(Color.RED);
        int radiusBig = 600;
        bufferGraphics.fillOval(getStartX(radiusBig), getStartY(radiusBig), radiusBig, radiusBig);
    }

    public static float getDelta(float delta) {
        return 0;
    }


    public static void swapBuffers() {
        Graphics g = content.getGraphics();
        g.drawImage(buffer, 0, 0, null);
    }
}