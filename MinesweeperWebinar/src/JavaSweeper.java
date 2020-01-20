import sweeper.Box;
import sweeper.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    private WinDialog winDialog;
    private CustomDialog customDialog;
    private int COLS = 9;
    private int ROWS = 9;
    private int BOMBS = 10;
    private final int IMAGE_SIZE = 35;

    public static void main(String[] args) {
        new JavaSweeper();
    }

    private JavaSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private JMenu gameMenu() {
        JMenu menu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New game");
        menu.add(newGame);
        menu.addSeparator();
        newGame.addActionListener(e -> {
            game.start();
            panel.repaint();
        });
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));

        JMenuItem beginner = new JMenuItem("Beginner");
        menu.add(beginner);
        JMenuItem intermediate = new JMenuItem("Intermediate");
        menu.add(intermediate);
        JMenuItem expert = new JMenuItem("Expert");
        menu.add(expert);
        JMenuItem custom = new JMenuItem("Custom");
        menu.add(custom);
        beginner.addActionListener(e -> setParamGame(9, 9, 10, "Beginner"));
        intermediate.addActionListener(e -> setParamGame(16, 16, 40, "Intermediate"));
        expert.addActionListener(e -> setParamGame(30, 16, 99, "Expert"));
        custom.addActionListener(e -> callCustomSetDialog());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(event -> System.exit(0));
        menu.addSeparator();
        menu.add(exit);
        exit.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_DOWN_MASK));

        // TODO: 04.01.2020 add user settings
        return menu;
    }

    private void setParamGame(int cols, int rows, int bomb, String newLabel) {
        COLS = cols;
        ROWS = rows;
        BOMBS = bomb;
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        initPanel();
        initFrame();
        label.setText(newLabel);
    }

    private void initLabel() {
        label = new JLabel("Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {  // don't paintComponents!
                super.paintComponents(g);
                for (Coord coord : Ranges.getAllCoords())
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
            }
        };

        panel.addMouseListener(new MyMouseAdapter());

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void callCustomSetDialog() {
        if(customDialog ==null){
            customDialog = new CustomDialog(JavaSweeper.this);
        }
        customDialog.setVisible(true);
    }

    private void callDialogWinner() {
        if (game.getState() == GameState.WINNER) {
            if (winDialog == null) {
                winDialog = new WinDialog(JavaSweeper.this);
            }
            winDialog.setVisible(true);
        }
    }
    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(gameMenu());
        setJMenuBar(menuBar);
        setResizable(false);
        setVisible(true);
        pack();
//        setLocationRelativeTo(null);
        setIconImage(getImage("icon"));
    }

    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name) {
        String filename = "img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX() / IMAGE_SIZE;
            int y = e.getY() / IMAGE_SIZE;
            Coord coord = new Coord(x, y);

            if (e.getButton() == MouseEvent.BUTTON1) {
                if (game.isFirstBombBox(coord)) {
                    game.start();
                    panel.repaint();
                } else
                    game.pressLeftButton(coord);
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                game.pressRightButton(coord);
            }
            if (e.getButton() == MouseEvent.BUTTON2) {
                game.start();
            }
            label.setText(getBombLeftMessage());
            panel.repaint();
            callDialogWinner();
        }
    }

    private String getBombLeftMessage() {
        return String.format("Bombs left : %d", game.getBombLeft());
    }
}