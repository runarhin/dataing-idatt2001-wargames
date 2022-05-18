package edu.ntnu.idatt2001.runarin.wargames.frontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Application-class which launches the war games' main window/application.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class WarGamesApp extends Application {

    private static final String VERSION = "4.0";
    private static Alert alert;

    /**
     * Method loads the application window.
     *
     * @param primaryStage to show the view.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(
                    WarGamesApp.class.getClassLoader().getResource("view_war_games.fxml")));

            primaryStage.setTitle("War Games Simulator   v." + VERSION);
            Image icon = new Image("icon_2.png");
            primaryStage.getIcons().add(icon);

            primaryStage.setScene(new Scene((root)));
            primaryStage.show();

        } catch (Exception e) {
            giveError("Initializing application, error message:  " + e.getMessage());
        }
    }

    /**
     * Initialising the application with main method.
     *
     * @param args command line arguments provided during startup. Not used in this app.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method that loads a new stage for adding units to an army.
     *
     * @param armyName the army name to be added in the window/stage title.
     */
    public static void openAddUnitsStage(String armyName) {
        try {
            FXMLLoader addUnitsView = new FXMLLoader(
                    WarGamesApp.class.getClassLoader().getResource("view_add_units.fxml"));
            Scene scene = new Scene(addUnitsView.load());

            Stage stage = new Stage();
            stage.setTitle("Add units to " + armyName);
            stage.initModality(Modality.APPLICATION_MODAL);
            Image icon = new Image("icon_2.png");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            giveError("Add units-button, error message:  " + e.getMessage());
            e.printStackTrace();
        }
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
    public static void giveError(String message) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
