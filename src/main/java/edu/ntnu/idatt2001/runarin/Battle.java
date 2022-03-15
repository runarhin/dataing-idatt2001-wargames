package edu.ntnu.idatt2001.runarin;

import edu.ntnu.idatt2001.runarin.units.Army;
import edu.ntnu.idatt2001.runarin.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.RangedUnit;
import edu.ntnu.idatt2001.runarin.units.Unit;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;

/**
 * A class Battle.
 * This class represents a battle between two armies.
 */
public class Battle {

    private Army armyOne;
    private Army armyTwo;

    /**
     * Constructor which instantiate a Battle between two armies.
     * @param armyOne   One out of two armies battling for survival.
     * @param armyTwo   Second out of two armies battling for survival.
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Method which simulates a battle between two armies.
     * The method randomises which warrior to attack first, to remove the advantage
     * of being the first army to strike. When a warrior dies, it is removed from the armies ArrayList.
     *
     * The battle is logged to the file BattleLog.txt.
     *
     * @return      The name of the winning army.
     */
    public Army simulate() {
        int roundCount = 0;
        int randomiseAttack;
        int attackedWarriorOldHealth;
        int attackedWarriorNewHealth;
        Random rand = new Random();

        // Creating a File object the battle log will be applied to.
        PrintStream o = null;
        try {
            o = new PrintStream("BattleLog.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // Store current System.out before assigning a new value
        PrintStream console = System.out;
        // Assign o to output stream. Every print from now until reset will go to a log-file.
        System.setOut(o);

        Unit warriorArmyOne = armyOne.getRandom();
        Unit warriorArmyTwo = armyTwo.getRandom();

        // Run as long as there is units left in both armies.
        while (armyOne.hasUnits() & armyTwo.hasUnits()) {

            roundCount++;
            System.out.println("\nRound " + roundCount + "");
            System.out.println("    " + warriorArmyOne.getName() + " [" + warriorArmyOne.getHealth() + " HP]"
            + " against "+ warriorArmyTwo.getName() + " [" + warriorArmyTwo.getHealth() + " HP]");

            // Randomises who attacks first.
            randomiseAttack = rand.nextInt(0, 2);
            if (randomiseAttack == 0) {
                if (warriorArmyOne.getHealth() > 0) {
                    attackedWarriorOldHealth = warriorArmyTwo.getHealth();
                    warriorArmyOne.attack(warriorArmyTwo);
                    attackedWarriorNewHealth = warriorArmyTwo.getHealth();
                    System.out.println("        "
                            + warriorArmyOne.getName() + " [" + warriorArmyOne.getHealth() + " HP]"
                            + " strikes and deals " + (attackedWarriorOldHealth - attackedWarriorNewHealth)
                            + " damage to " + warriorArmyTwo.getName() + " [" + attackedWarriorOldHealth
                            + " HP][-" + (attackedWarriorOldHealth - attackedWarriorNewHealth) + " HP]");
                }
                if (warriorArmyTwo.getHealth() > 0) {
                    attackedWarriorOldHealth = warriorArmyOne.getHealth();
                    warriorArmyTwo.attack(warriorArmyOne);
                    attackedWarriorNewHealth = warriorArmyOne.getHealth();
                    System.out.println("        "
                            + warriorArmyTwo.getName() + " [" + warriorArmyTwo.getHealth() + " HP]"
                            + " then deals " + (attackedWarriorOldHealth - attackedWarriorNewHealth)
                            + " damage to " + warriorArmyOne.getName() + " [" + attackedWarriorOldHealth
                            + " HP][-" + (attackedWarriorOldHealth - attackedWarriorNewHealth) + " HP]");
                }
            } else {
                if (warriorArmyTwo.getHealth() > 0) {
                    attackedWarriorOldHealth = warriorArmyOne.getHealth();
                    warriorArmyTwo.attack(warriorArmyOne);
                    attackedWarriorNewHealth = warriorArmyOne.getHealth();
                    System.out.println("        "
                            + warriorArmyTwo.getName() + " [" + warriorArmyTwo.getHealth() + " HP]"
                            + " strikes and deals " + (attackedWarriorOldHealth - attackedWarriorNewHealth)
                            + " damage to " + warriorArmyOne.getName() + " [" + attackedWarriorOldHealth
                            + " HP][-" + (attackedWarriorOldHealth - attackedWarriorNewHealth) + " HP]");
                }
                if (warriorArmyOne.getHealth() > 0) {
                    attackedWarriorOldHealth = warriorArmyTwo.getHealth();
                    warriorArmyOne.attack(warriorArmyTwo);
                    attackedWarriorNewHealth = warriorArmyTwo.getHealth();
                    System.out.println("        "
                            + warriorArmyOne.getName() + " [" + warriorArmyOne.getHealth() + " HP]"
                            + " then deals " + (attackedWarriorOldHealth - attackedWarriorNewHealth)
                            + " damage to " + warriorArmyTwo.getName() + " [" + attackedWarriorOldHealth
                            + " HP][-" + (attackedWarriorOldHealth - attackedWarriorNewHealth) + " HP]");
                }
            }
            // After an attack, see if a warrior died or not. If a warrior died, it is replaced.
            if (warriorArmyTwo.getHealth() <= 0) {
                System.out.println("\n    " + warriorArmyTwo.getName()
                        + " died of fatal blow from " + warriorArmyOne.getName() + "!");
                armyTwo.remove(warriorArmyTwo);
                if (armyTwo.hasUnits()) {
                    warriorArmyTwo = armyTwo.getRandom();
                }
                if (warriorArmyOne instanceof RangedUnit) {
                    ((RangedUnit) warriorArmyOne).resetAttacked();
                }
                if (warriorArmyOne instanceof CavalryUnit) {
                    ((CavalryUnit) warriorArmyOne).resetChargeAbility();
                }
            }
            if (warriorArmyOne.getHealth() <= 0) {
                System.out.println("\n    " + warriorArmyOne.getName()
                        + " died of fatal blow from " + warriorArmyTwo.getName() + "!");
                armyOne.remove(warriorArmyOne);
                if (armyOne.hasUnits()) {
                    warriorArmyOne = armyOne.getRandom();
                }
                if (warriorArmyTwo instanceof RangedUnit) {
                    ((RangedUnit) warriorArmyTwo).resetAttacked();
                }
                if (warriorArmyTwo instanceof CavalryUnit) {
                    ((CavalryUnit) warriorArmyTwo).resetChargeAbility();
                }
            }
        }

        if (!armyOne.hasUnits()) {
            System.out.println("\n  " + armyTwo.getName() + " wins the battle!"); // Add to log who the winner is.
            System.setOut(console);                                               // Restores output to console.
            return armyTwo;
        } else {
            System.out.println("\n  " + armyOne.getName() + " wins the battle!"); // Add to log who the winner is.
            System.setOut(console);                                               // Restores output to console.
            return armyOne;
        }
    }

    /**
     * toString-method which return the names of the two clashing armies.
     * @return      Names of the battling armies.
     */
    @Override
    public String toString() {
        return "Battle between " + armyOne + " and " + armyTwo + "";
    }
}
