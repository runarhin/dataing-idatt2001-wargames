package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class InfantryUnitTest {

    @Test
    void createSomeUnitsAndReturnToString() {
        try {
            Unit infantryTestUnit1 = new InfantryUnit("Footman",100);
            System.out.println("\n" + infantryTestUnit1.toString());

            Unit infantryTestUnit2 = new InfantryUnit("Grunt",100);
            System.out.println("\n" + infantryTestUnit2.toString());

            InfantryUnit infantryTestUnit3 = new InfantryUnit("Epic Knight",150,17,12);
            System.out.println("\n" + infantryTestUnit3.toString());

            InfantryUnit infantryTestUnit4 = new InfantryUnit("Epic Grunt",150,17,12);
            System.out.println("\n" + infantryTestUnit4.toString());

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void twoOpponentsWereOneExcessivelyAttacksTheOther() {
        try {
            System.out.println("\nNotes, InfantryUnit have: ");
            System.out.println("    - Base attack of 15.");
            System.out.println("    - Base armor of 10.");
            System.out.println("    - Resist bonus of 1.");
            System.out.println("    - Attack bonus of 2.");

            System.out.println("\n----------------------------------------------------------");
            Unit footman = new InfantryUnit("Footman", 100);
            Unit grunt = new InfantryUnit("Grunt", 100);

            System.out.println("\n0. Before the grunt attacked the footman:");
            System.out.println("\n" + footman.toString());
            System.out.println("\n" + grunt.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n1. After the grunt attacked the footman:");
            System.out.println("Expect (100 HP - 15 - 2 + 10 + 1 =) 94 HP left for footman after first blow.");
            grunt.attack(footman);
            System.out.println("\n" + footman.toString());
            System.out.println("\n" + grunt.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n2. After the grunt attacked the footman:");
            System.out.println("Expect (94 HP - 15 - 2 + 10 + 1 =) 88 HP left for footman after second blow.");
            grunt.attack(footman);
            System.out.println("\n" + footman.toString());
            System.out.println("\n" + grunt.toString());
            System.out.println("\n----------------------------------------------------------");

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }



    @Test
    void someTest() {
        try {

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }
}
