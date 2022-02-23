package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class BattleTest {

    @Test
    void someTest() {
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





}
