package edu.ntnu.idatt2001.runarin.battle.units.specialised;

import edu.ntnu.idatt2001.runarin.battle.units.specialised.CommanderUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommanderUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        CommanderUnit testUnit = new CommanderUnit("Mountain King", 180);

        assertEquals(testUnit.toString(), "\n| Mountain King | HP = 180 | Attack power = 25 | Armor points = 15 |");
    }

    @Test
    public void testingBonusCalculationsForCommanderUnit() {
        /*
        A commander unit attacks another to see outcome of health of the attacked unit. This test considers also the
        attack and resist bonus for a commander unit.
         */
        CommanderUnit hordeCommander = new CommanderUnit("Gul'dan", 180);
        CommanderUnit allianceCommander = new CommanderUnit("Mountain King", 180);

        //0. Before the Mountain King attacked Gul'dan:
        assertEquals(hordeCommander.getHealth(), 180);

        // 1. After the Mountain King charged Gul'dan:
        // Expect (180 HP - 25 - 6 + 15 + 1 =) 165 HP left for knight after first blow.
        allianceCommander.attack(hordeCommander);
        assertEquals(hordeCommander.getHealth(), 165);

        // 2. After the Mountain King attacked Gul'dan:
        // Expect (165 HP - 25 - 2 + 15 + 1 =) 154 HP left for knight after second blow.
        allianceCommander.attack(hordeCommander);
        assertEquals(hordeCommander.getHealth(), 154);

        // 3. After the Mountain King attacked Gul'dan:
        // Expect (154 HP - 25 - 2 + 15 + 1 =) 143 HP left for knight after third blow.
        allianceCommander.attack(hordeCommander);
        assertEquals(hordeCommander.getHealth(), 143);
    }
}
