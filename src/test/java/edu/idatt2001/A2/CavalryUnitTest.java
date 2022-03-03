package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class CavalryUnitTest {

    @Test
    void createSomeUnitsAndReturnToString() {
        try {
            Unit cavalryTestUnit1 = new CavalryUnit("Knight", 100);
            System.out.println("\n" + cavalryTestUnit1.toString());

            CavalryUnit cavalryTestUnit2 = new CavalryUnit("Raider", 100);
            System.out.println("\n" + cavalryTestUnit2.toString());

            Unit cavalryTestUnit3 = new CavalryUnit("Epic Raider", 150, 22, 14);
            System.out.println("\n" + cavalryTestUnit3.toString());

            CavalryUnit cavalryTestUnit4 = new CavalryUnit("Epic Knight", 150, 22, 14);
            System.out.println("\n" + cavalryTestUnit4.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void twoOpponentsWereOneExcessivelyAttacksTheOther() {
        try {
            System.out.println("\nNotes, cavalryUnit have: ");
            System.out.println("    - Base attack of 20.");
            System.out.println("    - Base armor of 12.");
            System.out.println("    - Resist bonus of 1.");
            System.out.println("    - Should have attack bonus of 4+2 on the first attack and 2 on the second.");

            System.out.println("\n----------------------------------------------------------");
            Unit knight = new CavalryUnit("Knight", 100);
            Unit raider = new CavalryUnit("Raider", 100);

            System.out.println("\n0. Before the raider attacked the knight:");
            System.out.println("\n" + knight.toString());
            System.out.println("\n" + raider.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n1. After the raider attacked the knight:");
            System.out.println("Expect (100 HP - 20 - 6 + 12 + 1 =) 87 HP left for knight after first blow.");
            raider.attack(knight);
            System.out.println("\n" + knight.toString());
            System.out.println("\n" + raider.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n2. After the raider attacked the knight:");
            System.out.println("Expect (87 HP - 20 - 2 + 12 + 1 =) 78 HP left for knight after second blow.");
            raider.attack(knight);
            System.out.println("\n" + knight.toString());
            System.out.println("\n" + raider.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n3. After the raider attacked the knight:");
            System.out.println("Expect (78 HP - 20 - 2 + 12 + 1 =) 69 HP left for knight after third blow.");
            raider.attack(knight);
            System.out.println("\n" + knight.toString());
            System.out.println("\n" + raider.toString());
            System.out.println("\n----------------------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
