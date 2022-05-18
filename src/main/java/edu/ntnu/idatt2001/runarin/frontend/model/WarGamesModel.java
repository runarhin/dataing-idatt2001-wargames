package edu.ntnu.idatt2001.runarin.frontend.model;

import edu.ntnu.idatt2001.runarin.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.backend.units.TerrainType;

import java.io.File;

/**
 * MVC Model-class for holding values used in the GUI.
 * This class holds all static data which does not need a listener,
 * and that can be accessed when called for.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class WarGamesModel {

    private static Battle battle;
    private static TerrainType terrain;
    private static ArmySelect armySelect;
    private static File filePathArmyOne;
    private static File filePathArmyTwo;

    /**
     * Return the static object battle.
     *
     * @return the battle object, representing the battle between two armies.
     */
    public static Battle getBattle() {
        return battle;
    }

    /**
     * Set the battle object which holds the battle-simulation between two armies.
     *
     * @param battle the battle between two armies.
     */
    public static void setBattle(Battle battle) {
        WarGamesModel.battle = battle;
    }

    /**
     * Return the terrain enum.
     *
     * @return enum terrain; FOREST, HILL or PLAINS.
     */
    public static TerrainType getTerrain() {
        return terrain;
    }

    /**
     * Set the terrain enum.
     *
     * @param terrain enum terrain; FOREST, HILL or PLAINS.
     */
    public static void setTerrain(TerrainType terrain) {
        WarGamesModel.terrain = terrain;
    }

    /**
     * Return the selected army.
     * This is used to tell which of the armies to have units added.
     *
     * @return selected army; ARMY_ONE, ARMY_TWO.
     */
    public static ArmySelect getArmySelect() {
        return armySelect;
    }

    /**
     * Set the army to be pointed at.
     *
     * @param armySelect set army; ARMY_ONE, ARMY_TWO.
     */
    public static void setArmySelect(ArmySelect armySelect) {
        WarGamesModel.armySelect = armySelect;
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
     * Set the file path and -name, to where army one is to be stored.
     *
     * @param filePathArmyOne file path and -name for army one.
     */
    public static void setFilePathArmyOne(File filePathArmyOne) {
        WarGamesModel.filePathArmyOne = filePathArmyOne;
    }

    /**
     * Set the file path and -name, to where army two is to be stored.
     *
     * @param filePathArmyTwo file path and -name for army two.
     */
    public static void setFilePathArmyTwo(File filePathArmyTwo) {
        WarGamesModel.filePathArmyTwo = filePathArmyTwo;
    }
}
