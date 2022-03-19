package edu.ntnu.idatt2001.runarin.battle.units.specialised;

import edu.ntnu.idatt2001.runarin.battle.units.Unit;

/**
 * A class for a cavalry unit.
 * This is a unit with relatively high attack power and also comes with a charge ability.
 */
public class CavalryUnit extends Unit {

    /**
     * chargeReady is used to determine if the warrior have attacked a target.
     * This to calculate the attack bonus.
     */
    private boolean chargeReady = true;

    /**
     * Constructor for instantiation of the CavalryUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack    Attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor     Armor points which decreases the damage taken from an opponent.
     */
    public CavalryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the CavalryUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public CavalryUnit(String name, int health) {
        super(name, health, 20, 12);
    }

    /**
     * Method that gives an indication whether the charge ability is available or not.
     * The attack bonus will change as the cavalry unit have ont attacked a target before.
     * @return      Value of the attack bonus for a cavalry unit.
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
     * Method which resets the charge ability for a cavalry unit when its opponent dies.
     * Used to indicate that the cavalry unit can use the charge ability again,
     * and against a new opponent at a distance.
     */
    public void resetChargeAbility() {
        this.chargeReady = true;
    }

    /**
     * Method that returns the resist bonus value.
     * @return      Value of the resist bonus for a cavalry unit.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
