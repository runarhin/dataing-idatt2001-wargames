package edu.ntnu.idatt2001.runarin.wargames.frontend.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MVC View-class which launches the war games' main window/application.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-22
 */
public class WarGamesApp extends Application {

    private static final String VERSION = "3.0";
    private static Alert alert;

    /**
     * Method loads the application window.
     *
     * @param stage to show the view.
     * @throws Exception thrown from FXMLLoader.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(
                WarGamesApp.class.getClassLoader().getResource("war_games_view.fxml"));
        Scene scene = new Scene(root.load());

        stage.setTitle("War Games   v." + VERSION);

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Give information.
     * Static method that can be called by the controller.
     *
     * @param message the message
     */
    public static void giveInformation(String message) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Give error.
     * Static method that can be called by the controller.
     *
     * @param message the message
     */
    public static void giveError(String message) { //TODO: Is this ever used?
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Initialising the application with main method.
     *
     * @param args command line arguments provided during startup. Not used in this app.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
