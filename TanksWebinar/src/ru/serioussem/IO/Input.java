package ru.serioussem.IO;

import javax.swing.*;
import java.util.Arrays;

public class Input extends JComponent {

    private boolean[] map;

    public Input(){
        map = new boolean[256];
    }

    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }

    public boolean getKey(int keyCode){
        return map[keyCode];
    }

}
