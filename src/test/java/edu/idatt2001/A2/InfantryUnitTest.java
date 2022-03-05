package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class InfantryUnitTest {

    @Test
    void createSomeUnitsAndReturnToString() {
        try {
            Unit infantryTestUnit1 = new InfantryUnit("Footman", 100);
            System.out.println("\n" + infantryTestUnit1.toString());

            Unit infantryTestUnit2 = new InfantryUnit("Grunt", 100);
            System.out.println("\n" + infantryTestUnit2.toString());

            InfantryUnit infantryTestUnit3 = new InfantryUnit("Epic Knight", 150, 17, 12);
            System.out.println("\n" + infantryTestUnit3.toString());

            InfantryUnit infantryTestUnit4 = new InfantryUnit("Epic Grunt", 150, 17, 12);
            System.out.println("\n" + infantryTestUnit4.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void oneInfantryUnitExcessivelyAttacksAnotherToSeeHealthOutcomeOfTheAttackedUnit() {
        try {
            /* Notes, InfantryUnit have:
                - Base attack of 15
                - Base armor of 10
                - Resist bonus of 1
                - Attack bonus of 2    */

            Unit footman = new InfantryUnit("Footman", 100);
            Unit grunt = new InfantryUnit("Grunt", 100);

            // 0. Before the grunt attacked the footman:
            assert(footman.getHealth() == 100);

            // 1. After the grunt attacked the footman:
            // Expect (100 HP - 15 - 2 + 10 + 1 =) 94 HP left for footman after first blow.
            grunt.attack(footman);
            assert(footman.getHealth() == 94);

            // 2. After the grunt attacked the footman:
            //Expect (94 HP - 15 - 2 + 10 + 1 =) 88 HP left for footman after second blow.
            grunt.attack(footman);
            assert(footman.getHealth() == 88);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
