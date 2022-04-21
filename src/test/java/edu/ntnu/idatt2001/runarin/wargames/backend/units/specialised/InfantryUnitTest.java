package edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised;

import edu.ntnu.idatt2001.runarin.wargames.backend.units.TerrainType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfantryUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        InfantryUnit testUnit = new InfantryUnit("Footman", 100);
        assertEquals(testUnit.toString(), "\nFootman  [100 hp]  InfantryUnit");
    }

    @Test
    public void oneInfantryUnitExcessivelyAttacksAnotherUnitToSeeHealthDecreaseOfTheAttackedUnit() {
        /*
        A unit attacks another to see outcome of health of the attacked unit. This test considers also the
        attack and resist bonus for an infantry unit.
         */
        InfantryUnit footman = new InfantryUnit("Footman", 100);
        InfantryUnit grunt = new InfantryUnit("Grunt", 100);

        // 0. Before the grunt attacked the footman:
        Assertions.assertEquals(footman.getHealth(), 100);

        // 1. After the grunt attacked the footman:
        // Expect (100 HP - 15 - 2 + 10 + 1 =) 94 HP left for footman after first blow.
        grunt.attack(footman, TerrainType.PLAINS);
        Assertions.assertEquals(footman.getHealth(), 94);

        // 2. After the grunt attacked the footman:
        //Expect (94 HP - 15 - 2 + 10 + 1 =) 88 HP left for footman after second blow.
        grunt.attack(footman, TerrainType.PLAINS);
        Assertions.assertEquals(footman.getHealth(), 88);
    }

    @Test
    public void assertAttackBonusWhenFightingInForestTerrain() {
        /*
        Test asserts that the infantry unit get the attack bonus when fighting in FOREST terrain
        against a cavalry unit which has no defence in forest.
         */
        InfantryUnit grunt = new InfantryUnit("Grunt", 100);
        CavalryUnit knight = new CavalryUnit("Knight", 100);

        assertEquals(2, grunt.getAttackBonus(TerrainType.HILL));
        assertEquals(2, grunt.getAttackBonus(TerrainType.PLAINS));
        assertEquals(5, grunt.getAttackBonus(TerrainType.FOREST));

        // 0. Before the grunt attacked the knight:
        assertEquals(100, knight.getHealth());

        // 1. After the grunt attacked the knight:
        // Expect (100 HP - 15 - 5 + 12 + 0 =) 92 HP left for knight after first blow.
        grunt.attack(knight, TerrainType.FOREST);
        assertEquals(92, knight.getHealth());

        // 2. After the grunt attacked the knight:
        // Expect (92 HP - 15 - 5 + 12 + 0 =) 84 HP left for knight after first blow.
        grunt.attack(knight, TerrainType.FOREST);
        assertEquals(84, knight.getHealth());
    }

    @Test
    public void assertResistBonusWhenAttackedInForestTerrain() {
        /*
        Test asserts that the infantry unit get the resist bonus when fighting in FOREST terrain
        against a ranged unit which has a small attack handicap in forest.
         */
        InfantryUnit grunt = new InfantryUnit("Grunt", 100);
        RangedUnit archer = new RangedUnit("Archer", 100);

        assertEquals(1, grunt.getResistBonus(TerrainType.HILL));
        assertEquals(1, grunt.getResistBonus(TerrainType.PLAINS));
        assertEquals(4, grunt.getResistBonus(TerrainType.FOREST));
        assertEquals(1, archer.getAttackBonus(TerrainType.FOREST));

        // 0. Before the archer attacked the grunt:
        assertEquals(100, grunt.getHealth());

        // 1. After the archer attacked the grunt:
        // Expect (100 HP - 15 - 1 + 13 + 1 =) 98 HP left for grunt after first blow.
        archer.attack(grunt, TerrainType.FOREST);
        assertEquals(98, grunt.getHealth());

        // 2. After the archer attacked the grunt:
        // Expect (98 HP - 15 - 1 + 13 + 1 =) 96 HP left for grunt after first blow.
        archer.attack(grunt, TerrainType.FOREST);
        assertEquals(96, grunt.getHealth());
    }
}
