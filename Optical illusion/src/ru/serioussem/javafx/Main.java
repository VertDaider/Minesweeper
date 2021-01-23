package ru.serioussem.javafx;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    private static final int width = 1000;
    private static final int height = 900;
    private static final int bigRadius = 400;
    private static final int smallRadius = 50;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        VBox root = new VBox(15.0);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20.0));
        Button button = new Button("Запустить анимацию");
        button.setOnAction(event -> {
            newWindow();
        });

        root.getChildren().addAll(button);
        Scene scene = new Scene(root, 400.0, 300.0);
        stage.setTitle("Optical illusion");
        stage.setScene(scene);
        stage.show();
    }

    private Line rotateLine(Line line, int angleDegree, double x, double y) {
        Line newLine = new Line();
        double angle = Math.toRadians(angleDegree);

        newLine.setStartX(((line.getStartX() - x) * Math.cos(angle)) - ((line.getStartY() - y) * Math.sin(angle)) + x);
        newLine.setStartY(((line.getStartX() - x) * Math.sin(angle)) + ((line.getStartY() - y) * Math.cos(angle)) + y);
        newLine.setEndX(((line.getEndX() - x) * Math.cos(angle)) - ((line.getEndY() - y) * Math.sin(angle)) + x);
        newLine.setEndY(((line.getEndX() - x) * Math.sin(angle)) + ((line.getEndY() - y) * Math.cos(angle)) + y);

        return newLine;
    }

    public void newWindow() {
        Stage window = new Stage();
        Pane pane = new Pane();

        Circle bigCircle = new Circle((double) width / 2, (double) height / 2, bigRadius, Color.RED);
        bigCircle.setSmooth(true);

        Line line = new Line();
        line.setStartX(bigCircle.getCenterX() - bigRadius + smallRadius);
        line.setStartY(bigCircle.getCenterY());
        line.setEndX(bigCircle.getCenterX() + bigRadius - smallRadius);
        line.setEndY(bigCircle.getCenterY());
        line.setStroke(Color.WHITE);
        System.out.println(line.getStartX() + " " + line.getStartY());
        System.out.println(line.getEndX() + " " + line.getEndY());

        Circle circle1 = new Circle(smallRadius, Color.WHITE);
        circle1.setSmooth(true);

        Path p1 = new Path(
                new MoveTo(line.getStartX(), line.getStartY()),
                new LineTo(line.getEndX(), line.getEndY())
        );

        PathTransition tr = new PathTransition(
                Duration.seconds(5.0), p1, circle1);
        tr.setAutoReverse(true);
        tr.setCycleCount(Animation.INDEFINITE);
        tr.setInterpolator(Interpolator.LINEAR);
        tr.play();

        Line line2 = rotateLine(line,90, bigCircle.getCenterX(), bigCircle.getCenterY());
        Line line3 = rotateLine(line,45, bigCircle.getCenterX(), bigCircle.getCenterY());

        Circle circle2 = new Circle(smallRadius, Color.WHITE);
        circle2.setSmooth(true);

        Path p2 = new Path(
                new MoveTo(line2.getStartX(), line2.getStartY()),
                new LineTo(line2.getEndX(), line2.getEndY())
        );

        PathTransition tr2 = new PathTransition(
                Duration.seconds(5.0), p2, circle2);
        tr2.setAutoReverse(true);
        tr2.setCycleCount(Animation.INDEFINITE);
        tr2.setInterpolator(Interpolator.LINEAR);
        tr2.play();

        pane.getChildren().addAll(bigCircle, circle1, circle2);
        Scene scene = new Scene(pane, width, height, Color.BLACK);
        pane.setBackground(Background.EMPTY);
        window.setScene(scene);
        window.setTitle("Illusion");
        window.show();
    }
}

