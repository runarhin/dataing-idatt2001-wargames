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
        void addListOfUnitsWithAddAllMethod() {
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army alliance = new Army("The Alliance"); // Simplified constructor.

            alliance.addAll(testArrayList);

            // Expects now that the army have 3 units in it.
            assertEquals(alliance.getAllUnits().size(), 3);
        }

        @Test
        void removeUnitsFromAnArmyAndSeeThatTheUnitListEventuallyGetsEmpty() {
            Army horde = new Army("The Horde"); // Simplified constructor.

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
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

        @Test
        void accessMethodGetNameReturnExpectedName() {
            Army horde = new Army("The Horde");
            assertEquals(horde.getName(), "The Horde");
        }

        @Test
        void removeASingleUnitFromUnitsList() {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new InfantryUnit("Grunt", 100);
            Unit testUnit3 = new InfantryUnit("Grunt", 100);

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);

            horde.remove(testUnit2);

            assertEquals(horde.getAllUnits().size(), 2);
            assertFalse(horde.getAllUnits().contains(testUnit2));
        }
    }

    @Test
    void equalsMethodTestedWithTwoArmies() {
        Army army1 = new Army("The Horde");
        Army army2 = new Army("The Horde");
        Army army3 = new Army("The Alliance");

        Unit testUnit1 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit2 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit3 = new InfantryUnit("SomeUnit", 100);

        army1.add(testUnit1);
        army1.add(testUnit2);

        army2.add(testUnit1);
        army2.add(testUnit2);

        army3.add(testUnit3);

        assertEquals(army1,army2);              // Army 1 and 2 equals that their values are the same,
        assertNotSame(army1, army2);   // but they are in reality two different armies.

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

        army.add(new InfantryUnit("SomeUnit",100));
        army.add(new InfantryUnit("SomeUnit",100));
        army.add(new InfantryUnit("SomeUnit",100));
        army.add(new InfantryUnit("SomeUnit",100));
        army.add(new InfantryUnit("SomeUnit",100));
        army.add(new InfantryUnit("SomeUnit",100));

        // Expects there to be six units in the army.
        assertEquals(army.getAllUnits().size(), 6);

        // Expects none of the units to have the same hashCode.
        for (int i = 0; i < army.getAllUnits().size(); i++) {
            for (int j = 0; j < army.getAllUnits().size(); j++) {
                if (i == j) {
                    assertEquals(army.getAllUnits().get(i).hashCode(), army.getAllUnits().get(j).hashCode());
                } else {
                    assertNotEquals(army.getAllUnits().get(i).hashCode(), army.getAllUnits().get(j).hashCode());
                }
            }
        }
    }
}
