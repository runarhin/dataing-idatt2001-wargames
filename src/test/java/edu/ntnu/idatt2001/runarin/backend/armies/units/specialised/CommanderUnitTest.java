package edu.ntnu.idatt2001.runarin.backend.armies.units.specialised;

import edu.ntnu.idatt2001.runarin.backend.armies.units.TerrainType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommanderUnitTest {

    @Test
    public void createAUnitAndCheckToString() {
        /*
        Instantiate a unit to check its toString.
         */
        CommanderUnit testUnit = new CommanderUnit("Mountain King", 180);
        assertEquals(testUnit.toString(), "\nMountain King  [180 hp]  CommanderUnit");
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
        Assertions.assertEquals(hordeCommander.getHealth(), 180);

        // 1. After the Mountain King charged Gul'dan:
        // Expect (180 HP - 25 - 6 + 15 + 1 =) 165 HP left for knight after first blow.
        allianceCommander.attack(hordeCommander, TerrainType.HILL);
        Assertions.assertEquals(hordeCommander.getHealth(), 165);

        // 2. After the Mountain King attacked Gul'dan:
        // Expect (165 HP - 25 - 2 + 15 + 1 =) 154 HP left for knight after second blow.
        allianceCommander.attack(hordeCommander, TerrainType.HILL);
        Assertions.assertEquals(hordeCommander.getHealth(), 154);

        // 3. After the Mountain King attacked Gul'dan:
        // Expect (154 HP - 25 - 2 + 15 + 1 =) 143 HP left for knight after third blow.
        allianceCommander.attack(hordeCommander, TerrainType.HILL);
        Assertions.assertEquals(hordeCommander.getHealth(), 143);
    }
}
