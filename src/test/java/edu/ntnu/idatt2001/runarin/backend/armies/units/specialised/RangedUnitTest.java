package edu.ntnu.idatt2001.runarin.backend.armies.units.specialised;

import edu.ntnu.idatt2001.runarin.backend.armies.units.TerrainType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangedUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        RangedUnit testUnit = new RangedUnit("Archer", 100);
        assertEquals(testUnit.toString(), "\nArcher  [100 hp]  RangedUnit");
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
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getHealth(), 97);

        // 2. After the grunt attacked the archer:
        // Expect (97 HP - 15 - 2 + 8 + 4 =) 92 HP left for archer after second blow.
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getHealth(), 92);

        // 3. After the grunt attacked the archer:
        // Expect (92 HP - 15 - 2 + 8 + 2 =) 85 HP left for archer after third blow.
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getHealth(), 85);

        // 4. After the grunt attacked the archer:
        // Expect (85 HP - 15 - 2 + 8 + 2 =) 78 HP left for archer after fourth blow.
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
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
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 6);

        // 1. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 4);

        // 2. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 2);

        // 3. After the grunt attacked the archer:
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 2);

        // 4. RESET of private variable attacked in the RangedUnit archer.
        archerDefender.resetAttacked();
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 6);

        // 5. Grunt thereafter attacks again.
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 4);

        // 6. Grunt attacks again.
        gruntAttacker.attack(archerDefender, TerrainType.PLAINS);
        assertEquals(archerDefender.getResistBonus(TerrainType.PLAINS), 2);
    }

    @Test
    public void assertAttackBonusWhenFightingInHillTerrain() {
        /*
        Test asserts that the ranged unit get the attack bonus when fighting in HILL terrain
        against an infantry unit which has no bonuses when fighting on a hill.
         */
        RangedUnit spearman = new RangedUnit("Spearman", 100);
        InfantryUnit footman = new InfantryUnit("Footman", 100);

        assertEquals(1, spearman.getAttackBonus(TerrainType.FOREST));
        assertEquals(7, spearman.getAttackBonus(TerrainType.HILL));
        assertEquals(3, spearman.getAttackBonus(TerrainType.PLAINS));

        // 0. Before the spearman attacked the footman:
        assertEquals(100, footman.getHealth());

        // 1. After the spearman attacked the footman:
        // Expect (100 HP - 15 - 7 + 10 + 1 =) 89 HP left for footman after first blow.
        spearman.attack(footman, TerrainType.HILL);
        assertEquals(89, footman.getHealth());

        // 2. After the spearman attacked the footman:
        // Expect (92 HP - 15 - 7 + 10 + 1 =) 78 HP left for footman after first blow.
        spearman.attack(footman, TerrainType.HILL);
        assertEquals(78, footman.getHealth());
    }
}
