package edu.ntnu.idatt2001.runarin.units.specialised;

/**
 * A class for a commander unit.
 * This is a commander unit with high attack power and also comes a charge ability.
 * Inherited by CavalryUnit class.
 */
public class CommanderUnit extends CavalryUnit {

    /**
     * Constructor for instantiation of the CommanderUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack    Attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor     Armor points which decreases the damage taken from an opponent.
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the CommanderUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public CommanderUnit(String name, int health) {
        super(name, health,25,15);
    }
}
