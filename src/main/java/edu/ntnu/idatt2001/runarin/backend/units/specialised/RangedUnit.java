package edu.ntnu.idatt2001.runarin.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.backend.units.Unit;

/**
 * A class for a ranged unit.
 * This is a basic ranged unit with normal attack power and armor resistance.
 * It has a benefit of attacking from a range, and therefore takes less damage the first two times it is attacked.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-04-03
 */
public class RangedUnit extends Unit {

    /**
     * lastHealth and attacked is used to determine how many times the warrior have been attacked.
     * This to calculate the resist bonus.
     */
    private int lastHealth = this.health;
    private int attacked = 0;

    /**
     * Constructor for instantiation of the RangedUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor armor points which decreases the damage taken from an opponent.
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }

    /**
     * Simplified constructor for instantiation of the RangedUnit class.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public RangedUnit(String name, int health) {
        super(name, health,15,8);
    }

    /**
     * Returns the attack bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     *
     * @return value of the attack bonus for a ranged unit.
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

    /**
     * Returns the resist bonus value.
     * The resist bonus will change as the range between the attacking and the attacked unit decreases.
     * This value is used in the parent class method attack(Unit opponent) for the attacking unit.
     *
     * @return value of the resist bonus for a ranged unit.
     */
    @Override
    public int getResistBonus() {
        if (this.health < lastHealth) {
            attacked++;
            lastHealth = this.health;
        }
        if (attacked == 0) {
            return 6;
        } else if (attacked == 1) {
            return 4;
        } else {
            return 2;
        }
    }

    /**
     * Resets that the ranged unit is attacked.
     * Used to indicate that the ranged unit have killed its resent opponent and refreshes
     * its range to the new attacking opponent.
     */
    public void resetAttacked() {
        this.attacked = 0;
    }
}
