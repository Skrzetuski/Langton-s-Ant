package application.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static application.controllers.MainController.*;
import static application.utility.CanvasProperty.*;


public class DrawingAntOnCanvasController implements Initializable {

    @FXML
    Canvas canvas;

    @FXML
    Button change;

    private GraphicsContext gc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        canvas.setHeight(HEIGHT);
        canvas.setWidth(WIDTH);

        gc = canvas.getGraphicsContext2D();

        if (MainController.image == null) {
            gc.setFill(Color.WHITE);
            gc.fillRect(START_PIXEL_X, START_PIXEL_Y, HEIGHT, WIDTH);
        } else {
            gc.drawImage(MainController.image, START_PIXEL_X, START_PIXEL_Y);
        }
    }

    public void switchLang() throws IOException {

        stop();
        MainController.image = canvas.snapshot(null, null);

        if (change.getText().equals("pl"))
            bundle = ResourceBundle.getBundle("/bundles/Bundle_pl_PL", new Locale("pl", "PL"));
        else
            bundle = ResourceBundle.getBundle("/bundles/Bundle_en_EN", new Locale("en", "EN"));

        canvas.getScene().setRoot(FXMLLoader.load(getClass().getResource("/view/main.fxml"), bundle));
    }

    public void draw() {
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    private final Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.001), actionEvent -> {

                WritableImage snap = canvas.getGraphicsContext2D().getCanvas().snapshot(null, null);

                ant.checkPosition();

                if (snap.getPixelReader().getColor(ant.getPositionX(), ant.getPositionY()).equals(Color.WHITE)) {

                    gc.setFill(Color.BLACK);
                    gc.fillRect(ant.getPositionX(), ant.getPositionY(), ant.getSize(), ant.getSize());
                    ant.turnLeft();

                } else {

                    gc.setFill(Color.WHITE);
                    gc.fillRect(ant.getPositionX(), ant.getPositionY(), ant.getSize(), ant.getSize());
                    ant.turnRight();

                }
            })
    );
}
