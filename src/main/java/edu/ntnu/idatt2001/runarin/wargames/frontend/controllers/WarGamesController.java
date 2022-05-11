package edu.ntnu.idatt2001.runarin.wargames.frontend.controllers;

import static edu.ntnu.idatt2001.runarin.wargames.frontend.WarGamesApp.giveError;
import static edu.ntnu.idatt2001.runarin.wargames.frontend.WarGamesApp.giveInformation;
import static edu.ntnu.idatt2001.runarin.wargames.frontend.model.WarGamesModel.*;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.wargames.backend.exceptions.ArmyEmptyOfUnitsException;
import edu.ntnu.idatt2001.runarin.wargames.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * MVC Controller class for communicating between the application and the model holding data.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-11
 */
public class WarGamesController implements Initializable {

    TerrainType terrain;

    @FXML private Text nameArmyOne;
    @FXML private Label armyOneNumberOfUnits;
    @FXML private Label armyOneNumberOfCommanders;
    @FXML private Label armyOneNumberOfCavalry;
    @FXML private Label armyOneNumberOfInfantry;
    @FXML private Label armyOneNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyOneUnits;
    @FXML private TextField filePathArmyOne;
    @FXML private Button idBtnInitialiseArmyOne;

    @FXML private Text nameArmyTwo;
    @FXML private Label armyTwoNumberOfUnits;
    @FXML private Label armyTwoNumberOfCommanders;
    @FXML private Label armyTwoNumberOfCavalry;
    @FXML private Label armyTwoNumberOfInfantry;
    @FXML private Label armyTwoNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyTwoUnits;
    @FXML private TextField filePathArmyTwo;
    @FXML private Button idBtnInitialiseArmyTwo;

    @FXML private Button idBtnArmyOneSelectFile;
    @FXML private Button idBtnArmyTwoSelectFile;
    @FXML private Button idBtnReinitialize;
    @FXML private Button idBtnStartBattle;
    @FXML private Button idBtnForest;
    @FXML private Button idBtnHill;
    @FXML private Button idBtnPlains;
    @FXML private TextArea textAreaBattleLog;
    @FXML private GridPane idGridPane;

    /**
     * Initializes all relevant and helpful data for the user.
     * This so that the user does not have to do this every time the program starts.
     *
     * @param url url
     * @param resourceBundle rb
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setFilePathArmyOne(new File("src/main/resources/battle-files/The Orcish Horde.csv"));
        filePathArmyOne.setText(getFilePathArmyOne());

        setFilePathArmyTwo(new File("src/main/resources/battle-files/The Human Army.csv"));
        filePathArmyTwo.setText(getFilePathArmyTwo());

        Tooltip.install(idBtnReinitialize,
                new Tooltip("Initializes both armies and adds units according to the chosen army files."));
        Tooltip.install(idBtnStartBattle,
                new Tooltip("Start the simulation of the two battling armies."));
        Tooltip.install(idBtnArmyOneSelectFile,
                new Tooltip("Browse for a file representing this army."));
        Tooltip.install(idBtnArmyTwoSelectFile,
                new Tooltip("Browse for a file representing this army."));
        Tooltip.install(idBtnInitialiseArmyOne,
                new Tooltip("Initialize army from chosen file."));
        Tooltip.install(idBtnInitialiseArmyTwo,
                new Tooltip("Initialize army from chosen file."));
        Tooltip.install(idBtnForest,
                new Tooltip("Infantry units gain attack and defence bonuses in forest terrain."
                + "\nCavalry units have no defence bonus and ranged units have limited attack bonus in this terrain."));
        Tooltip.install(idBtnHill,
                new Tooltip("Ranged units receive an attack bonus while fighting in hill terrain."));
        Tooltip.install(idBtnPlains,
                new Tooltip("Cavalry units gain attack bonus when fighting on plains terrain."));

        terrain = TerrainType.FOREST;
        idBtnForest.setStyle("-fx-background-color: grey;");
        idBtnReinitialize.setStyle("-fx-text-fill: white; -fx-background-color: green;");
        idBtnStartBattle.setStyle("-fx-background-color: grey;");
    }

    /**
     * Button that starts the simulation.
     *
     * @param actionEvent action event.
     */
    public void btnStartBattle(ActionEvent actionEvent) {

        StringBuilder battleLog = null;
        try {
            setBattle(new Battle(getArmyOne(), getArmyTwo()));
            getBattle().simulate(terrain);
            battleLog = FileHandler.readStringBuilderFromFile();

            armyOneNumberOfUnits.setText(String.valueOf(getArmyOne().getAllUnits().size()));
            armyOneNumberOfCommanders.setText(String.valueOf(getArmyOne().getCommanderUnits().size()));
            armyOneNumberOfCavalry.setText(String.valueOf(getArmyOne().getCavalryUnits().size()));
            armyOneNumberOfRanged.setText(String.valueOf(getArmyOne().getRangedUnits().size()));
            armyOneNumberOfInfantry.setText(String.valueOf(getArmyOne().getInfantryUnits().size()));

            armyTwoNumberOfUnits.setText(String.valueOf(getArmyTwo().getAllUnits().size()));
            armyTwoNumberOfCommanders.setText(String.valueOf(getArmyTwo().getCommanderUnits().size()));
            armyTwoNumberOfCavalry.setText(String.valueOf(getArmyTwo().getCavalryUnits().size()));
            armyTwoNumberOfRanged.setText(String.valueOf(getArmyTwo().getRangedUnits().size()));
            armyTwoNumberOfInfantry.setText(String.valueOf(getArmyTwo().getInfantryUnits().size()));

            ObservableList<Unit> obsListArmyOne = listenerListViewUnits(getArmyOne());
            listViewArmyOneUnits.setItems(obsListArmyOne);

            ObservableList<Unit> obsListArmyTwo = listenerListViewUnits(getArmyTwo());
            listViewArmyTwoUnits.setItems(obsListArmyTwo);
        }
        catch (ArmyEmptyOfUnitsException e) {
            if (getArmyOne() == null) {
                idBtnInitialiseArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            } else if (!getArmyOne().hasUnits()) {
                idBtnInitialiseArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            }
            if (getArmyTwo() == null) {
                idBtnInitialiseArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            } else if (!getArmyTwo().hasUnits()) {
                idBtnInitialiseArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            }
            giveInformation(e.getMessage());
        }
        catch (IllegalArgumentException | IOException | NullPointerException e) {
            giveError(e.getMessage());
        }

        textAreaBattleLog.clear();
        if (battleLog != null) {
            idBtnStartBattle.setStyle("-fx-background-color: grey;");
            idBtnReinitialize.setStyle(null);
            textAreaBattleLog.appendText(battleLog.toString());
            textAreaBattleLog.setScrollTop(Double.MAX_VALUE);
        }
        else {
            textAreaBattleLog.appendText("Battle log not available.");
        }
    }

