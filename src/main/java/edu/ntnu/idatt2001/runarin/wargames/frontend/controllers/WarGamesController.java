package edu.ntnu.idatt2001.runarin.wargames.frontend.controllers;

import static edu.ntnu.idatt2001.runarin.wargames.frontend.model.WarGamesModel.*;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.wargames.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.wargames.frontend.WarGamesApp;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MVC Controller class for communicating between the application and the model holding data.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-05-01
 */
public class WarGamesController implements Initializable {

    TerrainType terrain;

    @FXML private Label nameArmyOne;
    @FXML private Label armyOneNumberOfUnits;
    @FXML private Label armyOneNumberOfCommanders;
    @FXML private Label armyOneNumberOfCavalry;
    @FXML private Label armyOneNumberOfInfantry;
    @FXML private Label armyOneNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyOneUnits;
    @FXML private TextField filePathArmyOne;
    @FXML private Button idBtnInitialiseArmyOne;

    @FXML private Label nameArmyTwo;
    @FXML private Label armyTwoNumberOfUnits;
    @FXML private Label armyTwoNumberOfCommanders;
    @FXML private Label armyTwoNumberOfCavalry;
    @FXML private Label armyTwoNumberOfInfantry;
    @FXML private Label armyTwoNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyTwoUnits;
    @FXML private TextField filePathArmyTwo;
    @FXML private Button idBtnInitialiseArmyTwo;

    @FXML private Button idBtnStartBattle;
    @FXML private Button idBtnForest;
    @FXML private Button idBtnHill;
    @FXML private Button idBtnPlains;
    @FXML private TextArea textAreaBattleLog;

    @FXML private GridPane idGridPane;
    @FXML private Hyperlink idBtnArmyOneSelectFile;
    @FXML private Hyperlink idBtnArmyTwoSelectFile;


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

