package edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;

/**
 * A class for a cavalry unit.
 * This is a unit with relatively high attack power and also comes with a charge ability.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-17
 */

public class CavalryUnit extends Unit {

    /**
     * chargeReady is used to determine if the warrior have attacked a target.
     * This to calculate the attack bonus.
     */
    private boolean chargeReady = true;
    private static final int BASE_ATTACK = 20;
    private static final int BASE_DEFENCE = 12;

    /**
     * Constructor for instantiation of the CavalryUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor armor points which decreases the damage taken from an opponent.
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the CavalryUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public CavalryUnit(String name, int health) {
        super(name, health, BASE_ATTACK, BASE_DEFENCE);
    }

    /**
     * Returns the attack bonus value.
     * The attack bonus will change as the cavalry unit have ont attacked a target before.
     * The unit have an advantage when fighting on PLAINS, but a handicap when in FOREST.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return value of the attack bonus for a cavalry unit.
     */
    @Override
    public int getAttackBonus(TerrainType terrain) {
        int attackBonus = 2;
        int plainsBonus = 5;

        if (terrain.equals(TerrainType.PLAINS)) attackBonus += plainsBonus;

        if (chargeReady) {
            chargeReady = false;
            return attackBonus + 4;
        } else {
            return attackBonus;
        }
    }

    /**
     * Returns the resist bonus value.
     * If the unit is fighting in a FOREST the resist bonus will be zero.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return value of the resist bonus for a cavalry unit.
     */
    @Override
    public int getResistBonus(TerrainType terrain) {
        int resistBonus = 1;
        int forestHandicap = 0;

        return (terrain.equals(TerrainType.FOREST)) ? forestHandicap : resistBonus;
    }

    /**
     * Resets the charge ability for a cavalry unit when its opponent dies.
     * Used to indicate that the cavalry unit can use the charge ability again,
     * and against a new opponent at a distance.
     */
    public void resetChargeAbility() {
        this.chargeReady = true;
    }
}
