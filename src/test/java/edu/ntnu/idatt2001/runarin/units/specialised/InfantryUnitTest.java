package edu.ntnu.idatt2001.runarin.units.specialised;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InfantryUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        InfantryUnit testUnit = new InfantryUnit("Footman", 100);

        assertEquals(testUnit.toString(), "\n| Footman | HP = 100 | Attack power = 15 | Armor points = 10 |");
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
        assertEquals(footman.getHealth(), 100);

        // 1. After the grunt attacked the footman:
        // Expect (100 HP - 15 - 2 + 10 + 1 =) 94 HP left for footman after first blow.
        grunt.attack(footman);
        assertEquals(footman.getHealth(), 94);

        // 2. After the grunt attacked the footman:
        //Expect (94 HP - 15 - 2 + 10 + 1 =) 88 HP left for footman after second blow.
        grunt.attack(footman);
        assertEquals(footman.getHealth(), 88);
    }
}
