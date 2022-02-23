package edu.idatt2001.A2;

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
     *
     * @return
     */
    public Army simulate() {
        while (armyOne.hasUnits() || armyTwo.hasUnits()) {

            Unit warriorArmyOne = armyOne.getRandom();
            Unit warriorArmyTwo = armyTwo.getRandom();

            while ((warriorArmyOne.getHealth() == 0) || (warriorArmyTwo.getHealth() == 0)) {
                warriorArmyOne.attack(warriorArmyTwo);
                warriorArmyTwo.attack(warriorArmyOne);

                System.out.println("warriorArmyOne.getHealth: " + warriorArmyOne.getHealth());
                System.out.println("warriorArmyTwo.getHealth: " + warriorArmyTwo.getHealth());

                if (warriorArmyOne.getHealth() == 0) {
                    armyOne.remove(warriorArmyOne);
                }
                else if (warriorArmyTwo.getHealth() == 0) {
                    armyOne.remove(warriorArmyTwo);
                }
            }
        }

        if (!armyTwo.hasUnits()) {
            return armyOne;
        }
        else {
            return armyTwo;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Battle between " + armyOne + " and " + armyTwo + "!";
    }
}
