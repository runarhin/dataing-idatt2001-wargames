package edu.ntnu.idatt2001.runarin.units.specialised;

import edu.ntnu.idatt2001.runarin.units.Unit;

/**
 * A class for a cavalry unit.
 * This is a unit with relatively high attack power and also comes with a charge ability.
 */
public class CavalryUnit extends Unit {

    /**
     * attacks is used to determine how many times the warrior have attacked a target.
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
     * Method that returns the attack bonus value.
     * The attack bonus will change as the cavalry unit excessively attacks.
     * @return  int value of the attack bonus for an infantry unit.
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
     * Method which resets the attacks given by a cavalry unit when its opponent dies.
     * Used to indicate that the cavalry unit can use the charge ability again,
     * and against a new opponent at a distance.
     */
    public void resetChargeAbility() {
        this.chargeReady = true;
    }

    /**
     * Method that returns the resist bonus value.
     * This value is used in the parent class method attack(Unit opponent) for the attacking unit.
     * @return  int value of the resist bonus for a damage receiving unit.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
