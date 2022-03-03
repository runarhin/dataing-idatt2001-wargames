package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class BattleTest {

    @Test
    void equalsMethodTestingIfItReturnExpectedResult() {
        try {
            Army army1 = new Army("The Horde");
            Army army2 = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new InfantryUnit("Raider", 100);

            army1.add(testUnit1);
            army1.add(testUnit2);
            army2.add(testUnit1);
            army2.add(testUnit2);

            System.out.println(army1.equals(army2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void toStringTestToSeeIfTheOutcomeMakesSense() {
        try {
            Army horde = new Army("The Horde");
            Army alliance = new Army("The Alliance");

            Battle grandWar = new Battle(horde, alliance);

            // Adds x number of grunt units to the  horde army.
            for (int i = 0; i < 3; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.add(new InfantryUnit("Footman", 100));
            }

            System.out.println(grandWar.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void simulationMethodTest() {
        try {
            Army horde = new Army("The Horde");
            Army alliance = new Army("The Alliance");

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 2; i++) {
                alliance.add(new InfantryUnit("Footman", 100));
            }

            Battle grandWar = new Battle(horde, alliance);

            System.out.println("\n" + grandWar.simulate() + " won the battle!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void someWarGame() {
        try {
            Army horde = new Army("The Orcish Horde");
            Army alliance = new Army("The Human Army");

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 3; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.add(new InfantryUnit("Footman", 100));
            }
            // Adds x number of raider units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new CavalryUnit("Raider", 100));
            }
            // Adds x number of knight units to the alliance army.
            for (int i = 0; i < 2; i++) {
                alliance.add(new CavalryUnit("Knight", 100));
            }
            // Adds commanders to the respective armies.
            horde.add(new CavalryUnit("Gul'dan", 180));
            alliance.add(new CavalryUnit("Mountain King", 180));

            Battle grandWar = new Battle(horde, alliance);

            System.out.println("\n" + grandWar.simulate() + " won the battle!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    @Test
    void someTest() {
        try {
            // This is some test.

        } catch (Exception e) {
            System.out.println(e);
        }
    }
     */

}
