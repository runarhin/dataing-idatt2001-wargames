package edu.ntnu.idatt2001.runarin;

import edu.ntnu.idatt2001.runarin.units.Army;
import edu.ntnu.idatt2001.runarin.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.RangedUnit;
import org.junit.jupiter.api.Test;

public class BattleTest {

    @Test
    void toStringTestToSeeTheOutcomeOfToStringWithNameAndNumberOfUnits() {
        try {
            Army horde = new Army("The Horde");
            Army alliance = new Army("The Alliance");

            Battle grandWar = new Battle(horde, alliance);

            // Adds x number of grunt units to the  horde army.
            for (int i = 0; i < 3; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.add(new InfantryUnit("Footman", 100));
            }

            System.out.println(grandWar.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void someWarGame() {
        try {
            Army horde = new Army("The Orcish Horde");
            Army alliance = new Army("The Human Army");

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 3; i++) {
                horde.add(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.add(new InfantryUnit("Footman", 100));
            }
            // Adds x number of spearman units to the horde army.
            for (int i = 0; i < 3; i++) {
                horde.add(new RangedUnit("Spearman", 100));
            }
            // Adds x number of archer units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.add(new RangedUnit("Archer", 100));
            }
            // Adds x number of raider units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.add(new CavalryUnit("Raider", 100));
            }
            // Adds x number of knight units to the alliance army.
            for (int i = 0; i < 2; i++) {
                alliance.add(new CavalryUnit("Knight", 100));
            }
            // Adds commanders to the respective armies.
            horde.add(new CavalryUnit("Gul'dan", 180));
            alliance.add(new CavalryUnit("Mountain King", 180));

            Battle grandWar = new Battle(horde, alliance);

            System.out.println("\n" + grandWar.simulate() + " won the battle!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
