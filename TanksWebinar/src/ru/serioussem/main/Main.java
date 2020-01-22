package ru.serioussem.main;

import ru.serioussem.display.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        Display.create(800, 600, "Tanks", 0xFF00FF00);

        Timer timer = new Timer(1000/60, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Display.clear();
                Display.render();
                Display.swapBuffers();
            }
        });

        timer.setRepeats(true);
        timer.start();
    }
}