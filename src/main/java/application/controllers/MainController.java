package application.controllers;

import application.Ant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

import static application.utility.ProgramProperty.*;

public class MainController extends Application {

    static WritableImage image;

    static ResourceBundle bundle;

    static Ant ant = Ant.builder().positionX(200).positionY(200).size(2).direction(1).build();

    @Override
    public void start(Stage stage) throws Exception {

        bundle = ResourceBundle.getBundle("/bundles/Bundle_en_EN", new Locale("en", "EN"));

        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"), bundle);
        stage.setTitle(STAGE_TITLE);
        stage.setScene(new Scene(root, WIDTH_WINDOW, HEIGHT_WINDOW));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}