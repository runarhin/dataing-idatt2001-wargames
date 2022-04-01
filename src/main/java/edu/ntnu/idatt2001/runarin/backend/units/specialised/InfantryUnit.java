package edu.ntnu.idatt2001.runarin.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.backend.units.Unit;

/**
 * A class for an infantry unit.
 * This is a basic melee unit with normal attack power and armor resistance.
 *
 * @author Runar Indahl
 * @version 1.0.1
 */
public class InfantryUnit extends Unit {

    /**
     * Constructor for instantiation of the InfantryUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack    Attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor     Armor points which decreases the damage taken from an opponent.
     */
    public InfantryUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the InfantryUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public InfantryUnit(String name, int health) {
        super(name, health,15,10);
    }

    /**
     * Method that returns the attack bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * @return      Value of the attack bonus for an infantry unit.
     */
    @Override
    public int getAttackBonus() {
        return 2;
    }

    /**
     * Method that returns the resist bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * @return      Value of the resist bonus for an infantry unit.
     */
    @Override
    public int getResistBonus() {
        return 1;
    }
}
