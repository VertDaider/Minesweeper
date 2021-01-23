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
    private static final int smallRadius = 25;
    private static final Duration duration = Duration.seconds(5.0);

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

    private Line rotateLine(Line line, double angleDegree, double x, double y) {
        Line newLine = new Line();
        double angle = Math.toRadians(angleDegree);

        newLine.setStartX(((line.getStartX() - x) * Math.cos(angle)) - ((line.getStartY() - y) * Math.sin(angle)) + x);
        newLine.setStartY(((line.getStartX() - x) * Math.sin(angle)) + ((line.getStartY() - y) * Math.cos(angle)) + y);
        newLine.setEndX(((line.getEndX() - x) * Math.cos(angle)) - ((line.getEndY() - y) * Math.sin(angle)) + x);
        newLine.setEndY(((line.getEndX() - x) * Math.sin(angle)) + ((line.getEndY() - y) * Math.cos(angle)) + y);

        return newLine;
    }

    private Circle createAnimation(Line line, double angle) {
        Circle circle = new Circle(smallRadius, Color.WHITE);
        circle.setSmooth(true);

        Line newLine = line;
        if (angle != 0) {
            newLine = rotateLine(line, angle, (double) width / 2, (double) height / 2);
        }
        Path p1 = new Path(
                new MoveTo(newLine.getStartX(), newLine.getStartY()),
                new LineTo(newLine.getEndX(), newLine.getEndY())
        );

        PathTransition tr = new PathTransition(
                duration, p1, circle);
        tr.setAutoReverse(true);
        tr.setCycleCount(Animation.INDEFINITE);
//        tr.setInterpolator(Interpolator.SPLINE(0.5, 0.5, 1.0, 1.0));
        tr.setInterpolator(Interpolator.EASE_BOTH);
        tr.playFrom(duration.multiply(angle).divide(180));

        return circle;
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

        Circle circle1 = createAnimation(line, 0);
        Circle circle2 = createAnimation(line, 22.5);
        Circle circle3 = createAnimation(line, 45);
        Circle circle4 = createAnimation(line, 67.5);
        Circle circle5 = createAnimation(line, 90);
        Circle circle6 = createAnimation(line, 112.5);
        Circle circle7 = createAnimation(line, 135);
        Circle circle8 = createAnimation(line, 157.5);

        pane.getChildren().addAll(bigCircle, circle1, circle2, circle3, circle4, circle5, circle6, circle7, circle8);
        Scene scene = new Scene(pane, width, height, Color.BLACK);
        pane.setBackground(Background.EMPTY);
        window.setScene(scene);
        window.setTitle("Illusion");
        window.show();
    }
}

