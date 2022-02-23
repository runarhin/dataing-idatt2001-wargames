package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class ArmyTest {

    @Test
    void constructorInputParameterNeedsArrayList() {
        try {

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void instantiatingArmyClassAndSomeTestUnitsToBeEnlistedToTheArmy() {
        try {
            Army alliance = new Army("The Alliance");

            Unit testUnit1 = new CommanderUnit("Mountain King",180);
            Unit testUnit2 = new InfantryUnit("Footman",100);
            Unit testUnit3 = new CavalryUnit("Knight",100);

            alliance.add(testUnit1);
            alliance.add(testUnit2);
            alliance.add(testUnit3);





        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void hasUnitsMethodTest() {
        try {
            Army alliance = new Army("The Alliance");
            Unit testUnit = new CommanderUnit("Mountain King",180);
            System.out.println(alliance.hasUnits());    // Expects false.
            alliance.add(testUnit);
            System.out.println(alliance.hasUnits());    // Expects true.

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }





    @Test
    void someTest() {
        try {

        } catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }



}
