package edu.ntnu.idatt2001.runarin.units.specialised;

import edu.ntnu.idatt2001.runarin.battle.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.battle.units.specialised.RangedUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangedUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        RangedUnit testUnit = new RangedUnit("Archer", 100);

        assertEquals(testUnit.toString(), "\n| Archer | HP = 100 | Attack power = 15 | Armor points = 8 |");
    }

    @Test
    public void resistBonusForARangeUnitDecreasesAsAnOpposingUnitExcessivelyAttacks() {
        /*
        This test checks that the resist bonus for a ranged unit will change when excessively attacked.
            - Resist bonus depends on the "range" between units. 6 when far away, 4 when closer and 2 when up close.
        */

        RangedUnit archerDefender = new RangedUnit("Archer", 100);
        InfantryUnit gruntAttacker = new InfantryUnit("Grunt", 100);

        // 0. Before the grunt attacked the archer:
        assertEquals(archerDefender.getHealth(), 100);

        // 1. After the grunt attacked the archer:
        // Expect (100 HP - 15 - 2 + 8 + 6 =) 97 HP left for archer after first blow.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getHealth(), 97);

        // 2. After the grunt attacked the archer:
        // Expect (97 HP - 15 - 2 + 8 + 4 =) 92 HP left for archer after second blow.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getHealth(), 92);

        // 3. After the grunt attacked the archer:
        // Expect (92 HP - 15 - 2 + 8 + 2 =) 85 HP left for archer after third blow.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getHealth(), 85);

        // 4. After the grunt attacked the archer:
        // Expect (85 HP - 15 - 2 + 8 + 2 =) 78 HP left for archer after fourth blow.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getHealth(), 78);
    }

    @Test
    public void resetAttackedMethodMakesResistanceBonusToBeReset() {
        /*
        This test checks that the resist bonus is reset and that again gives a full bonus.
         */
        RangedUnit archerDefender = new RangedUnit("Archer", 100);
        InfantryUnit gruntAttacker = new InfantryUnit("Grunt", 100);

        // 0. Before the grunt attacked the archer:
        assertEquals(archerDefender.getResistBonus(), 6);

        // 1. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getResistBonus(), 4);

        // 2. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getResistBonus(), 2);

        // 3. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getResistBonus(), 2);

        // 4. RESET of private variable attacked in the RangedUnit archer.
        archerDefender.resetAttacked();
        assertEquals(archerDefender.getResistBonus(), 6);

        // 5. Grunt thereafter attacks again.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getResistBonus(), 4);

        // 6. Grunt attacks again.
        gruntAttacker.attack(archerDefender);
        assertEquals(archerDefender.getResistBonus(), 2);
    }
}
