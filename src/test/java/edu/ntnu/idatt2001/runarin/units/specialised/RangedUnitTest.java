package edu.ntnu.idatt2001.runarin.units.specialised;

import edu.ntnu.idatt2001.runarin.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.RangedUnit;
import edu.ntnu.idatt2001.runarin.units.Unit;
import org.junit.jupiter.api.Test;

public class RangedUnitTest {

    @Test
    void createSomeUnitsAndReturnToString() {
        try {
            Unit testUnit1 = new RangedUnit("Archer", 100);
            System.out.println("\n" + testUnit1.toString());

            Unit testUnit2 = new RangedUnit("Spearman", 100);
            System.out.println("\n" + testUnit2.toString());

            RangedUnit testUnit3 = new RangedUnit("Epic Archer", 150, 17, 10);
            System.out.println("\n" + testUnit3.toString());

            RangedUnit testUnit4 = new RangedUnit("Epic Spearman", 150, 17, 10);
            System.out.println("\n" + testUnit4.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void infantryUnitExcessivelyAttacksRangedUnitToSeeChangeOfRangedUnitsResistBonus() {
        try {
            /* Notes, RangedUnit have:
                - Base attack of 15.
                - Base armor of 8.
                - Resist bonus depends on the range between units. 6 when far away, 4 when closer and 2 when up close.
                - Attack bonus of 3.  */

            Unit archer = new RangedUnit("Archer", 100);
            Unit grunt = new InfantryUnit("Grunt", 100);

            // 0. Before the grunt attacked the archer:
            assert(archer.getHealth() == 100);

            // 1. After the grunt attacked the archer:
            // Expect (100 HP - 15 - 2 + 8 + 6 =) 97 HP left for archer after first blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 97);

            // 2. After the grunt attacked the archer:
            // Expect (97 HP - 15 - 2 + 8 + 4 =) 92 HP left for archer after second blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 92);

            // 3. After the grunt attacked the archer:
            // Expect (92 HP - 15 - 2 + 8 + 2 =) 85 HP left for archer after third blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 85);

            // 4. After the grunt attacked the archer:
            // Expect (85 HP - 15 - 2 + 8 + 2 =) 78 HP left for archer after fourth blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 78);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void resetMethodInRangedUnitClassShouldMakeResistanceBonusBeResetForWhenItsAttackedByANewOpponent() {
        try {
            Unit archer = new RangedUnit("Archer", 100);
            Unit grunt = new InfantryUnit("Grunt", 100);

            // 0. Before the grunt attacked the archer:
            assert(archer.getHealth() == 100);
            assert(archer.getResistBonus() == 6);

            // 1. After the grunt attacked the archer:
            // Expect (100 HP - 15 - 2 + 8 + 6 =) 97 HP left for archer after first blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 97);
            assert(archer.getResistBonus() == 4);

            // 2. After the grunt attacked the archer:
            // Expect (97 HP - 15 - 2 + 8 + 4 =) 92 HP left for archer after second blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 92);
            assert(archer.getResistBonus() == 2);

            // 3. After the grunt attacked the archer:
            // Expect (92 HP - 15 - 2 + 8 + 2 =) 85 HP left for archer after third blow.
            grunt.attack(archer);
            assert(archer.getHealth() == 85);
            assert(archer.getResistBonus() == 2);

            // 4. RESET of private variable attacked in the RangedUnit archer.
            ((RangedUnit) archer).resetAttacked();
            assert(archer.getResistBonus() == 6);

            // 5. Grunt thereafter attacks again.
            // Expect (85 HP - 15 - 2 + 8 + 6 =) 82 HP left for archer after fourth blow.
            grunt.attack(archer);
            assert(archer.getResistBonus() == 4);
            assert(archer.getHealth() == 82);

            // 6. Grunt attacks again.
            // Expect (82 HP - 15 - 2 + 8 + 4 =) 77 HP left for archer after fourth blow.
            grunt.attack(archer);
            assert(archer.getResistBonus() == 2);
            assert(archer.getHealth() == 77);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
