package edu.ntnu.idatt2001.runarin.units;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * A class Army.
 * This class represents an army with a name and a list of warrior units.
 */
public class Army {

    private final String name;
    private ArrayList<Unit> units;

    /**
     * Constructor for instantiation the class Army with a given Hashmap list of units.
     * @param name      Name of the faction army.
     * @param units     Units in the army; Footmen, raiders, knights, etc.
     */
    public Army(String name, ArrayList<Unit> units) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");

        this.name = name;
        this.units = units;
    }

    /**
     * Constructor for instantiation the class Army.
     * This simplified Army class initialises a HashMap list for containing all units in an army.
     * @param name      Name of the faction army.
     */
    public Army(String name) {
        if (name.isEmpty() || name.isBlank()) throw new IllegalArgumentException("Army name cannot be empty");

        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Get-method that returns the name of the army.
     * @return      Name of the army.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that adds a unit to the army list.
     * @param unit      Single unit to be added to the army.
     */
    public boolean add(Unit unit) {
        return units.add(unit);
    }

    /**
     * Method that adds a number of units to the army list.
     * @param unitsInput    Number of units to add to army.
     */
    public boolean addAll(ArrayList<Unit> unitsInput) {
        if (!unitsInput.isEmpty()) {
            return units.addAll(unitsInput);
        } else return false;
    }

    /**
     * Method which deletes a unit from the army list.
     * @param unit      A unit to be removed from the army list.
     */
    public boolean remove(Unit unit) {
        if (!units.isEmpty() & units.contains(unit)) {
            return units.remove(unit);
        } else return false;
    }

    /**
     * Method that gives an indication whether the units-list contains units or not.
     * @return      false if the list is empty. true if the army list contains units.
     */
    public boolean hasUnits() {
        return !units.isEmpty();
    }

    /**
     * Method that returns a deep copy of the army list, containing units.
     * @return      ArrayList containing all units enlisted.
     */
    public ArrayList<Unit> getAllUnits() {
        return new ArrayList<>(units);
    }

    /**
     * Method that finds a random number within the range of the units ArrayList and
     * thereafter finds a random unit by index.
     * @return      Random unit in the units ArrayList.
     */
    public Unit getRandom() {
        Random rand = new Random();
        int randomUnit = rand.nextInt(units.size());

        return units.get(randomUnit);
    }

    /**
     * toString-method that returns the name and the number of units of a mighty army.
     * @return      Army name and its size.
     */
    @Override
    public String toString() {
        return "" + name + " [" + units.size() + " unit(s)]";
    }

    /**
     * Method that checks if an army-object is the same object another army-object.
     * @param o     Object to be compared to.
     * @return      True if the two objects are the same. False if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army army)) return false;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * Method that returns the HashCode of an object.
     * @return      Int value of the HashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }
}
