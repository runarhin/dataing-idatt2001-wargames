package edu.ntnu.idatt2001.runarin.frontend.controllers;

import static edu.ntnu.idatt2001.runarin.frontend.WarGamesApp.*;

import edu.ntnu.idatt2001.runarin.backend.exceptions.ArmyEmptyOfUnitsException;
import edu.ntnu.idatt2001.runarin.frontend.model.WarGamesModel;
import edu.ntnu.idatt2001.runarin.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.backend.armies.units.TerrainType;
import edu.ntnu.idatt2001.runarin.backend.armies.units.Unit;

import edu.ntnu.idatt2001.runarin.frontend.model.ArmySelect;
import edu.ntnu.idatt2001.runarin.frontend.model.ArmyWrapper;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * MVC Controller class for communication between the application view and the model holding data.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class WarGamesController implements Initializable {

    @FXML private Text idTextUnitsListArmyOne;
    @FXML private Text idTextArmyOneFilePath;
    @FXML private Text idTextInitArmies;
    @FXML private Text idTextStartSim;
    @FXML private Text idTextChooseTerrain;
    @FXML private Text idTextBattleLog;
    @FXML private Text idTextUnitsListArmyTwo;
    @FXML private Text idTextArmyTwoFilePath;

    @FXML private GridPane idGridPane;
    @FXML private Button idBtnStartBattle;
    @FXML private Button idBtnInitializeArmies;
    @FXML private Button idBtnForest;
    @FXML private Button idBtnHill;
    @FXML private Button idBtnPlains;
    @FXML private Button idBtnArmyOneSelectFile;
    @FXML private Button idBtnArmyTwoSelectFile;
    @FXML private TextArea textAreaBattleLog;

    @FXML private Text nameArmyOne;
    @FXML private Label armyOneNumberOfUnits;
    @FXML private Label armyOneNumberOfInfantry;
    @FXML private Label armyOneNumberOfRanged;
    @FXML private Label armyOneNumberOfCavalry;
    @FXML private Label armyOneNumberOfCommanders;
    @FXML private ListView<Unit> listViewArmyOneUnits;
    @FXML private Button idBtnAddUnitsArmyOne;
    @FXML private TextField filePathArmyOne;
    @FXML private Button idBtnAddUnitsFromFileArmyOne;

    @FXML private Text nameArmyTwo;
    @FXML private Label armyTwoNumberOfUnits;
    @FXML private Label armyTwoNumberOfRanged;
    @FXML private Label armyTwoNumberOfInfantry;
    @FXML private Label armyTwoNumberOfCavalry;
    @FXML private Label armyTwoNumberOfCommanders;
    @FXML private ListView<Unit> listViewArmyTwoUnits;
    @FXML private Button idBtnAddUnitsArmyTwo;
    @FXML private TextField filePathArmyTwo;
    @FXML private Button idBtnAddUnitsFromFileArmyTwo;

    private static final ArmyWrapper armyOneWrapper = new ArmyWrapper(new Army("Initial Army One"));
    private static final ArmyWrapper armyTwoWrapper = new ArmyWrapper(new Army("Initial Army Two"));

    /**
     * Initializes all relevant and helpful data for the user prior to opening the stage.
     *
     * @param url represents a Uniform Resource Locator
     * @param resourceBundle contains locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initViewData();
        initTooltips();
        initBindings();
        initListeners();

        // This method makes the program run slow, but gives the application better text visibility.
        initTextStrokes();
    }

    /**
     * Return the wrapper for army one.
     * Used for adding units from another window.
     * @see AddUnitsController
     *
     * @return static armyOneWrapper.
     */
    public static ArmyWrapper getArmyOneWrapper() {
        return armyOneWrapper;
    }

    /**
     * Return the wrapper for army two.
     * Used for adding units from another window.
     * @see AddUnitsController
     *
     * @return static army two wrapper.
     */
    public static ArmyWrapper getArmyTwoWrapper() {
        return armyTwoWrapper;
    }

    /**
     * Button that starts the simulation and updates the results in the view.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnStartBattle(ActionEvent actionEvent) {

        buttonStylesUpdate();

        StringBuilder battleLog = null;
        try {
            WarGamesModel.setBattle(new Battle(armyOneWrapper.getArmy(), armyTwoWrapper.getArmy()));
            WarGamesModel.getBattle().simulate(WarGamesModel.getTerrain());

            // Reads battle log from file, post to simulation.
            battleLog = FileHandler.readStringBuilderFromFile();

            armyOneWrapper.updateViewContent();
            armyTwoWrapper.updateViewContent();

        } catch (ArmyEmptyOfUnitsException e) {
            buttonStylesUpdate();
            giveInformation(e.getMessage());

        } catch (IllegalArgumentException | IOException | NullPointerException e) {
            giveError(e.getMessage());
        }

        textAreaBattleLog.clear();
        if (battleLog != null) {
            textAreaBattleLog.appendText(battleLog.toString());
            textAreaBattleLog.setScrollTop(Double.MAX_VALUE);
        }
        else {
            textAreaBattleLog.appendText("Battle log not available.");
        }
    }

    /**
     * Button that initializes both armies with units and enables relevant buttons.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnInitializeArmies(ActionEvent actionEvent) {
        idBtnInitializeArmies.setStyle(null);
        idBtnInitializeArmies.setText("Reinitialize armies");

        idBtnStartBattle.setDisable(false);
        idBtnAddUnitsArmyOne.setDisable(false);
        idBtnAddUnitsArmyTwo.setDisable(false);
        idBtnAddUnitsFromFileArmyOne.setDisable(false);
        idBtnAddUnitsFromFileArmyTwo.setDisable(false);

        textAreaBattleLog.clear();

        initArmy(WarGamesModel.getFilePathArmyOne(), nameArmyOne, armyOneWrapper);
        initArmy(WarGamesModel.getFilePathArmyTwo(), nameArmyTwo, armyTwoWrapper);

        buttonStylesUpdate();
    }

    /**
     * Method that initializes an army from a file.
     *
     * @param armyFilePath file path to the army, using the data of army name and its units.
     * @param textFieldArmyName the text to be updated in the view.
     * @param armyWrapper the wrapper containing the army to be initialized.
     */
    private void initArmy(String armyFilePath, Text textFieldArmyName, ArmyWrapper armyWrapper) {
        try {
            String newArmyName = FileHandler.readArmyNameFromFile(armyFilePath);
            textFieldArmyName.setText(newArmyName);

            Army newArmy = new Army(newArmyName);
            newArmy.addUnitsFromFile(armyFilePath);
            armyWrapper.setArmy(newArmy);

        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            giveError(e.getMessage());
        }
    }

    /**
     * Button that selects the file path for army one.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnArmyOneSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select army from file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            WarGamesModel.setFilePathArmyOne(file);
            filePathArmyOne.setText(WarGamesModel.getFilePathArmyOne());
        }
    }

    /**
     * Button that selects the file path for army two.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnArmyTwoSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select army from file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            WarGamesModel.setFilePathArmyTwo(file);
            filePathArmyTwo.setText(WarGamesModel.getFilePathArmyTwo());
        }
    }

    /**
     * Button that initializes army one with units according to selected army file.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnAddUnitsFromFileArmyOne(ActionEvent actionEvent) {
        WarGamesModel.setFilePathArmyOne(new File(filePathArmyOne.getText()));
        try {
            armyOneWrapper.getArmy().addUnitsFromFile(WarGamesModel.getFilePathArmyOne());
            armyOneWrapper.updateViewContent();
        } catch (IOException e) {
            giveError("Adding units from file to army one, error: \n" + e.getMessage());
        }
        buttonStylesUpdate();
    }

    /**
     * Button that initializes army two with units according to selected army file.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnAddUnitsFromFileArmyTwo(ActionEvent actionEvent) {
        WarGamesModel.setFilePathArmyTwo(new File(filePathArmyTwo.getText()));
        try {
            armyTwoWrapper.getArmy().addUnitsFromFile(WarGamesModel.getFilePathArmyTwo());
            armyTwoWrapper.updateViewContent();
        } catch (IOException e) {
            giveError("Adding units from file to army two, error: \n" + e.getMessage());
        }
        buttonStylesUpdate();
    }

    /**
     * Button opens a new stage where a desired number of units can be added to army one.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnAddUnitsArmyOne(ActionEvent actionEvent) {
        WarGamesModel.setArmySelect(ArmySelect.ARMY_ONE);
        openAddUnitsStage(getArmyOneWrapper().getArmy().getName());
    }

    /**
     * Button opens a new stage where a desired number of units can be added to army two.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnAddUnitsArmyTwo(ActionEvent actionEvent) {
        WarGamesModel.setArmySelect(ArmySelect.ARMY_TWO);
        openAddUnitsStage(getArmyTwoWrapper().getArmy().getName());
    }

    /**
     * Button that changes terrain to FORREST terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnForest(ActionEvent actionEvent) {
        idBtnForest.setStyle("-fx-background-color: grey;");
        idBtnHill.setStyle(null);
        idBtnPlains.setStyle(null);

        WarGamesModel.setTerrain(TerrainType.FOREST);
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/forest.css")).toExternalForm());
    }

    /**
     * Button that changes terrain to HILL terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnHill(ActionEvent actionEvent) {
        idBtnForest.setStyle(null);
        idBtnHill.setStyle("-fx-background-color: grey;");
        idBtnPlains.setStyle(null);

        WarGamesModel.setTerrain(TerrainType.HILL);
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/hill.css")).toExternalForm());
    }

    /**
     * Button that changes terrain to PLAINS terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent OnAction, button pressed.
     */
    public void btnPlains(ActionEvent actionEvent) {
        idBtnForest.setStyle(null);
        idBtnHill.setStyle(null);
        idBtnPlains.setStyle("-fx-background-color: grey;");

        WarGamesModel.setTerrain(TerrainType.PLAINS);
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/plains.css")).toExternalForm());
    }

    /**
     * Method for initializing view data. These data regards file paths, what buttons
     * to be initially disabled, button color, and terrain enum.
     *
     * @see Initializable
     */
    private void initViewData() {
        WarGamesModel.setFilePathArmyOne(new File("src/main/resources/battle-files/The Orcish Horde.csv"));
        filePathArmyOne.setText(WarGamesModel.getFilePathArmyOne());

        WarGamesModel.setFilePathArmyTwo(new File("src/main/resources/battle-files/The Human Army.csv"));
        filePathArmyTwo.setText(WarGamesModel.getFilePathArmyTwo());

        idBtnStartBattle.setDisable(true);
        idBtnAddUnitsArmyOne.setDisable(true);
        idBtnAddUnitsArmyTwo.setDisable(true);
        idBtnAddUnitsFromFileArmyOne.setDisable(true);
        idBtnAddUnitsFromFileArmyTwo.setDisable(true);

        WarGamesModel.setTerrain(TerrainType.FOREST);
        idBtnForest.setStyle("-fx-background-color: grey;");
        idBtnInitializeArmies.setStyle("-fx-text-fill: white; -fx-background-color: green;");
    }

    /**
     * Method for setting CSS data regarding subtitle strokes, thickness and fill.
     * NOTE: When initialized, the application runs slower than what should be expected.
     *
     * @see Initializable
     */
    private void initTextStrokes() {
        setTextStrokeAndFill(nameArmyOne, 3.0);
        setTextStrokeAndFill(nameArmyTwo, 3.0);
        setTextStrokeAndFill(idTextUnitsListArmyOne, 2.0);
        setTextStrokeAndFill(idTextArmyOneFilePath, 2.0);
        setTextStrokeAndFill(idTextInitArmies, 2.0);
        setTextStrokeAndFill(idTextStartSim, 2.0);
        setTextStrokeAndFill(idTextChooseTerrain, 2.0);
        setTextStrokeAndFill(idTextBattleLog, 2.0);
        setTextStrokeAndFill(idTextUnitsListArmyTwo, 2.0);
        setTextStrokeAndFill(idTextArmyTwoFilePath, 2.0);
    }
    /**
     * Helper method to initTextStrokes() for minimizing code.
     */
    private void setTextStrokeAndFill(Text text, Double strokeWidth) {
        text.setStrokeWidth(strokeWidth);
        text.setStroke(Color.BLACK);
        text.setFill(Color.WHITE);
    }

    /**
     * Method sets tooltips to give additional information to a user, when hovering buttons.
     *
     * @see Initializable
     */
    private void initTooltips() {
        Tooltip.install(idBtnInitializeArmies,
                new Tooltip("""
                        Initializes and adds units to both armies according to given files.

                        All previous data regarding the armies will be lost."""));

        Tooltip.install(idBtnStartBattle,
                new Tooltip("Start a simulation between the two armies."));

        Tooltip tooltipBrowse = new Tooltip("Browse for a file representing this army.");
        Tooltip.install(idBtnArmyOneSelectFile, tooltipBrowse);
        Tooltip.install(idBtnArmyTwoSelectFile, tooltipBrowse);

        Tooltip tooltipAddUnitsFromFile = new Tooltip("Add a set of units from the chosen file");
        Tooltip.install(idBtnAddUnitsFromFileArmyOne, tooltipAddUnitsFromFile);
        Tooltip.install(idBtnAddUnitsFromFileArmyTwo, tooltipAddUnitsFromFile);

        Tooltip.install(idBtnForest,
                new Tooltip("""
                        Forrest terrain bonus:\s
                         - Infantry units gain increased attack and defence.
                         - Cavalry units have no defence.
                         - Ranged units have limited attack."""));
        Tooltip.install(idBtnHill,
                new Tooltip("""
                        Hill terrain bonus:\s
                         - Ranged units gain increased attack."""));
        Tooltip.install(idBtnPlains,
                new Tooltip("""
                        Plains terrain bonus:\s
                         - Cavalry units gain increased attack."""));

        Tooltip tooltipAddUnits = new Tooltip("Add specific units to the army.");
        Tooltip.install(idBtnAddUnitsArmyOne, tooltipAddUnits);
        Tooltip.install(idBtnAddUnitsArmyTwo, tooltipAddUnits);

        Tooltip tooltipPath = new Tooltip("Add the path to a file representing an army.");
        Tooltip.install(filePathArmyOne, tooltipPath);
        Tooltip.install(filePathArmyTwo, tooltipPath);

        Tooltip.install(idTextBattleLog, new Tooltip("A battle log will appear here after a simulation."));
    }

    /**
     * Method links static Controller data with View data.
     *
     * @see Initializable
     * @see ArmyWrapper
     */
    private void initBindings() {
        armyOneNumberOfUnits.textProperty().bind(armyOneWrapper.getTotalNrOfUnitsProperty().asString());
        armyOneNumberOfInfantry.textProperty().bind(armyOneWrapper.getTotalNrOfInfantryProperty().asString());
        armyOneNumberOfRanged.textProperty().bind(armyOneWrapper.getTotalNrOfRangedProperty().asString());
        armyOneNumberOfCavalry.textProperty().bind(armyOneWrapper.getTotalNrOfCavalryProperty().asString());
        armyOneNumberOfCommanders.textProperty().bind(armyOneWrapper.getTotalNrOfCommanderProperty().asString());

        armyTwoNumberOfUnits.textProperty().bind(armyTwoWrapper.getTotalNrOfUnitsProperty().asString());
        armyTwoNumberOfInfantry.textProperty().bind(armyTwoWrapper.getTotalNrOfInfantryProperty().asString());
        armyTwoNumberOfRanged.textProperty().bind(armyTwoWrapper.getTotalNrOfRangedProperty().asString());
        armyTwoNumberOfCavalry.textProperty().bind(armyTwoWrapper.getTotalNrOfCavalryProperty().asString());
        armyTwoNumberOfCommanders.textProperty().bind(armyTwoWrapper.getTotalNrOfCommanderProperty().asString());
    }

    /**
     * Method initializes listeners to watch over an army's units list.
     * Whenever a list is updated these listeners will update the
     * ListView content in the application view.
     *
     * ArmyWrapper class contains fields with an observable
     * datatype that can be updated by these lambda methods.
     *
     * @see Initializable
     * @see ArmyWrapper
     */
    private void initListeners() {
        armyOneWrapper.getUnits().addListener(
                (InvalidationListener) observable -> listViewArmyOneUnits
                        .setItems(FXCollections.observableArrayList(armyOneWrapper.getArmy().getUnits())));

        armyTwoWrapper.getUnits().addListener(
                (InvalidationListener) observable -> listViewArmyTwoUnits
                        .setItems(FXCollections.observableArrayList(armyTwoWrapper.getArmy().getUnits())));
    }

    /**
     * Method updates the view style for buttons that are relevant for adding units to an army.
     */
    public void buttonStylesUpdate() {

        if (armyOneWrapper.getArmy() == null) {
            idBtnAddUnitsFromFileArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            idBtnAddUnitsArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
        }
        else if (!armyOneWrapper.getArmy().hasUnits()) {
            idBtnAddUnitsFromFileArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            idBtnAddUnitsArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
        }
        else if (armyOneWrapper.getArmy().hasUnits()) {
            idBtnAddUnitsFromFileArmyOne.setStyle(null);
            idBtnAddUnitsArmyOne.setStyle(null);
        }

        if (armyTwoWrapper.getArmy() == null) {
            idBtnAddUnitsFromFileArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            idBtnAddUnitsArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
        }
        else if (!armyTwoWrapper.getArmy().hasUnits()) {
            idBtnAddUnitsFromFileArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            idBtnAddUnitsArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
        }
        else if (armyTwoWrapper.getArmy().hasUnits()) {
            idBtnAddUnitsFromFileArmyTwo.setStyle(null);
            idBtnAddUnitsArmyTwo.setStyle(null);
        }

        if (armyOneWrapper.getArmy().hasUnits() & armyTwoWrapper.getArmy().hasUnits()) {
            idBtnStartBattle.setStyle(null);
            idBtnInitializeArmies.setStyle(null);
        }
    }
}
