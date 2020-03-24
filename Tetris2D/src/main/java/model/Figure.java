package model;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    I1(0, 1,     1, 1,   2, 1,     3, 1),  // oooo

    I2(1, 1,
                1, 1,
                1, 2,
                1, 3),

    J1(  1, 0,
                  1, 1,
            0, 2, 1, 2),

    J2(0, 0,
                0, 1,   1, 1,   2, 1),

    J3(1,0,     2, 0,
                1, 1,
                1, 2),

    J4(0,1,     1,1,    2,1,
                                 2,2),

    L1(1, 0,
                1, 1,
                1, 2,   2, 2),

    L2(0, 1,    1, 1,   2, 1,
                0, 2),

    L3(0, 0,    1, 0,
                         1, 1,
                         1, 2),

    L4(             2, 0,
            0, 1,   1, 1,    2, 1),

    O (0, 0,    0, 1,
                1, 0,   1, 1),

    S1(1, 1,    2, 1,
        0, 2,  1, 2),

    S2(0, 0,
                0, 1,   1, 1,
                        1, 2),

    T1(0, 1,    1, 1,   2, 1,
                         1, 2),

    T2(     1, 0,
            0, 1,    1, 1,
                     1, 2),

    T3(     1, 0,
            0, 1,    1, 1,   2, 1),

    T4(     1, 0,
                    0, 1,    1, 1,
                    1, 2),

    Z1 (0, 1,   1, 1,
                         1, 2,      2, 2),

    Z2 (    2, 0,
            1, 1,    2, 1,
            1, 2);

    public final List<Coord> dots;
    public final Coord top;
    public final Coord bot;

    Figure(int... coords) {
        dots = new ArrayList<Coord>();
        for (int j = 0; j < coords.length; j += 2) {
            dots.add(new Coord(coords[j], coords[j + 1]));
        }
        top = setTop();
        bot = setBot();
    }

    private Coord setTop() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coord coord : dots) {
            if (x > coord.x) x = coord.x;
            if (y > coord.y) y = coord.y;
        }
        return new Coord(x,y);
    }

    private Coord setBot() {
        int x = dots.get(0).x;
        int y = dots.get(0).y;
        for (Coord coord : dots) {
            if (x < coord.x) x = coord.x;
            if (y < coord.y) y = coord.y;
        }
        return new Coord(x,y);
    }

    public Figure turnLeft(){
        if (this == O) {
            return O;
        } else {
            return turnRight().turnRight().turnRight();
        }
    }

    public Figure turnRight(){
        switch (this) {
            case I1: return I2;
            case I2: return I1;
            case J1: return J2;
            case J2: return J3;
            case J3: return J4;
            case J4: return J1;
            case L1: return L2;
            case L2: return L3;
            case L3: return L4;
            case L4: return L1;
            case O: return O;
            case S1: return S2;
            case S2: return S1;
            case T1: return T2;
            case T2: return T3;
            case T3: return T4;
            case T4: return T1;
            case Z1: return Z2;
            case Z2:
            default: return Z1;
        }
    }

    public static Figure getRandom() {
        switch ((int) (Math.random() * 19)) {
            case 1: return I1;
            case 2: return I2;
            case 3: return J1;
            case 4: return J2;
            case 5: return J3;
            case 6: return J4;
            case 7: return L1;
            case 8: return L2;
            case 9: return L3;
            case 10: return L4;
            case 11: return O;
            case 12: return S1;
            case 13: return S2;
            case 14: return T1;
            case 15: return T2;
            case 16: return T3;
            case 17: return T4;
            case 18: return Z1;
            case 19:
            default:return Z2;
        }
    }
}
