package edu.ntnu.idatt2001.runarin.backend.armies;

import edu.ntnu.idatt2001.runarin.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.RangedUnit;
import edu.ntnu.idatt2001.runarin.backend.exceptions.CorruptedFileException;
import edu.ntnu.idatt2001.runarin.backend.armies.units.Unit;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents an army with a name and a list of warrior units.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-11
 */
public class Army {

    private final String name;
    private final ArrayList<Unit> units;

    /**
     * Constructor for instantiation the class Army with a list of units.
     *
     * @param name name of the faction army.
     * @param units units in the army; Footmen, raiders, knights, etc.
     */
    public Army(String name, ArrayList<Unit> units) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");
        if (name.length() > 25) throw new IllegalArgumentException("Army name is to long for: \"" + name + "\"." +
                "\nTry to shorten the name in the imported army file.");
        this.name = name;
        this.units = units;
    }

    /**
     * Simplified constructor for instantiation the class Army.
     *
     * @param name name of the faction army.
     */
    public Army(String name) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");
        if (name.length() > 25) throw new IllegalArgumentException("Army name is to long for: \"" + name + "\"." +
                "\nTry to shorten the name in the imported army file.");
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
     * Add a unit to the army list.
     *
     * @param unit single unit to be added to the army.
     */
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    /**
     * Add a number of units to the army list.
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
     * Returns a boolean whether the units-list contains units or not.
     *
     * @return false if the list is empty. true if the army list contains units.
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Returns a deep copy of the army's units list.
     *
     * @return ArrayList containing all units enlisted.
     */
    public ArrayList<Unit> getUnits() {
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
     * Streams through the army's units list and filter on instances of ranged units.
     * @return ArrayList containing only ranged units.
     */
    public ArrayList<Unit> getRangedUnits() {
        return units.stream()
                .filter(unit -> unit instanceof RangedUnit)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
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
     * Creates a new CSV-file, or writes to existing file, information about the army to a specified location.
     * The file name is the same as the army's name.
     *
     * @param filePath path to where the file is to be stored. The file-name
     *                 itself is self-generated based on the army name.
     * @throws IOException thrown from FileWriter-object in the FileHandler-class.
     * @see FileHandler handles the files to be written to.
     */
    public void writeArmyToFile(String filePath) throws IOException {
        FileHandler.writeArmyToFile(this, filePath);
    }

    /**
     * Add units to army from a CSV-file.
     *
     * @param file path and name to where the file is stored.
     * @throws CorruptedFileException thrown if the file data is corrupted.
     * @throws IOException thrown if the file refers to the wrong army.
     */
    public void addUnitsFromFile(String file) throws IOException {
        ArrayList<Unit> importUnitsList = FileHandler.readUnitsFromFile(this, file);
        units.addAll(importUnitsList);
    }

    /**
     * Returns a random unit from the army's units list.
     *
     * @return random unit from the army.
     */
    public Unit getRandom() {
        Random rand = new Random();
        int randomUnit = rand.nextInt(units.size());

        return units.get(randomUnit);
    }

    /**
     * Returns the name and the number of units of an army.
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
     * @return true if the two objects are the same, false if not.
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
