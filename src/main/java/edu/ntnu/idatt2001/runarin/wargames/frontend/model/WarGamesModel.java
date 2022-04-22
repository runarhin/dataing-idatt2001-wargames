package edu.ntnu.idatt2001.runarin.wargames.frontend.model;

import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;

import java.io.File;

/**
 * MVC Model-class for holding values used in the GUI.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-22
 */
public class WarGamesModel {

    private static Army armyOne;
    private static Army armyTwo;
    private static File filePathArmyOne;
    private static File filePathArmyTwo;

    /**
     * Return the static object army one.
     *
     * @return army one, initialised by input army file.
     * @see edu.ntnu.idatt2001.runarin.wargames.frontend.controllers.WarGamesController
     */
    public static Army getArmyOne() {
        return armyOne;
    }

    /**
     * Return the static object army two.
     *
     * @return army two, initialised by input army file.
     * @see edu.ntnu.idatt2001.runarin.wargames.frontend.controllers.WarGamesController
     */
    public static Army getArmyTwo() {
        return armyTwo;
    }

    /**
     * Set an army to function as the first battling army in the simulation.
     *
     * @param armyOne the first out of two armies to battle in the simulation.
     */
    public static void setArmyOne(Army armyOne) {
        WarGamesModel.armyOne = armyOne;
    }

    /**
     * Set an army to function as the second battling army in the simulation.
     *
     * @param armyTwo the second out of two armies to battle in the simulation.
     */
    public static void setArmyTwo(Army armyTwo) {
        WarGamesModel.armyTwo = armyTwo;
    }

    /**
     * Return the file path and -name, to where army one is stored.
     *
     * @return file path and -name for army one.
     */
    public static String getFilePathArmyOne() {
        return filePathArmyOne.getPath();
    }

    /**
     * Return the file path and -name, to where army two is stored.
     *
     * @return file path and -name for army two.
     */
    public static String getFilePathArmyTwo() {
        return filePathArmyTwo.getPath();
    }

    /**
     * Sets the file path and -name, to where army one is to be stored.
     *
     * @param filePathArmyOne file path and -name for army one.
     */
    public static void setFilePathArmyOne(File filePathArmyOne) {
        WarGamesModel.filePathArmyOne = filePathArmyOne;
    }

    /**
     * Sets the file path and -name, to where army two is to be stored.
     *
     * @param filePathArmyTwo file path and -name for army two.
     */
    public static void setFilePathArmyTwo(File filePathArmyTwo) {
        WarGamesModel.filePathArmyTwo = filePathArmyTwo;
    }
}
