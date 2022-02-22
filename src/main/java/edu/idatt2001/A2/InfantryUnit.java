package edu.idatt2001.A2;

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
        super.name = name;
        super.health = health;
        super.attack = attack;
        super.armor = armor;
    }

    /**
     * Second constructor for instantiation of the InfantryUnit class.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public InfantryUnit(String name, int health) {
        super(name, health);
        super.name = name;
        super.health = health;
        super.attack = 15;
        super.armor = 10;
    }

    /**
     * Method that returns the attack bonus value.
     * This value is used in the parent class method attack(Unit opponent).
     * @return  int value of the attack bonus for an infantry unit.
     */
    @Override
    public int getAttackBonus() {
        return 2;
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
