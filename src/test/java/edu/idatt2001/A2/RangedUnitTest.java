package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class RangedUnitTest {

    @Test
    void createSomeUnitsAndReturnToString() {
        try {
            Unit testUnit1 = new RangedUnit("Archer",100);
            System.out.println("\n" + testUnit1.toString());

            Unit testUnit2 = new RangedUnit("Spearman",100);
            System.out.println("\n" + testUnit2.toString());

            RangedUnit testUnit3 = new RangedUnit("Epic Archer",150,17,10);
            System.out.println("\n" + testUnit3.toString());

            RangedUnit testUnit4 = new RangedUnit("Epic Spearman",150,17,10);
            System.out.println("\n" + testUnit4.toString());

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void twoOpponentsWereOneExcessivelyAttacksTheOther() {
        try {
            System.out.println("\nNotes, RangedUnit have: ");
            System.out.println("    - Base attack of 15.");
            System.out.println("    - Base armor of 8.");
            System.out.println("    - Resist bonus depends on the range between units. 6 when far away, " +
                    "4 when closer and 2 when up close.");
            System.out.println("    - Attack bonus of 3.");

            System.out.println("\n----------------------------------------------------------");
            Unit archer = new RangedUnit("Archer", 100);
            Unit spearman = new RangedUnit("Spearman", 100);

            System.out.println("\n0. Before the spearman attacked the archer:");
            System.out.println("\n" + archer.toString());
            System.out.println("\n" + spearman.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n1. After the spearman attacked the archer:");
            System.out.println("Expect (100 HP - 15 - 3 + 8 + 6 =) 96 HP left for footman after first blow.");
            spearman.attack(archer);
            System.out.println("\n" + archer.toString());
            System.out.println("\n" + spearman.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n2. After the spearman attacked the archer:");
            System.out.println("Expect (96 HP - 15 - 3 + 8 + 4 =) 90 HP left for footman after second blow.");
            spearman.attack(archer);
            System.out.println("\n" + archer.toString());
            System.out.println("\n" + spearman.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n3. After the spearman attacked the archer:");
            System.out.println("Expect (90 HP - 15 - 3 + 8 + 2 =) 82 HP left for footman after third blow.");
            spearman.attack(archer);
            System.out.println("\n" + archer.toString());
            System.out.println("\n" + spearman.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n4. After the spearman attacked the archer:");
            System.out.println("Expect (82 HP - 15 - 3 + 8 + 2 =) 74 HP left for footman after fourth blow.");
            spearman.attack(archer);
            System.out.println("\n" + archer.toString());
            System.out.println("\n" + spearman.toString());
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
