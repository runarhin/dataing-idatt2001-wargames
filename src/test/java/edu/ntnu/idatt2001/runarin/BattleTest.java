package edu.ntnu.idatt2001.runarin;

import edu.ntnu.idatt2001.runarin.units.Army;
import edu.ntnu.idatt2001.runarin.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.units.specialised.RangedUnit;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BattleTest {

    @Test
    void toStringTestToCheckTotalToStringWithNameAndNumberOfUnitsAfterAddingUnitsToBothArmies() {
        /*
        Instantiate a battle to check its toString.
         */
        Army horde = new Army("The Horde");
        Army alliance = new Army("The Alliance");

        Battle grandWar = new Battle(horde, alliance);

        // Adds x number of grunt units to the  horde army.
        for (int i = 0; i < 3; i++) {
            horde.addUnit(new InfantryUnit("Grunt", 100));
        }
        // Adds x number of footman units to the alliance army.
        for (int i = 0; i < 3; i++) {
            alliance.addUnit(new InfantryUnit("Footman", 100));
        }
        assertEquals(grandWar.toString(), "Battle between The Horde [3 unit(s)] and The Alliance [3 unit(s)]");
    }

    @Test
    void someWarGame() {
        try {
            Army horde = new Army("The Orcish Horde");
            Army alliance = new Army("The Human Army");

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 3; i++) {
                horde.addUnit(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.addUnit(new InfantryUnit("Footman", 100));
            }
            // Adds x number of spearman units to the horde army.
            for (int i = 0; i < 3; i++) {
                horde.addUnit(new RangedUnit("Spearman", 100));
            }
            // Adds x number of archer units to the alliance army.
            for (int i = 0; i < 3; i++) {
                alliance.addUnit(new RangedUnit("Archer", 100));
            }
            // Adds x number of raider units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.addUnit(new CavalryUnit("Raider", 100));
            }
            // Adds x number of knight units to the alliance army.
            for (int i = 0; i < 2; i++) {
                alliance.addUnit(new CavalryUnit("Knight", 100));
            }
            // Adds commanders to the respective armies.
            horde.addUnit(new CavalryUnit("Gul'dan", 180));
            alliance.addUnit(new CavalryUnit("Mountain King", 180));

            Battle grandWar = new Battle(horde, alliance);

            System.out.println("\n" + grandWar.simulate() + " won the battle!");

        } catch (FileNotFoundException f) {
            f.printStackTrace();
        }
    }
}
