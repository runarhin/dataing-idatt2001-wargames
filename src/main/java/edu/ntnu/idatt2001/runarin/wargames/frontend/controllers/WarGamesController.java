package edu.ntnu.idatt2001.runarin.wargames.frontend.controllers;
import static edu.ntnu.idatt2001.runarin.wargames.frontend.model.WarGamesModel.*;

import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.wargames.backend.exceptions.ArmyEmptyOfUnitsException;
import edu.ntnu.idatt2001.runarin.wargames.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.wargames.frontend.views.WarGamesApp;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-19
 */
public class WarGamesController implements Initializable {

    @FXML private Label nameArmyOne;
    @FXML private Label armyOneNumberOfUnits;
    @FXML private Label armyOneNumberOfCommanders;
    @FXML private Label armyOneNumberOfCavalry;
    @FXML private Label armyOneNumberOfInfantry;
    @FXML private Label armyOneNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyOneUnits;
    @FXML private TextField filePathArmyOne;

    @FXML private Label nameArmyTwo;
    @FXML private Label armyTwoNumberOfUnits;
    @FXML private Label armyTwoNumberOfCommanders;
    @FXML private Label armyTwoNumberOfCavalry;
    @FXML private Label armyTwoNumberOfInfantry;
    @FXML private Label armyTwoNumberOfRanged;
    @FXML private ListView<Unit> listViewArmyTwoUnits;
    @FXML private TextField filePathArmyTwo;

    @FXML private Button idBtnStartBattle;
    @FXML private Button idBtnForest;
    @FXML private Button idBtnHill;
    @FXML private Button idBtnPlains;

    @FXML private TextArea textFieldBattleLog;

    TerrainType terrain;

    /*
    private ObservableList<Unit> obsListArmyOne;
    private void initArmyOneUnitsListView() {
        obsListArmyOne = FXCollections.observableList(getArmyOne().getAllUnits());
        obsListArmyOne.addListener(new ListChangeListener<Unit>() {
            @Override
            public void onChanged(Change<? extends Unit> change) {
                System.out.println("Army One: List change detected.");
                obsListArmyOne.setAll(getArmyOne().getAllUnits());
                listViewArmyOneUnits.setItems(null);
                listViewArmyOneUnits.setItems(obsListArmyOne);
            }
        });
        listViewArmyOneUnits.setItems(obsListArmyOne);
    }*/

    private ObservableList<Unit> updateUnitsListView(Army army) {

        ObservableList<Unit> obsArmyList = FXCollections.observableList(army.getAllUnits());
        obsArmyList.addListener(new ListChangeListener<>() {
            @Override
            public void onChanged(Change<? extends Unit> change) {
                System.out.println("Army One: List change detected.");
                obsArmyList.setAll(army.getAllUnits());
            }
        });
        return obsArmyList;
    }


    public void btnStartBattle(ActionEvent actionEvent) {

        StringBuilder battleLog = null;
        try {
            Battle battle = new Battle(getArmyOne(), getArmyTwo());
            battle.simulate(terrain);
            battleLog = FileHandler.readBattleLogFromFile();
        }
        catch (IOException e) {
            WarGamesApp.giveInformation(e.getMessage());
        }

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

        ObservableList<Unit> obsListArmyOne = updateUnitsListView(getArmyOne());
        listViewArmyOneUnits.setItems(obsListArmyOne);

        ObservableList<Unit> obsListArmyTwo = updateUnitsListView(getArmyTwo());
        listViewArmyTwoUnits.setItems(obsListArmyTwo);

        //if (!getArmyOne().hasUnits() || !getArmyTwo().hasUnits()) idBtnStartBattle.setDisable(true);

        if (battleLog != null) {
            textFieldBattleLog.setText(battleLog.toString());textFieldBattleLog.setText(battleLog.toString());
        }
        else {
            textFieldBattleLog.setText("Battle log not available.");
        }
    }

    public void btnForest(ActionEvent actionEvent) {
        idBtnStartBattle.setDisable(false);
        idBtnForest.setDisable(true);
        idBtnHill.setDisable(false);
        idBtnPlains.setDisable(false);

        terrain = TerrainType.FOREST;
    }

    public void btnHill(ActionEvent actionEvent) {
        idBtnStartBattle.setDisable(false);
        idBtnForest.setDisable(false);
        idBtnHill.setDisable(true);
        idBtnPlains.setDisable(false);

        terrain = TerrainType.HILL;
    }

    public void btnPlains(ActionEvent actionEvent) {
        idBtnStartBattle.setDisable(false);
        idBtnForest.setDisable(false);
        idBtnHill.setDisable(false);
        idBtnPlains.setDisable(true);

        terrain = TerrainType.PLAINS;
    }


    public void btnArmyOneSelectFile(ActionEvent actionEvent) {
    }

    public void btnArmyTwoSelectFile(ActionEvent actionEvent) {
    }

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

        ObservableList<Unit> obsListArmyOne = updateUnitsListView(getArmyOne());
        listViewArmyOneUnits.setItems(obsListArmyOne);
    }

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

        ObservableList<Unit> obsListArmyTwo = updateUnitsListView(getArmyTwo());
        listViewArmyTwoUnits.setItems(obsListArmyTwo);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setFilePathArmyOne(new File("src/main/resources/battle-files/The Orcish Horde.csv"));
        filePathArmyOne.setText(getFilePathArmyOne());

        setFilePathArmyTwo(new File("src/main/resources/battle-files/The Human Army.csv"));
        filePathArmyTwo.setText(getFilePathArmyTwo());
    }
}