    /**
     * Button that selects the file path for army one.
     *
     * @param actionEvent action event.
     */
    public void btnArmyOneSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select army from file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            setFilePathArmyOne(file);
            filePathArmyOne.setText(getFilePathArmyOne());
        }
    }

    /**
     * Button that selects the file path for army two.
     *
     * @param actionEvent action event.
     */
    public void btnArmyTwoSelectFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select army from file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV","*.csv"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            setFilePathArmyTwo(file);
            filePathArmyTwo.setText(getFilePathArmyTwo());
        }
    }

    /**
     * Button that initializes army one with units according to selected army file.
     *
     * @param actionEvent action event.
     */
    public void btnInitialiseArmyOne(ActionEvent actionEvent) {
        setFilePathArmyOne(new File(filePathArmyOne.getText()));

        if (getArmyOne() != null) {
            if (!getArmyOne().hasUnits()) {
                idBtnStartBattle.setStyle(null);
            }
        }
        initArmyOne();

        if (getArmyTwo() != null) {
            if (getArmyTwo().hasUnits()) {
                idBtnReinitialize.setStyle(null);
                idBtnReinitialize.setText("Reinitialize");
                idBtnStartBattle.setStyle(null);
            }
        }
    }

    /**
     * Helper method that initializes army one, to be used
     * in both btnInitialiseArmyOne() and btnReinitialize().
     */
    private void initArmyOne() {
        try {
            String newArmyName = FileHandler.readArmyNameFromFile(getFilePathArmyOne());
            Army newArmy = new Army(newArmyName);
            newArmy.addUnitsFromFile(getFilePathArmyOne());
            setArmyOne(newArmy);

            nameArmyOne.setText(newArmyName);
            armyOneNumberOfUnits.setText(String.valueOf(getArmyOne().getAllUnits().size()));
            armyOneNumberOfCommanders.setText(String.valueOf(getArmyOne().getCommanderUnits().size()));
            armyOneNumberOfCavalry.setText(String.valueOf(getArmyOne().getCavalryUnits().size()));
            armyOneNumberOfRanged.setText(String.valueOf(getArmyOne().getRangedUnits().size()));
            armyOneNumberOfInfantry.setText(String.valueOf(getArmyOne().getInfantryUnits().size()));

            ObservableList<Unit> obsListArmyOne = listenerListViewUnits(getArmyOne());
            listViewArmyOneUnits.setItems(obsListArmyOne);

            idBtnInitialiseArmyOne.setStyle(null);

        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            giveError(e.getMessage());
        }
    }

    /**
     * Button that initializes army two with units according to selected army file.
     *
     * @param actionEvent action event.
     */
    public void btnInitialiseArmyTwo(ActionEvent actionEvent) {
        setFilePathArmyTwo(new File(filePathArmyTwo.getText()));

        if (getArmyTwo() != null) {
            if (!getArmyTwo().hasUnits()) {
                idBtnStartBattle.setStyle(null);
            }
        }
        initArmyTwo();

        if (getArmyOne() != null) {
            if (getArmyOne().hasUnits()) {
                idBtnReinitialize.setStyle(null);
                idBtnReinitialize.setText("Reinitialize");
                idBtnStartBattle.setStyle(null);
            }
        }
    }

    /**
     * Helper method that initializes army two, to be used
     * in both btnInitialiseArmyTwo() and btnReinitialize().
     */
    private void initArmyTwo() {
        try {
            String newArmyName = FileHandler.readArmyNameFromFile(getFilePathArmyTwo());
            Army newArmy = new Army(newArmyName);
            newArmy.addUnitsFromFile(getFilePathArmyTwo());
            setArmyTwo(newArmy);

            nameArmyTwo.setText(newArmyName);
            armyTwoNumberOfUnits.setText(String.valueOf(getArmyTwo().getAllUnits().size()));
            armyTwoNumberOfCommanders.setText(String.valueOf(getArmyTwo().getCommanderUnits().size()));
            armyTwoNumberOfCavalry.setText(String.valueOf(getArmyTwo().getCavalryUnits().size()));
            armyTwoNumberOfRanged.setText(String.valueOf(getArmyTwo().getRangedUnits().size()));
            armyTwoNumberOfInfantry.setText(String.valueOf(getArmyTwo().getInfantryUnits().size()));

            ObservableList<Unit> obsListArmyTwo = listenerListViewUnits(getArmyTwo());
            listViewArmyTwoUnits.setItems(obsListArmyTwo);

            idBtnInitialiseArmyTwo.setStyle(null);

        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            giveError(e.getMessage());
        }
    }

    /**
     * Button that initializes both armies with units according to selected army files.
     *
     * @param actionEvent action event.
     */
    public void btnReinitialize(ActionEvent actionEvent) {
        idBtnReinitialize.setStyle("-fx-background-color: grey;");
        idBtnReinitialize.setText("Reinitialize");

        idBtnStartBattle.setStyle(null);
        textAreaBattleLog.clear();
        idBtnInitialiseArmyOne.setStyle(null);
        idBtnInitialiseArmyTwo.setStyle(null);

        initArmyOne();
        initArmyTwo();
    }

    /**
     * Button that changes terrain to FORREST terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnForest(ActionEvent actionEvent) {
        idBtnForest.setStyle("-fx-background-color: grey;");
        idBtnHill.setStyle(null);
        idBtnPlains.setStyle(null);

        terrain = TerrainType.FOREST;
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/forest.css")).toExternalForm());

    }

    /**
     * Button that changes terrain to HILL terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnHill(ActionEvent actionEvent) {
        idBtnForest.setStyle(null);
        idBtnHill.setStyle("-fx-background-color: grey;");
        idBtnPlains.setStyle(null);

        terrain = TerrainType.HILL;
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/hill.css")).toExternalForm());
    }

    /**
     * Button that changes terrain to PLAINS terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnPlains(ActionEvent actionEvent) {
        idBtnForest.setStyle(null);
        idBtnHill.setStyle(null);
        idBtnPlains.setStyle("-fx-background-color: grey;");

        terrain = TerrainType.PLAINS;
        idGridPane.getStylesheets().clear();
        idGridPane.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/plains.css")).toExternalForm());
    }

    /**
     * Observes whether there is a change in one of the armies' units list and updates list on change.
     *
     * @param army the observed army.
     * @return updated list with the remaining units.
     */
    private ObservableList<Unit> listenerListViewUnits(Army army) {

        ObservableList<Unit> obsArmyList = FXCollections.observableList(army.getAllUnits());
        obsArmyList.addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(Change<? extends Unit> change) {
                obsArmyList.setAll(army.getAllUnits());
            }
        });
        return obsArmyList;
    }
}
