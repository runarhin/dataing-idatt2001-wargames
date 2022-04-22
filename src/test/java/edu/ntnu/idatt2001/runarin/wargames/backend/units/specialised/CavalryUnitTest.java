package edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CavalryUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        CavalryUnit testUnit = new CavalryUnit("Knight", 100);
        assertEquals(testUnit.toString(), "\nKnight  [100 hp]  CavalryUnit");
    }

    @Test
    public void cavalryUnitExcessivelyAttacksAnInfantryUnitToSeeAttackBonusDecreaseAfterFirstAttack() {
        /*
        A unit attacks another to see outcome of health of the attacked unit. This test considers also the
        attack and resist bonus for an infantry unit.

        This test cannot use assertEquals(raiderAttacker.getAttackBonus(), 6) because by calling it, it will be set.
        Instead, the HP of the attacked unit is asserted to see that the charge ability is successful.
         */
        InfantryUnit footmanDefender = new InfantryUnit("Footman", 100);
        CavalryUnit raiderAttacker = new CavalryUnit("Raider", 100);

        // 0. Before the raider attacked the footman:
        assertEquals(footmanDefender.getHealth(),100);

        // 1. After the raider attacked the footman:
        // Expect (100 HP - 20 - 6 + 10 + 1 =) 85 HP left for footman after first blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 85);

        // 2. After the raider attacked the footman:
        // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 74);

        // 3. After the raider attacked the footman:
        // Expect (74 HP - 20 - 2 + 10 + 1 =) 63 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 63);

    }

    @Test
    public void resetMethodInCavalryUnitClassShouldMakeAttackBonusToBeResetForWhenItsAttackingANewOpponent() {
        /*
        This test checks that the attack bonus is reset and that again gives a full bonus.

        This test cannot use assertEquals(raiderAttacker.getAttackBonus(), 6) because by calling it, it will be set.
        Instead, the HP of the attacked unit is asserted to see that the charge ability is successful.
         */
        InfantryUnit footmanDefender = new InfantryUnit("Footman", 100);
        CavalryUnit raiderAttacker = new CavalryUnit("Raider", 100);

        // 0. Before the raider attacked the footman:
        assertEquals(footmanDefender.getHealth(), 100);

        // 1. After the raider attacked the footman:
        // Expect (100 HP - 20 - 6 + 10 + 1 =) 85 HP left for footman after first blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 85);

        // 2. After the raider attacked the footman:
        // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals (footmanDefender.getHealth(), 74);

        // 3. RESET of private variable attacked in the RangedUnit archer.
        raiderAttacker.resetChargeAbility();

        // 4. The raider attacks again.
        // Expect (74 HP - 20 - 6 + 10 + 1 =) 59 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 59);

        // 5. The raider attacks again.
        // Expect (59 HP - 20 - 2 + 10 + 1 =) 48 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender, TerrainType.HILL);
        assertEquals(footmanDefender.getHealth(), 48);
    }

    @Test
    public void assertAttackBonusWhenFightingInPlainsTerrain() {
        /*
        Test asserts that the cavalry unit get the attack bonus when fighting in PLAINS terrain
        against an infantry unit which has no bonuses when fighting on a plains.
         */
        CavalryUnit raider = new CavalryUnit("Raider", 100);
        InfantryUnit footman = new InfantryUnit("Footman", 100);

        // Calling raider.getAttackBonus() twice in FOREST to assert charge ability is disabled.
        assertEquals(6, raider.getAttackBonus(TerrainType.FOREST));
        assertEquals(2, raider.getAttackBonus(TerrainType.FOREST));
        assertEquals(2, raider.getAttackBonus(TerrainType.HILL));
        assertEquals(7, raider.getAttackBonus(TerrainType.PLAINS));

        // 0. Before the raider attacked the footman:
        assertEquals(100, footman.getHealth());

        // 1. After the raider attacked the footman:
        // Expect (100 HP - 20 - 7 + 10 + 1 =) 84 HP left for footman after first blow.
        raider.attack(footman, TerrainType.PLAINS);
        assertEquals(84, footman.getHealth());

        // 2. After the raider attacked the footman:
        // Expect (84 HP - 20 - 7 + 10 + 1 =) 68 HP left for footman after first blow.
        raider.attack(footman, TerrainType.PLAINS);
        assertEquals(68, footman.getHealth());
    }

    @Test
    public void assertResistHandicapWhenAttackedInForestTerrain() {
        /*
        Test asserts that the cavalry unit have no resist bonus when fighting in FOREST terrain
        against a ranged unit which has a small attack handicap in forest.
         */
        CavalryUnit knight = new CavalryUnit("Knight", 100);
        RangedUnit spearman = new RangedUnit("Spearman", 100);

        assertEquals(1, knight.getResistBonus(TerrainType.HILL));
        assertEquals(1, knight.getResistBonus(TerrainType.PLAINS));
        assertEquals(0, knight.getResistBonus(TerrainType.FOREST));
        assertEquals(1, spearman.getAttackBonus(TerrainType.FOREST));

        // 0. Before the spearman attacked the knight:
        assertEquals(100, knight.getHealth());

        // 1. After the spearman attacked the knight:
        // Expect (100 HP - 15 - 1 + 12 + 0 =) 96 HP left for knight after first blow.
        spearman.attack(knight, TerrainType.FOREST);
        assertEquals(96, knight.getHealth());

        // 2. After the spearman attacked the knight:
        // Expect (98 HP - 15 - 1 + 12 + 0 =) 92 HP left for knight after first blow.
        spearman.attack(knight, TerrainType.FOREST);
        assertEquals(92, knight.getHealth());
    }
}
