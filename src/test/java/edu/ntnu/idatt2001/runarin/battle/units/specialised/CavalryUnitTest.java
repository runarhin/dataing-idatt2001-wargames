package edu.ntnu.idatt2001.runarin.battle.units.specialised;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CavalryUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        CavalryUnit testUnit = new CavalryUnit("Knight", 100);

        assertEquals(testUnit.toString(), "\n| Knight | HP = 100 | Attack power = 20 | Armor points = 12 |");
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
        raiderAttacker.attack(footmanDefender);
        assertEquals(footmanDefender.getHealth(), 85);

        // 2. After the raider attacked the footman:
        // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
        raiderAttacker.attack(footmanDefender);
        assertEquals(footmanDefender.getHealth(), 74);

        // 3. After the raider attacked the footman:
        // Expect (74 HP - 20 - 2 + 10 + 1 =) 63 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender);
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
        raiderAttacker.attack(footmanDefender);
        assertEquals(footmanDefender.getHealth(), 85);

        // 2. After the raider attacked the footman:
        // Expect (85 HP - 20 - 2 + 10 + 1 =) 74 HP left for footman after second blow.
        raiderAttacker.attack(footmanDefender);
        assertEquals (footmanDefender.getHealth(), 74);

        // 3. RESET of private variable attacked in the RangedUnit archer.
        raiderAttacker.resetChargeAbility();

        // 4. The raider attacks again.
        // Expect (74 HP - 20 - 6 + 10 + 1 =) 59 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender);
        assertEquals(footmanDefender.getHealth(), 59);

        // 5. The raider attacks again.
        // Expect (59 HP - 20 - 2 + 10 + 1 =) 48 HP left for footman after third blow.
        raiderAttacker.attack(footmanDefender);
        assertEquals(footmanDefender.getHealth(), 48);
    }
}
