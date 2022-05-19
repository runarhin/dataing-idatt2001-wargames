package edu.ntnu.idatt2001.runarin.backend.units;

/**
 * Abstract superclass Unit provides general capabilities to its subclasses.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-19
 */
public abstract class Unit {

    protected final String name;
    protected int health;           // Health points to indicate the warrior's health. Cannot be below zero.
    protected int attack;
    protected int armor;

    /**
     * Constructor for class Unit.
     * Unit functions as a superclass for more specialised classes of warriors.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     * @param attack attack points the warrior inflicts to an opponent (weapon damage).
     * @param armor armor points which decreases the damage taken from an opponent.
     */
    public Unit(String name, int health, int attack, int armor) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Name of the warrior cannot be empty.");
        if (health <= 0) throw new IllegalArgumentException("Health points of the warrior must be above zero.");
        if (attack <= 0) throw new IllegalArgumentException("Attack power of the warrior must be above zero.");
        if (armor < 0) throw new IllegalArgumentException("Armor points of the warrior cannot be below zero.");

        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armor = armor;
    }

    /**
     * Simplified constructor for class Unit.
     * Unit functions as a superclass for more specialised classes of warriors.
     *
     * @param name description of the type of warrior; Archer, Swordsman, etc.
     * @param health number of remaining health points for the warrior. Value is decreased when taking damage.
     */
    public Unit(String name, int health) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Name of the warrior cannot be empty.");
        if (health <= 0) throw new IllegalArgumentException("Health points of the warrior must be above zero.");

        this.name = name;
        this.health = health;
    }

    /**
     * Warrior attacks an opponent. The opponent will lose health points when attacked,
     * based on the defenders armor and the attackers attack power.
     *
     * @param opponent opponent that loses health points when attacked.
     * @param terrain deters the terrain the unit is fighting on.
     */
    public void attack(Unit opponent, TerrainType terrain) {
        int healthOpponent = opponent.getHealth();
        int newHealth;
        if (healthOpponent > 0) {
            newHealth =
                opponent.getHealth() -
                (this.getAttack() + this.getAttackBonus(terrain)) +
                (opponent.getArmor() + opponent.getResistBonus(terrain));

            opponent.setHealth(newHealth);
        }
    }

    /**
     * Return type of warrior.
     *
     * @return description of warrior type.
     */
    public String getName() {
        return name;
    }

    /**
     * Return remaining health of the warrior.
     *
     * @return int value of health for a warrior.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Return attack value for a warrior.
     *
     * @return int value of attack.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Return armor value for a warrior.
     *
     * @return int value of armor.
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Reduces the amount of health for the warrior when it is attacked.
     * Values below zero will be corrected to zero.
     *
     * @param newHealth number of health points to be reduced for the warrior.
     */
    public void setHealth(int newHealth) {
        if (newHealth <= 0) {
            newHealth = 0;
        }
        this.health = newHealth;
    }

    /**
     * Return unit type, name and health status of a warrior in toString-format.
     *
     * @return status of a warrior.
     */
    @Override
    public String toString() {
        return (
            "\n" + name + "  [" + health + " hp]  " + getClass().getSimpleName()
        );
    }

    /**
     * Abstract method to be specified in a subclass to the superclass Unit.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return int value to be used for calculating damage to an opponent.
     */
    public abstract int getAttackBonus(TerrainType terrain);

    /**
     * Abstract method to be specified in a subclass to the superclass Unit.
     *
     * @param terrain deters the terrain the unit is fighting on.
     * @return int value to be used for calculating damage from an opponent.
     */
    public abstract int getResistBonus(TerrainType terrain);
}
