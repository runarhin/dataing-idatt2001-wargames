package edu.ntnu.idatt2001.runarin.frontend.controllers;

import edu.ntnu.idatt2001.runarin.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.frontend.WarGamesApp;
import edu.ntnu.idatt2001.runarin.frontend.model.WarGamesModel;
import edu.ntnu.idatt2001.runarin.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.backend.units.UnitFactory;
import edu.ntnu.idatt2001.runarin.backend.units.UnitType;
import edu.ntnu.idatt2001.runarin.frontend.model.ArmySelect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for the popup-view for adding new units to an army.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class AddUnitsController implements Initializable {

    @FXML private TextField infantryName;
    @FXML private TextField infantryHealth;
    @FXML private Spinner<Integer> infantryAmount;

    @FXML private TextField rangedName;
    @FXML private TextField rangedHealth;
    @FXML private Spinner<Integer> rangedAmount;

    @FXML private TextField cavalryName;
    @FXML private TextField cavalryHealth;
    @FXML private Spinner<Integer> cavalryAmount;

    @FXML private TextField commanderName;
    @FXML private TextField commanderHealth;
    @FXML private Spinner<Integer> commanderAmount;

    @FXML private BorderPane idBorderPane;

    /**
     * Initializes all relevant and helpful data for the user prior to opening the stage.
     *
     * @param url represents a Uniform Resource Locator
     * @param resourceBundle contains locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initBackground();

        if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
            initUnitData(WarGamesController.getArmyOneWrapper().getArmy().getName());
        }
        else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
            initUnitData(WarGamesController.getArmyTwoWrapper().getArmy().getName());
        }
    }

    /**
     * Button to add units to an army. Upon action, data is read from all data
     * fields and the chosen units are added to the army by a UnitFactory.
     *
     * @param actionEvent OnAction, button pressed.
     * @see UnitFactory
     */
    @FXML
    private void btnAddUnits(ActionEvent actionEvent) {

        try {
            if (infantryAmount.getValue() > 0) {
                ArrayList<Unit> infantryFactoryList =
                        UnitFactory.getUnitsByType(
                                infantryAmount.getValue(),
                                UnitType.INFANTRY,
                                infantryName.getText(),
                                Integer.parseInt(infantryHealth.getText()));
                if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
                    WarGamesController.getArmyOneWrapper().getArmy().addUnitsFromList(infantryFactoryList);
                }
                else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
                    WarGamesController.getArmyTwoWrapper().getArmy().addUnitsFromList(infantryFactoryList);
                }
            }

            if (rangedAmount.getValue() > 0) {
                ArrayList<Unit> rangedFactoryList =
                        UnitFactory.getUnitsByType(
                                rangedAmount.getValue(),
                                UnitType.RANGED,
                                rangedName.getText(),
                                Integer.parseInt(rangedHealth.getText()));
                if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
                    WarGamesController.getArmyOneWrapper().getArmy().addUnitsFromList(rangedFactoryList);
                }
                else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
                    WarGamesController.getArmyTwoWrapper().getArmy().addUnitsFromList(rangedFactoryList);
                }
            }

            if (cavalryAmount.getValue() > 0) {
                ArrayList<Unit> cavalryFactoryList =
                        UnitFactory.getUnitsByType(
                                cavalryAmount.getValue(),
                                UnitType.CAVALRY,
                                cavalryName.getText(),
                                Integer.parseInt(cavalryHealth.getText()));
                if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
                    WarGamesController.getArmyOneWrapper().getArmy().addUnitsFromList(cavalryFactoryList);
                }
                else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
                    WarGamesController.getArmyTwoWrapper().getArmy().addUnitsFromList(cavalryFactoryList);
                }
            }

            if (commanderAmount.getValue() > 0) {
                Unit commanderUnit =
                        UnitFactory.getUnit(
                                UnitType.COMMANDER,
                                commanderName.getText(),
                                Integer.parseInt(commanderHealth.getText()));
                if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
                    WarGamesController.getArmyOneWrapper().getArmy().addUnit(commanderUnit);
                }
                else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
                    WarGamesController.getArmyTwoWrapper().getArmy().addUnit(commanderUnit);
                }
            }

            if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_ONE) {
                WarGamesController.getArmyOneWrapper().updateViewContent();
            }
            else if (WarGamesModel.getArmySelect() == ArmySelect.ARMY_TWO) {
                WarGamesController.getArmyTwoWrapper().updateViewContent();
            }
        } catch (Exception e) {
            WarGamesApp.giveError(e.getMessage());
        }

        // Closes the window on "Add".
        ((Stage)(((Button)actionEvent.getSource()).getScene().getWindow())).close();
    }

    /**
     * Method which initializes the background of the
     * "Add units"-stage to follow the previously set terrain.
     */
    private void initBackground() {
        if (WarGamesModel.getTerrain() == TerrainType.FOREST) {
            idBorderPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/css/forest.css")).toExternalForm());
        }
        else if (WarGamesModel.getTerrain() == TerrainType.HILL) {
            idBorderPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/css/hill.css")).toExternalForm());
        }
        else if (WarGamesModel.getTerrain() == TerrainType.PLAINS) {
            idBorderPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource("/css/plains.css")).toExternalForm());
        }
    }

    /**
     * Method that sets initial data upon opening the stage, depending on what army is set.
     * The initial data is chosen by what is expected to be used when adding units.
     */
    private void initUnitData(String armyName) {
        infantryHealth.setText("100");
        infantryAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999));

        rangedHealth.setText("100");
        rangedAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999));

        cavalryHealth.setText("100");
        cavalryAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 999));

        commanderHealth.setText("180");
        commanderAmount.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1));

        if (armyName.contains("Horde")) {
            infantryName.setText("Grunt");
            rangedName.setText("Spearman");
            cavalryName.setText("Raider");
            commanderName.setPromptText("Gul'dan?");
        }
        else if (armyName.contains("Human")) {
            infantryName.setText("Footman");
            rangedName.setText("Archer");
            cavalryName.setText("Knight");
            commanderName.setPromptText("Mountain King?");
        }
    }
}
