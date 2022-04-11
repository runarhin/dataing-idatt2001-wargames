package edu.ntnu.idatt2001.runarin.wargames.backend.armies;

import edu.ntnu.idatt2001.runarin.wargames.backend.exceptions.CorruptedFileException;
import edu.ntnu.idatt2001.runarin.wargames.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.RangedUnit;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class Army.
 * This class represents an army with a name and a list of warrior units.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-04-11
 */
public class Army {

    private final String name;
    private final ArrayList<Unit> units;

    /**
     * Constructor for instantiation the class Army with a given Hashmap list of units.
     *
     * @param name name of the faction army.
     * @param units units in the army; Footmen, raiders, knights, etc.
     */
    public Army(String name, ArrayList<Unit> units) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");
        this.name = name;
        this.units = units;
    }

    /**
     * Constructor for instantiation the class Army.
     * This simplified Army class initialises a HashMap list for containing all units in an army.
     *
     * @param name name of the faction army.
     */
    public Army(String name) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Returns the name of the army.
     *
     * @return name of the army.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds a unit to the army list.
     *
     * @param unit single unit to be added to the army.
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    /**
     * Adds a number of units to the army list.
     *
     * @param unitsInput list of units to add to army.
     */
    public void addUnitsFromList(ArrayList<Unit> unitsInput) {
        units.addAll(unitsInput);
    }

    /**
     * Deletes a unit from the army list.
     *
     * @param unit a unit to be removed from the army list.
     */
    public void remove(Unit unit) {
        units.remove(unit);
    }

    /**
     * Returns an indication whether the units-list contains units or not.
     *
     * @return false if the list is empty. true if the army list contains units.
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Returns a deep copy of an armies unit list.
     *
     * @return ArrayList containing all units enlisted.
     */
    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> newUnitsList = new ArrayList<>();
        for (Unit u : this.units) {
            if (u instanceof CommanderUnit) {
                newUnitsList.add(new CommanderUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof CavalryUnit) {
                newUnitsList.add(new CavalryUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof RangedUnit) {
                newUnitsList.add(new RangedUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof InfantryUnit) {
                newUnitsList.add(new InfantryUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
        }
        return newUnitsList;
    }

    /**
     * Returns ArrayList<InfantryUnit>.
     * Streams through the army's units list and filter on instances of infantry units.
     *
     * @return ArrayList containing only infantry units.
     */
    public ArrayList<Unit> getInfantryUnits() {
        return units.stream()
                .filter(unit -> unit instanceof InfantryUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns ArrayList<RangedUnit>.
     * Streams through the army's units list and filter on instances of ranged units.
     * @return ArrayList containing only ranged units.
     */
    public ArrayList<Unit> getRangedUnits() {
        return units.stream()
                .filter(unit -> unit instanceof RangedUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns ArrayList<CavalryUnit>.
     * Streams through the army's units list and filter on instances of cavalry units.
     *
     * @return ArrayList containing only cavalry units.
     */
    public ArrayList<Unit> getCavalryUnits() {
        return units.stream()
                .filter(unit -> ((unit instanceof CavalryUnit) & !(unit instanceof CommanderUnit)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Returns ArrayList<CommanderUnit>.
     * Streams through the army's units list and filter on instances of commander units.
     *
     * @return ArrayList containing only commander units.
     */
    public ArrayList<Unit> getCommanderUnits() {
        return units.stream()
                .filter(unit -> unit instanceof CommanderUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Creates a new, or writes to existing, csv.-file information
     * about the army and its units to a wanted location.
     * The file name is the same as the army's name.
     *
     * @param filePath path to where the file is to be stored. The file-name
     *                 itself is self-generated based on the army name.
     * @throws IOException thrown from FileWriter-object in the FileHandler-class.
     * @see FileHandler
     */
    public void writeArmyToFile(String filePath) throws IOException {
        FileHandler.writeArmyToFile(this, filePath);
    }

    /**
     * Reads a file containing units and adds these to the army.
     *
     * @param file path and name to where the file is stored.
     * @throws CorruptedFileException thrown if the file data is corrupted.
     * @throws IOException thrown if the file refers to the wrong army.
     */
    public void addUnitsFromFile(String file) throws IOException {
        ArrayList<Unit> importUnitsList = FileHandler.readArmyFromFile(this, file);
        units.addAll(importUnitsList);
    }

    /**
     * Finds a random number within the range of the units ArrayList and
     * thereafter finds a random unit by index.
     *
     * @return random unit in the units ArrayList.
     */
    public Unit getRandom() {
        Random rand = new Random();
        int randomUnit = rand.nextInt(units.size());

        return units.get(randomUnit);
    }

    /**
     * Returns the name and the number of units of a mighty army.
     *
     * @return army name and its size.
     */
    @Override
    public String toString() {
        return "" + name + " [" + units.size() + " unit(s)]";
    }

    /**
     * Checks if an army-object is the same object another army-object.
     *
     * @param o object to be compared to.
     * @return true if the two objects are the same. False if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army army)) return false;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * Returns the HashCode of an object.
     *
     * @return int value of the HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