    /**
     * Button that starts the simulation.
     *
     * @param actionEvent action event.
     */
    public void btnStartBattle(ActionEvent actionEvent) {

        StringBuilder battleLog = null;
        try {
            Battle battle = new Battle(getArmyOne(), getArmyTwo());
            battle.simulate(terrain);
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
        catch (IOException e) {

            if (getArmyOne() == null) {
                System.out.println("\n" + idBtnInitialiseArmyOne.getStyleClass());
                idBtnInitialiseArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
                System.out.println("\n" + idBtnInitialiseArmyOne.getStyleClass());
            } else if (!getArmyOne().hasUnits()) {
                idBtnInitialiseArmyOne.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            }
            if (getArmyTwo() == null) {
                idBtnInitialiseArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            } else if (!getArmyTwo().hasUnits()) {
                idBtnInitialiseArmyTwo.setStyle("-fx-text-fill: white; -fx-background-color: green;");
            }
            WarGamesApp.giveInformation(e.getMessage());
        }

        if (battleLog != null) {
            textAreaBattleLog.clear();
            textAreaBattleLog.appendText(battleLog.toString());
            textAreaBattleLog.setScrollTop(Double.MAX_VALUE);
        }
        else {
            textAreaBattleLog.clear();
            textAreaBattleLog.appendText("Battle log not available.");
        }
    }

    /**
     * Button that changes terrain to FORREST terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnForest(ActionEvent actionEvent) {
        idBtnForest.setDisable(true);
        idBtnHill.setDisable(false);
        idBtnPlains.setDisable(false);

        terrain = TerrainType.FOREST;
        //TODO: Change CSS background to FOREST. Does not work.
        idGridPane.getStylesheets().add(getClass().getResource("/css/forest.css").toExternalForm());
    }

    /**
     * Button that changes terrain to HILL terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnHill(ActionEvent actionEvent) {
        idBtnForest.setDisable(false);
        idBtnHill.setDisable(true);
        idBtnPlains.setDisable(false);

        terrain = TerrainType.HILL;
        //TODO: Change CSS background to HILL. Does not work.
        idGridPane.getStylesheets().add(getClass().getResource("/css/hill.css").toExternalForm());
    }

    /**
     * Button that changes terrain to PLAINS terrain.
     * Units will have benefits accordingly.
     *
     * @param actionEvent action event.
     */
    public void btnPlains(ActionEvent actionEvent) {
        idBtnForest.setDisable(false);
        idBtnHill.setDisable(false);
        idBtnPlains.setDisable(true);

        terrain = TerrainType.PLAINS;
        //TODO: Change CSS background to PLAINS. Does not work.
        idGridPane.getStylesheets().add(getClass().getResource("/css/plains.css").toExternalForm());
    }

    /**
     * Button that selects the file path for army one.
     *
     * @param actionEvent action event.
     */
    public void btnArmyOneSelectFile(ActionEvent actionEvent) {
    }

    /**
     * Button that selects the file path for army two.
     *
     * @param actionEvent action event.
     */
    public void btnArmyTwoSelectFile(ActionEvent actionEvent) {
    }

    /**
     * Button that initializes army one with units according to selected army file.
     *
     * @param actionEvent action event.
     * @throws IOException throws IO exception if there is any errors in the file.
     */
    public void btnInitialiseArmyOne(ActionEvent actionEvent) throws IOException {
        String newArmyName = FileHandler.readArmyNameFromFile(getFilePathArmyOne());
        nameArmyOne.setText(newArmyName);

        Army newArmy = new Army(newArmyName);
        newArmy.addUnitsFromFile(getFilePathArmyOne());
        setArmyOne(newArmy);

        armyOneNumberOfUnits.setText(String.valueOf(getArmyOne().getAllUnits().size()));
        armyOneNumberOfCommanders.setText(String.valueOf(getArmyOne().getCommanderUnits().size()));
        armyOneNumberOfCavalry.setText(String.valueOf(getArmyOne().getCavalryUnits().size()));
        armyOneNumberOfRanged.setText(String.valueOf(getArmyOne().getRangedUnits().size()));
        armyOneNumberOfInfantry.setText(String.valueOf(getArmyOne().getInfantryUnits().size()));

        ObservableList<Unit> obsListArmyOne = listenerListViewUnits(getArmyOne());
        listViewArmyOneUnits.setItems(obsListArmyOne);

        idBtnInitialiseArmyOne.setStyle(null);
    }

    /**
     * Button that initializes army two with units according to selected army file.
     *
     * @param actionEvent action event.
     * @throws IOException throws IO exception if there is any errors in the file.
     */
    public void btnInitialiseArmyTwo(ActionEvent actionEvent) throws IOException {
        String newArmyName = FileHandler.readArmyNameFromFile(getFilePathArmyTwo());
        nameArmyTwo.setText(newArmyName);

        Army newArmy = new Army(newArmyName);
        newArmy.addUnitsFromFile(getFilePathArmyTwo());
        setArmyTwo(newArmy);

        armyTwoNumberOfUnits.setText(String.valueOf(getArmyTwo().getAllUnits().size()));
        armyTwoNumberOfCommanders.setText(String.valueOf(getArmyTwo().getCommanderUnits().size()));
        armyTwoNumberOfCavalry.setText(String.valueOf(getArmyTwo().getCavalryUnits().size()));
        armyTwoNumberOfRanged.setText(String.valueOf(getArmyTwo().getRangedUnits().size()));
        armyTwoNumberOfInfantry.setText(String.valueOf(getArmyTwo().getInfantryUnits().size()));

        ObservableList<Unit> obsListArmyTwo = listenerListViewUnits(getArmyTwo());
        listViewArmyTwoUnits.setItems(obsListArmyTwo);

        idBtnInitialiseArmyTwo.setStyle(null);
    }

    /**
     * Initializes all relevant and helpful data for the user.
     * This so that the user does not have to do this every time the program starts.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setFilePathArmyOne(new File("src/main/resources/battle-files/The Orcish Horde.csv"));
        filePathArmyOne.setText(getFilePathArmyOne());

        setFilePathArmyTwo(new File("src/main/resources/battle-files/The Human Army.csv"));
        filePathArmyTwo.setText(getFilePathArmyTwo());


        terrain = TerrainType.FOREST;
        idBtnForest.setDisable(true);
    }
}
