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
    void cavalryUnitExcessivelyAttacksAnInfantryUnitToSeeAttackBonusDecreaseAfterFirstAttack() {
        try {
            /* Notes, cavalryUnit have:
                - Base attack of 20.
                - Base armor of 12.
                - Resist bonus of 1.
                - Should have attack bonus of 4+2 on the first attack and 2 on the second.   */

            Unit footman = new InfantryUnit("Footman", 100);
            Unit raider = new CavalryUnit("Raider", 100);

            // 0. Before the raider attacked the footman:
            assert(footman.getHealth() == 100);

            // 1. After the raider attacked the footman:
            // Expect (100 HP - 20 - 6 + 10 + 1 =) 85 HP left for footman after first blow.
            raider.attack(footman);
            assert(footman.getHealth() == 85);

            // 2. After the raider attacked the footman:
            // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
            raider.attack(footman);
            assert(footman.getHealth() == 74);

            // 3. After the raider attacked the footman:
            // Expect (74 HP - 20 - 2 + 10 + 1 =) 63 HP left for footman after third blow.
            raider.attack(footman);
            assert(footman.getHealth() == 63);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void resetMethodInCavalryUnitClassShouldMakeAttackBonusToBeResetForWhenItsAttackingANewOpponent() {
        try {
            Unit footman = new InfantryUnit("Footman", 100);
            Unit raider = new CavalryUnit("Raider", 100);

            // 0. Before the raider attacked the footman:
            assert(footman.getHealth() == 100);
            //assert(raider.getAttackBonus() == 6);
            // Attack bonus is 6 initially, but this changes when getAttackBonus()-method is called for.

            // 1. After the raider attacked the footman:
            // Expect (100 HP - 20 - 6 + 10 + 1 =) 85 HP left for footman after first blow.
            raider.attack(footman);
            assert(footman.getHealth() == 85);

            // 2. After the raider attacked the footman:
            // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
            raider.attack(footman);
            assert (footman.getHealth() == 74);

            // 3. RESET of private variable attacked in the RangedUnit archer.
            ((CavalryUnit) raider).resetChargeAbility();

            // 4. The raider attacks again.
            // Expect (74 HP - 20 - 6 + 10 + 1 =) 59 HP left for footman after third blow.
            raider.attack(footman);
            assert(footman.getHealth() == 59);

            // 5. The raider attacks again.
            // Expect (59 HP - 20 - 2 + 10 + 1 =) 48 HP left for footman after third blow.
            raider.attack(footman);
            assert(footman.getHealth() == 48);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
