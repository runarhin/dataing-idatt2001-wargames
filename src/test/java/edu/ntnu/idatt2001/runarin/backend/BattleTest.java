package edu.ntnu.idatt2001.runarin.backend;

import edu.ntnu.idatt2001.runarin.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.RangedUnit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BattleTest {

    @Test
    public void constructorThrowsIllegalArgumentExceptionWhenAnArmyIsNull() {
        try {
            Army horde = new Army("The Horde");
            Army alliance = new Army("The Alliance");
            Battle grandWar = new Battle(null, alliance);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("There must be two armies as input.", e.getMessage());
        }
    }

    @Test
    public void constructorThrowsIllegalArgumentExceptionWhenArmyIsToBattleItself() {
        try {
            Army alliance = new Army("The Alliance");
            Battle grandWar = new Battle(alliance, alliance);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("An army cannot battle itself.", e.getMessage());
        }
    }

    @Test
    public void toStringTestToCheckTotalToStringWithNameAndNumberOfUnitsAfterAddingUnitsToBothArmies() {
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
        assertEquals("Battle between The Horde [3 unit(s)] and The Alliance [3 unit(s)]", grandWar.toString());
    }

    @Test
    public void armyWithMostUnitsWinTheWarAndReturnCorrectWinningArmy() {
        /*
        This test adds a powerful unit to one of the armies and footman to the other.
        The goal is to check that the correct army is returned when it wins.
         */
        Army horde = new Army("The Horde");
        Army alliance = new Army("The Alliance");

        horde.addUnit(new CommanderUnit("Gul'dan", 9000));
        alliance.addUnit(new InfantryUnit("Footman", 100));

        Battle grandWar = new Battle(horde, alliance);

        assertEquals("The Horde [1 unit(s)]", grandWar.simulate().toString());
    }

    @Test
    public void someWarGameWhereTheWinningArmyIsAsserted() {
        /*
        This test is used to give indications after a longer battle for that units attack and
        looses health in an intuitive way. This means for example that the cavalry unit can charge new opponents
        and as a new attacking unit approaches a ranged unit, the range will be refreshed.
        The results can be observed in the BattleLog.txt-file.

        This test has been the main test resource for fault proofing the simulate-method.
        It also asserts that one of the armies is empty of units after a simulation.
         */
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

        Battle battle = new Battle(horde, alliance);

        Army winningArmy = battle.simulate();

        if (!winningArmy.equals(alliance)) assertFalse(alliance.hasUnits());
        else {assertFalse(horde.hasUnits());}
    }
}
