package ui;

import model.Coord;
import model.Figure;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame implements Runnable {
    private Box[][] boxes;
    private Figure figure;
    private Coord coord;

    public Window() {
        boxes = new Box[Config.WIDTH][Config.HEIGHT];
        initForm();
        initBoxes();
        addKeyListener(new KeyAdapter());

    }

    public void addFigure() {
        figure = Figure.getRandom();
        coord = new Coord(5, 5);
        showFigure();
    }

    private void initForm() {
        setSize(Config.WIDTH * Config.SIZE, Config.HEIGHT * Config.SIZE + 30);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tetris the Game");
//        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    private void initBoxes() {
        for (int x = 0; x < Config.WIDTH; x++) {
            for (int y = 0; y < Config.HEIGHT; y++) {
                boxes[x][y] = new Box(x, y);
                add(boxes[x][y]);
            }
        }
    }

    @Override
    public void run() {
        repaint();
    }

    private void showFigure() {
        showFigure(figure, coord, 1);
    }

    private void hideFigure() {
        showFigure(figure, coord, 0);
    }

    private void showFigure(Figure figure, Coord at, int color) {
        for (Coord dot : figure.dots) {
            setBoxColor(at.x + dot.x, at.y + dot.y, color);
        }
    }

    private void setBoxColor(int x, int y, int color) {
        if (x < 0 || x >= Config.WIDTH) return;
        if (y < 0 || y >= Config.HEIGHT) return;
        boxes[x][y].setColor(color);
    }

    private boolean canMoveFigure(int sx, int sy) {
        if(coord.x + sx + figure.top.x < 0) return false;
        if (coord.x + sx + figure.bot.x >= Config.WIDTH) return false;
        if (coord.y + sy + figure.top.y < 0) return false;
        if (coord.y + sy + figure.bot.y >= Config.HEIGHT) return false;
        return true;
    }

    private void moveFigure(int sx, int sy) {
        if (canMoveFigure(sx, sy)) {
            coord = coord.plus(sx, sy);
        }
    }

    class KeyAdapter implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            hideFigure();
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                    moveFigure(0, -1);
                    break;
                case KeyEvent.VK_LEFT:
                    moveFigure(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    moveFigure(+1, 0);
                    break;
                case KeyEvent.VK_DOWN:
                    moveFigure(0, +1);
                    break;
            }
            showFigure();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }
}
