package edu.ntnu.idatt2001.runarin.units;

import java.util.ArrayList;

import edu.ntnu.idatt2001.runarin.units.*;
import edu.ntnu.idatt2001.runarin.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.RangedUnit;
import org.junit.jupiter.api.Test;

public class ArmyTest {

    @Test
    void addAListOfUnitsToAnArmyByAddingAListOfUnitsInTheConstructorAndCheckIfTheyAreRegistered() {
        try {
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);

            Army testArmy = new Army("The Alliance", testArrayList);

            // The list contain units:  Expect true.
            assert(testArmy.hasUnits());

            // Number of units in the list:  Expects 2 units to be added.
            assert(testArmy.getAllUnits().size() == 2);

            // List of units in the ArrayList contains testUnit1 and 2, but not 3.
            assert(testArmy.getAllUnits().contains(testUnit1));
            assert(testArmy.getAllUnits().contains(testUnit2));
            assert(!testArmy.getAllUnits().contains(testUnit3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void createAListOfUnitsToTestTheAddAllUnitsMethodWithSimplifiedConstructor() {
        try {
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army alliance = new Army("The Alliance"); // Simplified constructor.

            // expects the army to have no units in it.
            assert(!alliance.hasUnits());
            assert(alliance.getAllUnits().size() == 0);

            alliance.addAll(testArrayList);

            // Expects now that the army have 3 units in it.
            assert(alliance.hasUnits());
            assert(alliance.getAllUnits().size() == 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void removeUnitsFromAnArmyAndSeeThatTheArmyUnitsListEventuallyGetsEmpty() {
        try {
            Army horde = new Army("The Alliance"); // Simplified constructor.
            assert(!horde.hasUnits());  // Expects that the Horde does not have any units yet.

            // Adds x number of grunt units to the  horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            assert(horde.getAllUnits().size() == 2);    // Expects that the army now have 2 units in it.

            // Removes 1 unit from the army:
            horde.remove(horde.getRandom());
            assert(horde.getAllUnits().size() == 1);    // Expects that the army now have 1 unit left.
            assert(!horde.getAllUnits().isEmpty());     // And that the list is not empty.

            // Removes the last unit in the army:
            horde.remove(horde.getRandom());
            assert(horde.getAllUnits().size() == 0);    // Expects that the army now have no units left.
            assert(horde.getAllUnits().isEmpty());      // And that the list now is empty.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void accessMethodGetNameReturnExpectedName() {
        try {
            Army horde = new Army("The Horde");

            // Add some examples which should and should not return valid result.
            assert(horde.getName().equals("The Horde"));
            assert(!horde.getName().equals("The horde"));
            assert(!horde.getName().equals("Horde"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    void removeASingleUnitFromUnitsList() {
        try {
            Army horde = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new InfantryUnit("Grunt", 100);
            Unit testUnit3 = new InfantryUnit("Grunt", 100);

            horde.add(testUnit1);
            horde.add(testUnit2);
            horde.add(testUnit3);

            assert(horde.getAllUnits().contains(testUnit1));
            assert(horde.getAllUnits().contains(testUnit2));
            assert(horde.getAllUnits().contains(testUnit3));

            horde.remove(testUnit2);

            assert(horde.getAllUnits().contains(testUnit1));
            assert(!horde.getAllUnits().contains(testUnit2));
            assert(horde.getAllUnits().contains(testUnit3));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getRandomWarriorFromAnArmy() {
        try {
            Army horde = new Army("The Horde");

            horde.add(new CommanderUnit("Gul'dan", 180));
            horde.add(new CavalryUnit("Raider", 100));
            horde.add(new RangedUnit("Spearman", 100));
            horde.add(new InfantryUnit("Grunt", 100));

            // Runs a loop to see if a unit seems to appear at random.
            for (int i = 0; i < 20; i++) {
                System.out.println(horde.getRandom().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void equalsMethodTestedWithTwoArmies() {
        try {
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

            assert(army1.equals(army2));    // Army 1 and 2 equals that their values are the same,
            assert(!(army1 == army2));      // but they are in reality two different armies.

            assert(!army1.equals(army3));   // Army 1 and Army 3 on the other hand are two different factions.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void hashCodeTestToSeeIfArmiesAndUnitsHaveDifferentHashCodeAndEvenIfSomeValuesAreTheSame() {
        try {
            Army army1 = new Army("The Horde");
            Army army2 = new Army("The Horde");
            Army army3 = new Army("The Alliance");

            assert(army1.hashCode() == army2.hashCode());   // army1 and army2 is saved on the same
                                                            // hashCode as they have the same name.
            assert(army1.hashCode() != army3.hashCode());   // army1 and army3 differs as they have different names.

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new CavalryUnit("Grunt", 100);
            Unit testUnit3 = new RangedUnit("Grunt", 100);

            army1.add(testUnit1);
            army1.add(testUnit2);
            army1.add(testUnit3);
            army1.add(new InfantryUnit("Grunt",100));
            army1.add(new InfantryUnit("Grunt",100));
            army1.add(new InfantryUnit("Grunt",100));

            assert(army1.getAllUnits().size() == 6);    // Expects there to be six units in army1.

            // Expects none of the units to have the same hashCode.
            for (int i = 0; i < army1.getAllUnits().size(); i++) {
                for (int j = 0; j < army1.getAllUnits().size(); j++) {
                    if (i == j) {
                        assert(army1.getAllUnits().get(i).hashCode() == army1.getAllUnits().get(j).hashCode());
                    } else {
                        assert(army1.getAllUnits().get(i).hashCode() != army1.getAllUnits().get(j).hashCode());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
