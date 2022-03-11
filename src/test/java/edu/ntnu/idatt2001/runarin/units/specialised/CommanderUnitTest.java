package edu.ntnu.idatt2001.runarin.units.specialised;

import edu.ntnu.idatt2001.runarin.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.units.Unit;
import org.junit.jupiter.api.Test;

public class CommanderUnitTest {

    @Test
    void createSomeCommanderUnitAndReturnToString() {
        try {
            Unit commanderTestUnit1 = new CommanderUnit("Gul'dan", 180);
            Unit commanderTestUnit2 = new CommanderUnit("Mountain King", 180, 23, 17);
            System.out.println("\n" + commanderTestUnit1.toString());
            System.out.println("\n" + commanderTestUnit2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testingBonusCalculationsForCommanderUnit() {
        try {
            /* Notes, CommanderUnit have:
                - Base attack of 25.
                - Base armor of 15.
                - Resist bonus of 1.
                - Should have attack bonus of 4+2 on the first attack and 2 on the second.  */

            CommanderUnit hordeCommander = new CommanderUnit("Gul'dan", 180);
            CommanderUnit allianceCommander = new CommanderUnit("Mountain King", 180);

            //0. Before the Mountain King attacked Gul'dan:
            assert(hordeCommander.getHealth() == 180);

            // 1. After the Mountain King attacked Gul'dan:
            // Expect (180 HP - 25 - 6 + 15 + 1 =) 165 HP left for knight after first blow.
            allianceCommander.attack(hordeCommander);
            assert(hordeCommander.getHealth() == 165);

            // 2. After the Mountain King attacked Gul'dan:
            // Expect (165 HP - 25 - 2 + 15 + 1 =) 154 HP left for knight after second blow.
            allianceCommander.attack(hordeCommander);
            assert(hordeCommander.getHealth() == 154);

            // 3. After the Mountain King attacked Gul'dan:
            // Expect (154 HP - 25 - 2 + 15 + 1 =) 143 HP left for knight after third blow.
            allianceCommander.attack(hordeCommander);
            assert(hordeCommander.getHealth() == 143);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
