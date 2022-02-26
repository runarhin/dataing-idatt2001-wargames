package edu.idatt2001.A2;

/**
 * A class for a ranged unit.
 * This is a basic ranged unit with normal attack power and armor resistance.
 * It has a benefit of attacking from a range, and therefore takes less damage the first two times it is attacked.
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
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack    Attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor     Armor points which decreases the damage taken from an opponent.
     */
    public RangedUnit(String name, int health, int attack, int armor) {
        super(name, health, attack, armor);
    }
    /**
     * Second constructor for instantiation of the RangedUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public RangedUnit(String name, int health) {
        super(name, health);
        super.attack = 15;
        super.armor = 8;
    }
    /**
     * Method that returns the resist bonus value.
     * The resist bonus will change as the range between the attacking and the attacked unit decreases.
     * This value is used in the parent class method attack(Unit opponent) for the attacking unit.
     * @return  int value of the resist bonus for a damage receiving unit.
     */
    @Override
    public int getResistBonus() {

        if (this.health < lastHealth) {
            attacked++;
            lastHealth = this.health;
        }

        if (attacked == 0) {
            return 6;
        }
        else if (attacked == 1) {
            return 4;
        }
        else {
            return 2;
        }
        //TODO: Reset attacked if opponent dies?
        // Alternative feature:
        // getResistBonus(Unit someAttackingUnit) {if (someAttackingUnit.getHealth == 0) {attacked = 0;}}
    }

    /**
     * Method that returns the attack bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * @return  int value of the attack bonus for an infantry unit.
     */
    @Override
    public int getAttackBonus() {
        return 3;
    }

}
