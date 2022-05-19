package edu.ntnu.idatt2001.runarin.backend.units;

import edu.ntnu.idatt2001.runarin.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.RangedUnit;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A unit factory for getting a large amount of units of the same type, and with the same name and health.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-10
 */
public class UnitFactory {

    /**
     * Return a unit based on type, name and health.
     *
     * @param unitType the enum type of unit; INFANTRY, RANGED, CAVALRY or COMMANDER.
     * @see UnitType enum UnitType containing the type of unit.
     * @param unitName the name  of the unit.
     * @param unitHealth the unit's health.
     * @return a unit based on given type, name and health.
     */
    public static Unit getUnit(UnitType unitType, String unitName, int unitHealth) {
        return switch (unitType) {
            case INFANTRY -> new InfantryUnit(unitName, unitHealth);
            case RANGED -> new RangedUnit(unitName, unitHealth);
            case COMMANDER -> new CommanderUnit(unitName, unitHealth);
            case CAVALRY -> new CavalryUnit(unitName, unitHealth);
        };
    }

    /**
     * Return ArrayList containing n number of units of same type, name, and health.
     *
     * @param n the number of units to be generated.
     * @param unitType the enum type of unit; INFANTRY, RANGED, CAVALRY or COMMANDER.
     * @see UnitType enum UnitType containing the type of units.
     * @param unitName the name  of the units.
     * @param unitHealth the units' health.
     * @return a list of n number of units of the same type and with the same name and health.
     */
    public static ArrayList<Unit> getUnitsByType(int n, UnitType unitType, String unitName, int unitHealth) {
        return (ArrayList<Unit>) Stream.generate(ArrayList<Unit>::new)
                .limit(n)
                .map(u -> getUnit(unitType, unitName, unitHealth))
                .collect(Collectors.toList());
    }
}
