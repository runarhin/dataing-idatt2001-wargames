package edu.ntnu.idatt2001.runarin.units;

import java.util.ArrayList;

import edu.ntnu.idatt2001.runarin.units.specialised.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArmyTest {

    @Nested
    class addUnitsToAnArmy {

        @Test
        void addAListOfUnitsToAnArmyByAddingAListOfUnitsInTheConstructor() {
            /*
            This test adds units to an army and asserts that the right amount of
            units are in the list.
             */
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);

            Army testArmy = new Army("The Alliance", testArrayList);

            // The list contain units:  Expect true.
            assertTrue(testArmy.hasUnits());

            // Number of units in the list:  Expects 2 units to be added and not 1 or 3.
            assertEquals(testArmy.getAllUnits().size(), 2);
            assertNotEquals(testArmy.getAllUnits().size(), 1);
            assertNotEquals(testArmy.getAllUnits().size(), 3);
        }

        @Test
        void addListOfUnitsWithAddUnitsFromListMethod() {
            /*
            This test asserts the method addUnitsFromList by checking the number of units in the army.
             */
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army alliance = new Army("The Alliance"); // Simplified constructor.

            alliance.addUnitsFromList(testArrayList);

            // Expects now that the army have 3 units in it.
            assertEquals(alliance.getAllUnits().size(), 3);
        }

        @Test
        void removeASingleUnitFromUnitsList() {
            /*
            This test asserts that only a single unit is removed from the army.
             */
            Army horde = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new InfantryUnit("Grunt", 100);
            Unit testUnit3 = new InfantryUnit("Grunt", 100);

            horde.addUnit(testUnit1);
            horde.addUnit(testUnit2);
            horde.addUnit(testUnit3);

            horde.remove(testUnit2);

            assertEquals(horde.getAllUnits().size(), 2);
            assertFalse(horde.getAllUnits().contains(testUnit2));
        }

        @Test
        void removeUnitsFromAnArmyAndSeeThatTheUnitListEventuallyGetsEmpty() {
            /*
            This test removes a unit from a list one at the time and asserts that the number of units decreases.
             */
            Army horde = new Army("The Horde"); // Simplified constructor.

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.addUnit(new InfantryUnit("Grunt", 100));
            }
            assertEquals(horde.getAllUnits().size(), 2);     // Expects that the army now have 2 units in it.

            // Removes 1 unit from the army:
            horde.remove(horde.getRandom());
            assertEquals(horde.getAllUnits().size(), 1);    // Expects that the army now have 1 unit left.
            assertFalse(horde.getAllUnits().isEmpty());            // And that the list is not empty.

            // Removes the last unit in the army:
            horde.remove(horde.getRandom());
            assertEquals(horde.getAllUnits().size(), 0);    // Expects that the army now have no units left.
            assertTrue(horde.getAllUnits().isEmpty());             // And that the list now is empty.
        }
    }

    @Test
    void accessMethodGetNameReturnExpectedName() {
        Army horde = new Army("The Horde");
        assertEquals(horde.getName(), "The Horde");
    }

    @Test
    void throwsIllegalArgumentExceptionIfArmyNameIsBlank() {
        try {
            Army army = new Army("  ");
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "Army name cannot be empty");
        }
    }

    @Test
    void methodGetAllUnitsReturnAllTypesOfUnitsToTheDeepCopiedList() {
        /*
        This test first adds units to the army list and then checks that all units are returned to
        the deep copied list.
         */
        Army horde = new Army("The Horde");
        horde.addUnit(new InfantryUnit("Grunt", 100));
        horde.addUnit(new RangedUnit("Spearman", 100));
        horde.addUnit(new CavalryUnit("Raider", 100));
        horde.addUnit(new CommanderUnit("Gul'dan", 100));

        ArrayList<Unit> testArrayList = horde.getAllUnits();

        assertEquals(testArrayList.get(0).toString(),
                "\n| Grunt | HP = 100 | Attack power = 15 | Armor points = 10 |");
        assertEquals(testArrayList.get(1).toString(),
                "\n| Spearman | HP = 100 | Attack power = 15 | Armor points = 8 |");
        assertEquals(testArrayList.get(2).toString(),
                "\n| Raider | HP = 100 | Attack power = 20 | Armor points = 12 |");
        assertEquals(testArrayList.get(3).toString(),
                "\n| Gul'dan | HP = 100 | Attack power = 25 | Armor points = 15 |");
    }

    @Test
    void equalsMethodTestedWithTwoArmies() {
        Army army1 = new Army("The Horde");
        Army army2 = new Army("The Horde");
        Army army3 = new Army("The Alliance");

        Unit testUnit1 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit2 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit3 = new InfantryUnit("SomeUnit", 100);

        army1.addUnit(testUnit1);
        army1.addUnit(testUnit2);

        army2.addUnit(testUnit1);
        army2.addUnit(testUnit2);

        army3.addUnit(testUnit3);

        assertEquals(army1,army2);              // Army 1 and 2 equals that their values are the same,
        assertNotSame(army1, army2);            // but they are in reality two different armies.

        assertNotEquals(army1,army3);           // Army 1 and Army 3 differs on value and being different armies.
    }

    @Test
    void hashCodeIsDifferentBetweenArmies() {
        Army army1 = new Army("The Horde");
        Army army2 = new Army("The Horde");
        Army army3 = new Army("The Alliance");

        // army1 and army2 is saved on the same hashCode as they have the same name.
        assertEquals(army1.hashCode(), army2.hashCode());

        // army1 and army3 differs as they have different names.
        assertNotEquals(army1.hashCode(), army3.hashCode());
    }

    @Test
    void hashCodeIsDifferentForANumberOfUnitsInAnArmy() {
        Army army = new Army("The Horde");

        army.addUnit(new InfantryUnit("SomeUnit",100));
        army.addUnit(new InfantryUnit("SomeUnit",100));
        army.addUnit(new InfantryUnit("SomeUnit",100));
        army.addUnit(new InfantryUnit("SomeUnit",100));
        army.addUnit(new InfantryUnit("SomeUnit",100));
        army.addUnit(new InfantryUnit("SomeUnit",100));

        // Expects there to be six units in the army.
        assertEquals(army.getAllUnits().size(), 6);

        ArrayList<Unit> armyList = army.getAllUnits();

        // Expects none of the units to have the same hashCode.
        for (int i = 0; i < army.getAllUnits().size(); i++) {
            for (int j = 0; j < army.getAllUnits().size(); j++) {
                if (i == j) {
                    assertEquals(armyList.get(i).hashCode(), armyList.get(j).hashCode());
                } else {
                    assertNotEquals(armyList.get(i).hashCode(), armyList.get(j).hashCode());
                }
            }
        }
    }
}
