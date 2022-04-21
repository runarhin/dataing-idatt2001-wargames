package edu.ntnu.idatt2001.runarin.wargames.frontend.model;

import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import java.io.File;

/**
 *
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-19
 */
public class WarGamesModel {

    private static Army armyOne;
    private static Army armyTwo;
    private static File filePathArmyOne;
    private static File filePathArmyTwo;

    public static Army getArmyOne() {
        return armyOne;
    }

    public static Army getArmyTwo() {
        return armyTwo;
    }

    public static void setArmyOne(Army armyOne) {
        WarGamesModel.armyOne = armyOne;
    }

    public static void setArmyTwo(Army armyTwo) {
        WarGamesModel.armyTwo = armyTwo;
    }

    public static String getFilePathArmyOne() {
        return filePathArmyOne.getPath();
    }

    public static String getFilePathArmyTwo() {
        return filePathArmyTwo.getPath();
    }

    public static void setFilePathArmyOne(File filePathArmyOne) {
        WarGamesModel.filePathArmyOne = filePathArmyOne;
    }

    public static void setFilePathArmyTwo(File filePathArmyTwo) {
        WarGamesModel.filePathArmyTwo = filePathArmyTwo;
    }
}
