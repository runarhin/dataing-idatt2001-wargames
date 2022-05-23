package edu.ntnu.idatt2001.runarin.backend.armies.units;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitFactoryTest {

    @Test
    public void assertStaticMethodGetUnitMethodReturnsAUnit() {
        /*
        Test asserts that the static method getUnit in UnitFactory
        class returns a unit's toString() by the given parameters.
         */
        Unit unit = UnitFactory.getUnit(UnitType.INFANTRY, "Grunt", 100);

        assertEquals("\nGrunt  [100 hp]  InfantryUnit", unit.toString());
    }

    @Test
    public void assertStaticMethodGetUnitsByTypeReturnsAListWithGivenNumberOfUnitsByGivenType() {
        /*
        Test asserts that the static method getUnitsByType in UnitFactory
        class returns an ArrayList of given number of units by the given type.

        Expect list to have 4 units and all have the same toString().
         */
        ArrayList<Unit> units = UnitFactory.getUnitsByType(4, UnitType.RANGED, "Archer", 100);

        assertEquals(4, units.size());
        for (Unit unit : units) {
            assertEquals("\nArcher  [100 hp]  RangedUnit",
                    unit.toString());
        }
    }
}
