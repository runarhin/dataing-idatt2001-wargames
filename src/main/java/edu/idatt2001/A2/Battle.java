package edu.idatt2001.A2;

import java.util.Random;

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
     * of being the first army. When a warrior dies, it is removed from the armies ArrayList.
     * @return      The winning army.
     */
    public Army simulate() {

        int roundCount = 0;
        int randomiseAttack;
        Random rand = new Random();

        Unit warriorArmyOne = armyOne.getRandom();
        Unit warriorArmyTwo = armyTwo.getRandom();

        // Run as long as there is units left in both armies.
        while (armyOne.hasUnits() & armyTwo.hasUnits()) {

            // Print for test purposes.
            roundCount++;
            System.out.println("\nRound " + roundCount);
            System.out.println("ArmyOne: " + warriorArmyOne.getName()
                    + " have " + warriorArmyOne.getHealth() + " HP");
            System.out.println("ArmyTwo: " + warriorArmyTwo.getName()
                    + " have " + warriorArmyTwo.getHealth() + " HP");

            // Randomises who attack first.
            randomiseAttack = rand.nextInt(0,2);
            if (randomiseAttack == 0) {
                if (warriorArmyOne.getHealth() > 0) {
                    warriorArmyOne.attack(warriorArmyTwo);
                }
                if (warriorArmyTwo.getHealth() > 0) {
                    warriorArmyTwo.attack(warriorArmyOne);
                }
            }
            else {
                if (warriorArmyTwo.getHealth() > 0) {
                    warriorArmyTwo.attack(warriorArmyOne);
                }
                if (warriorArmyOne.getHealth() > 0){
                    warriorArmyOne.attack(warriorArmyTwo);
                }
            }
            // Cleans up the mess and see if a warrior died or not. If a warrior died, it is replaced.
            if (warriorArmyTwo.getHealth() == 0) {
                System.out.println(warriorArmyTwo.getName() + " died of fatal blow from "
                        + warriorArmyOne.getName() + "");
                armyTwo.remove(warriorArmyTwo);
                if (armyTwo.hasUnits()) {
                    warriorArmyTwo = armyTwo.getRandom();
                }
            }
            if (warriorArmyOne.getHealth() == 0) {
                System.out.println(warriorArmyOne.getName() + " died of fatal blow from "
                        + warriorArmyTwo.getName() + "");
                armyOne.remove(warriorArmyOne);
                if (armyOne.hasUnits()) {
                    warriorArmyOne = armyOne.getRandom();
                }
            }
        }
        if (!armyOne.hasUnits()) {
            return armyTwo;
        }
        else {
            return armyOne;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Battle between " + armyOne + " and " + armyTwo + "";
    }
}
