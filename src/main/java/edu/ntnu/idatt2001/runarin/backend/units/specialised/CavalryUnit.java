package edu.ntnu.idatt2001.runarin.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.backend.units.Unit;

/**
 * A class for a cavalry unit.
 * This is a unit with relatively high attack power and also comes with a charge ability.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-04-03
 */

public class CavalryUnit extends Unit {

    /**
     * chargeReady is used to determine if the warrior have attacked a target.
     * This to calculate the attack bonus.
     */
    private boolean chargeReady = true;

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
        super(name, health, 20, 12);
    }

    /**
     * Returns boolean whether the charge ability is available or not.
     * The attack bonus will change as the cavalry unit have ont attacked a target before.
     *
     * @return value of the attack bonus for a cavalry unit.
     */
    @Override
    public int getAttackBonus() {
        int attackBonus = 2;

        if (chargeReady) {
            chargeReady = false;
            return attackBonus + 4;
        } else {
            return attackBonus;
        }
    }

    /**
     * Resets the charge ability for a cavalry unit when its opponent dies.
     * Used to indicate that the cavalry unit can use the charge ability again,
     * and against a new opponent at a distance.
     */
    public void resetChargeAbility() {
        this.chargeReady = true;
    }

    /**
     * Returns the resist bonus value.
     *
     * @return value of the resist bonus for a cavalry unit.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
