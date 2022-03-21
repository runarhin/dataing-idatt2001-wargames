package edu.ntnu.idatt2001.runarin.gui.views;

import edu.ntnu.idatt2001.runarin.battle.units.Unit;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    private static final String VERSION = "1.0.1";

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("War Games Application v" + VERSION);

        // Creating text fields for army one and two.
        TextField textFieldPathOne = new TextField("path/to/armyOne/file.csv");
        TextField textFieldPathTwo = new TextField("path/to/armyTwo/file.csv");

        // Creating buttons.
        Button btnStartBattle = new Button("Start battle!");
        btnStartBattle.setStyle("-fx-font-size: 2em; ");

        Button btnArmyOneChooseFile = new Button("Choose army file..");
        Button btnArmyOneInitArmyUnits = new Button("Initialise army with units");
        btnArmyOneChooseFile.setMinWidth(120);
        btnArmyOneInitArmyUnits.setMinWidth(160);

        Button btnArmyTwoChooseFile = new Button("Choose army file..");
        Button btnArmyTwoInitArmyUnits = new Button("Initialise army with units");
        btnArmyTwoChooseFile.setMinWidth(120);
        btnArmyTwoInitArmyUnits.setMinWidth(160);

        // creating list views.
        ListView<String> armyOneName = new ListView<>();
        armyOneName.setMinWidth(120);
        armyOneName.setMaxWidth(140);
        armyOneName.setMaxHeight(20);

        ListView<String> armyTwoName = new ListView<>();
        armyTwoName.setMinWidth(140);
        armyTwoName.setMaxWidth(180);
        armyTwoName.setMaxHeight(20);

        ListView<Unit> armyOneUnits = new ListView<>();
        Label labelArmyOne = new Label("Army One:");
        labelArmyOne.setFont(new Font("System Regular", 16));

        ListView<String> battleLog = new ListView<>();
        battleLog.setMinWidth(300);
        Label labelBattleLog = new Label("Battle log:");
        labelBattleLog.setFont(new Font("System Regular", 16));

        ListView<Unit> armyTwoUnits = new ListView<>();
        Label labelArmyTwo = new Label("Army Two:");
        labelArmyTwo.setFont(new Font("System Regular", 16));

        // Creating the grid pane and configure default set up.
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: BEIGE");
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(5);
        grid.setHgap(10);

        // Adding elements to the grid.
        grid.add(labelArmyOne,0,0);
        GridPane.setHalignment(labelArmyOne, HPos.LEFT);
        grid.add(armyOneName,1,0);
        GridPane.setHalignment(armyOneName, HPos.RIGHT);
        grid.add(armyOneUnits,0,2,2,5);
        GridPane.setHalignment(armyOneUnits, HPos.CENTER);
        GridPane.setValignment(armyOneUnits, VPos.BOTTOM);
        grid.add(btnArmyOneChooseFile,0,8);
        grid.add(textFieldPathOne,1,8);
        GridPane.setHalignment(textFieldPathOne, HPos.RIGHT);
        grid.add(btnArmyOneInitArmyUnits,0,9);

        grid.add(btnStartBattle,3,2);
        GridPane.setHalignment(btnStartBattle, HPos.CENTER);
        grid.add(labelBattleLog,3,3);
        grid.add(battleLog,3,4,1,3);

        grid.add(labelArmyTwo,5,0,2,1);
        GridPane.setHalignment(labelArmyTwo, HPos.LEFT);
        grid.add(armyTwoName,6,0);
        GridPane.setHalignment(armyTwoName, HPos.RIGHT);
        grid.add(armyTwoUnits,5,2,2,5);
        grid.add(btnArmyTwoChooseFile,5,8);
        GridPane.setHalignment(btnArmyTwoChooseFile, HPos.LEFT);
        grid.add(textFieldPathTwo,6,8);
        GridPane.setHalignment(textFieldPathTwo, HPos.RIGHT);
        grid.add(btnArmyTwoInitArmyUnits,5,9);
        GridPane.setHalignment(btnArmyTwoInitArmyUnits, HPos.LEFT);

        // Setting size and showing stage.
        primaryStage.setScene(new Scene(grid, 1040, 440));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}