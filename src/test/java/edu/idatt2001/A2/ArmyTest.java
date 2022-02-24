package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ArmyTest {

    @Test
    void constructorInputParameterTestInputArrayList() {
        try {
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King",180);
            Unit testUnit2 = new InfantryUnit("Footman",100);
            Unit testUnit3 = new CavalryUnit("Knight",100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army testArmy = new Army("The Alliance", testArrayList);

            System.out.println("The list contain units:         " + testArmy.hasUnits());
            System.out.println("Number of units in the list:    " + testArmy.getAllUnits().size() + "\n");
            System.out.println("List of units in the ArrayList: " + testArmy.getAllUnits());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void instantiatingArmyClassAndSomeTestUnitsToBeAddedToTheArmy() {
        try {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new CommanderUnit("Gul'dan",180);
            Unit testUnit2 = new InfantryUnit("Grunt",100);
            Unit testUnit3 = new CavalryUnit("Raider",100);
            // Exception handling for method add(Unit unit) tested here.

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);

            System.out.println("The list contain units:         " + horde.hasUnits());
            System.out.println("Number of units in the list:    " + horde.getAllUnits().size() + "\n");
            System.out.println("List of units in the ArrayList: " + horde.getAllUnits());

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void hasUnitsMethodTest() {
        try {
            Army alliance = new Army("The Alliance");   // Simplified constructor.
            Unit testUnit = new CommanderUnit("Mountain King",180);
            System.out.println(alliance.hasUnits());    // Expects false.
            alliance.add(testUnit);
            System.out.println(alliance.hasUnits());    // Expects true.

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void testToSeeReturnOfHasUnitsMethodWhenUnitsHaveBeenRemoved() {
        try {
            Army horde = new Army("The Alliance");   // Simplified constructor.

            System.out.println("Expect false: " + horde.hasUnits());    // Expects false.

            // Adds x number of grunt units to the  horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new InfantryUnit("Grunt",100));
            }

            System.out.println("\n2 units in the army:");
            System.out.println("Expect true:  " + horde.hasUnits());    // Expects true.
            System.out.println(horde.getAllUnits().toString());
            System.out.println("Expect true:  " + horde.hasUnits());    // Expects true.

            System.out.println("\n1 unit in the army:");
            horde.remove(horde.getRandom());
            System.out.println(horde.getAllUnits().toString());
            System.out.println("Expect true:  " + horde.hasUnits());    // Expects true.

            System.out.println("\n0 units in the army:");
            horde.remove(horde.getRandom());
            System.out.println(horde.getAllUnits().toString());
            System.out.println("Expect false:  " + horde.hasUnits());    // Expects false.



        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void accessMethodsTestingReturns() {
        try {
            Army horde = new Army("The Horde");
            System.out.println("Name of the army is:    " + horde.getName());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void addAllUnitsMethodTest() {
        try {
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King",180);
            Unit testUnit2 = new InfantryUnit("Footman",100);
            Unit testUnit3 = new CavalryUnit("Knight",100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army alliance = new Army("The Alliance");   // Simplified constructor.

            System.out.println("\nUnits in list:                " + alliance.hasUnits());
            System.out.println("Number of units in the list:    " + alliance.getAllUnits().size());
            alliance.addAll(testArrayList);
            System.out.println("\nUnits in list:                " + alliance.hasUnits());
            System.out.println("Number of units in the list:    " + alliance.getAllUnits().size());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void removeASingleUnitFromUnitsList() {
        try {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt1",100);
            Unit testUnit2 = new InfantryUnit("Grunt2",100);
            Unit testUnit3 = new InfantryUnit("Grunt3",100);
            Unit testUnit4 = new InfantryUnit("Grunt4",100);

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);
            horde.add(testUnit4);

            System.out.println("The list contain units:         " + horde.hasUnits());
            System.out.println("Number of units in the list:    " + horde.getAllUnits().size() + "\n");
            System.out.println("List of units in the ArrayList: " + horde.getAllUnits());

            horde.remove(testUnit2);

            System.out.println("The list contain units:         " + horde.hasUnits());
            System.out.println("Number of units in the list:    " + horde.getAllUnits().size() + "\n");
            System.out.println("List of units in the ArrayList: " + horde.getAllUnits());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void getRandomWarriorFromAnArrayList() {
        try {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new CommanderUnit("Gul'dan",100);
            Unit testUnit2 = new CavalryUnit("Raider",100);
            Unit testUnit3 = new RangedUnit("Spearman",100);
            Unit testUnit4 = new InfantryUnit("Grunt",100);

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);
            horde.add(testUnit4);

            for (int i = 0; i < 20; i++) {
                System.out.println(horde.getRandom());
            }

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void getToStringFromArmyClass() {
        try {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new CommanderUnit("Gul'dan",180);
            Unit testUnit2 = new CavalryUnit("Raider",100);
            Unit testUnit3 = new RangedUnit("Spearman",100);
            Unit testUnit4 = new InfantryUnit("Grunt",100);

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);
            horde.add(testUnit4);

            System.out.println(horde.toString());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void equalsMethodTestedWithTwoArmies() {
        try {
            Army army1 = new Army("The Horde");
            Army army2 = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt",100);
            Unit testUnit2 = new InfantryUnit("Raider",100);

            army1.add(testUnit1);
            army1.add(testUnit2);
            army2.add(testUnit1);
            army2.add(testUnit2);

            System.out.println(army1.equals(army2));

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void hashCodeTestToSeeIfTheSameValueForAnObjectIsReturned() {
        try {
            Army army1 = new Army("The Horde");
            Army army2 = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt",100);
            Unit testUnit2 = new CavalryUnit("Raider",100);
            Unit testUnit3 = new RangedUnit("Spearman",100);
            Unit testUnit4 = new InfantryUnit("Grunt",100);
            Unit testUnit5 = new InfantryUnit("Grunt",100);
            Unit testUnit6 = new InfantryUnit("Grunt",100);

            army1.add(testUnit4);
            army1.add(testUnit5);
            army2.add(testUnit4);
            army2.add(testUnit5);
            army2.add(testUnit6);

            System.out.println(army1.hashCode());
            System.out.println(army1.hashCode());
            System.out.println(army2.hashCode());
            System.out.println(testUnit1.hashCode());
            System.out.println(testUnit2.hashCode());
            System.out.println(testUnit3.hashCode());
            System.out.println(testUnit4.hashCode());
            System.out.println(testUnit5.hashCode());
            System.out.println(testUnit6.hashCode());

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }



}
