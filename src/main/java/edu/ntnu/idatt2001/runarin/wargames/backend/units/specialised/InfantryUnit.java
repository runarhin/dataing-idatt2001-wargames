package edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;

/**
 * A class for an infantry unit.
 * This is a basic melee unit with normal attack power and armor resistance.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-17
 */
public class InfantryUnit extends Unit {

    private static final int BASE_ATTACK = 15;
    private static final int BASE_DEFENCE = 10;
    private final int FOREST_BONUS = 3;

    /**
     * Constructor for instantiation of the InfantryUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor armor points which decreases the damage taken from an opponent.
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the InfantryUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public InfantryUnit(String name, int health) {
        super(name, health, BASE_ATTACK, BASE_DEFENCE);
    }

    /**
     * Returns the attack bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * The unit have an advantage when fighting in FOREST terrain.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return value of the attack bonus for an infantry unit.
     */
    @Override
    public int getAttackBonus(TerrainType terrain) {
        int attackBonus = 2;
        return (terrain.equals(TerrainType.FOREST)) ? attackBonus + FOREST_BONUS : attackBonus;
    }

    /**
     * Returns the resist bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * The unit have an advantage when fighting in FOREST terrain.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return value of the resist bonus for an infantry unit.
     */
    @Override
    public int getResistBonus(TerrainType terrain) {
        int resistBonus = 1;
        return (terrain.equals(TerrainType.FOREST)) ? resistBonus + FOREST_BONUS : resistBonus;
    }
}
