package edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised;

/**
 * A class for a commander unit.
 * This is a commander unit with high attack power.
 * Extends the CavalryUnit class and comes with a charge ability.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-17
 */
public class CommanderUnit extends CavalryUnit {

    private static final int BASE_ATTACK = 25;
    private static final int BASE_DEFENCE = 15;

    /**
     * Constructor for instantiation of the CommanderUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor armor points which decreases the damage taken from an opponent.
     */
    public CommanderUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the CommanderUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public CommanderUnit(String name, int health) {
        super(name, health, BASE_ATTACK, BASE_DEFENCE);
    }
}
