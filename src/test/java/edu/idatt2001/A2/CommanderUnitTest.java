package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class CommanderUnitTest {

    @Test
    void createSomeCommanderUnitAndReturnToString() {
        try {
            Unit commanderTestUnit1 = new CommanderUnit("Gul'dan", 180);
            Unit commanderTestUnit2 = new CommanderUnit("Mountain King", 180,23,17);
            System.out.println("\n" + commanderTestUnit1.toString());
            System.out.println("\n" + commanderTestUnit2.toString());
        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }

    @Test
    void testingBonusCalculationsForCommanderUnit() {
        try {
            System.out.println("\nNotes, CommanderUnit have: ");
            System.out.println("    - Base attack of 25.");
            System.out.println("    - Base armor of 15.");
            System.out.println("    - Resist bonus of 1.");
            System.out.println("    - Should have attack bonus of 4+2 on the first attack and 2 on the second.");

            System.out.println("\n----------------------------------------------------------");
            CommanderUnit hordeCommander = new CommanderUnit("Gul'dan", 180);
            CommanderUnit allianceCommander = new CommanderUnit("Mountain King", 180);

            System.out.println("\n0. Before the Mountain King attacked Gul'dan:");
            System.out.println("\n" + hordeCommander.toString());
            System.out.println("\n" + allianceCommander.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n1. After the Mountain King attacked Gul'dan:");
            System.out.println("Expect (180 HP - 25 - 6 + 15 + 1 =) 165 HP left for knight after first blow.");
            allianceCommander.attack(hordeCommander);
            System.out.println("\n" + hordeCommander.toString());
            System.out.println("\n" + allianceCommander.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n2. After the Mountain King attacked Gul'dan:");
            System.out.println("Expect (165 HP - 25 - 2 + 15 + 1 =) 154 HP left for knight after second blow.");
            allianceCommander.attack(hordeCommander);
            System.out.println("\n" + hordeCommander.toString());
            System.out.println("\n" + allianceCommander.toString());

            System.out.println("\n----------------------------------------------------------");
            System.out.println("\n3. After the Mountain King attacked Gul'dan:");
            System.out.println("Expect (154 HP - 25 - 2 + 15 + 1 =) 143 HP left for knight after third blow.");
            allianceCommander.attack(hordeCommander);
            System.out.println("\n" + hordeCommander.toString());
            System.out.println("\n" + allianceCommander.toString());
            System.out.println("\n----------------------------------------------------------");

        }catch (Exception e) {
            System.out.println("Error:  " + e);
        }
    }
}
