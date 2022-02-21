package edu.idatt2001.A2;

public abstract class Unit {

    private String name;
    private int health;     // Health points to indicate the warrior's health. Can not be below zero.
    private int attack;
    private int armor;

    /**
     * Constructor to the class Unit.
     * Abstract class which will not be used for instantiation.
     * Unit functions as a superclass for more specialised classes of warriors.
     * @param name      Description of the type of warrior; Archer, Swordsman, etc.
     * @param health    Number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack    Attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor     Armor points which decreases the damage taken from an opponent.
     */
    public Unit(String name, int health, int attack, int armor) {
        this.name = name;
        if (health <= 0) {
            throw new IllegalArgumentException("Health points for a warrior must be above zero.");
        }
        else {
            this.health = health;
        }
        this.attack = attack + getAttackBonus();
        this.armor = armor + getResistBonus();
    }

    /**
     * Method that makes the warrior attack an opponent.
     * The opponent will lose health point when attacked, based on armor and attack point.
     * @param opponent      The opponent that loses health points when attacked.
     */
    public void attack(Unit opponent) {
        int healthOpponent = opponent.getHealth();
        int newHealth;
        if (healthOpponent > 0) {
            newHealth = opponent.getHealth()
                    - (this.attack + this.getAttackBonus()) + (opponent.getArmor() + opponent.getResistBonus());

            opponent.setHealth(newHealth);
        }
    }

    /**
     * Get-method that returns the type of warrior
     * @return  String description of warrior type.
     */
    public String getName() {
        return name;
    }

    /**
     * Get-method that returns the remaining health points of the warrior.
     * @return  Int value of health points for a warrior.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get-method that returns attack point value for a warrior.
     * @return  Int value of attack points.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Get-method that returns the armor point value for a warrior.
     * @return  Int value of armor points.
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Set-method which reduces the amount of health points for the warrior when it is attacked.
     * Values below zero will be corrected to zero.
     * @param newHealth    Number of health points to be reduced for the warrior.
     */
    public void setHealth(int newHealth) {
        if (newHealth < 0) {
            newHealth = 0;
        }
        this.health = newHealth;
    }

    /**
     * To-string-method which returns the status of a warrior.
     * @return  String status of a warrior.
     */
    @Override
    public String toString() {
        return "Unit: " + name + " | HP = " + health + " | Attack power = " + attack
                + " | Armor points = " + armor + " |";
    }

    /**
     * Abstract method to be specified in a subclass to the superclass Unit.
     * Will return int value to be used for calculating damage to an opponent.
     */
    public abstract int getAttackBonus();

    /**
     * Abstract method to be specified in a subclass to the superclass Unit.
     * Will return int value to be used for calculating damage from an opponent.
     */
    public abstract int getResistBonus();
}
