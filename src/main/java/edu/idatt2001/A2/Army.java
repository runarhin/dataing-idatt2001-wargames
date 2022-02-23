package edu.idatt2001.A2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Army {

    private String name;
    private ArrayList<Unit> units;

    /**
     * Constructor for instantiation the class Army with a given Hashmap list of units.
     * @param name      Name of the faction army.
     * @param units     Units in the army; Footmen, raiders, knights, etc.
     */
    public Army(String name, ArrayList<Unit> units) {
        this.name = name;
        this.units = units;
    }

    /**
     * Constructor for instantiation the class Army.
     * This simplified Army class initialises a HashMap list for containing all units in an army.
     * @param name      Name of the faction army.
     */
    public Army(String name) {
        this.name = name;
        this.units = new ArrayList<>();
    }

    /**
     * Get-method that returns the name of the army.
     * @return  String, name of the army.
     */
    public String getName() {
        return name;
    }

    /**
     * Method that adds a unit to the army list.
     * @param unit  Single unit to be added to the army.
     */
    public boolean add(Unit unit) throws Exception {
        if (unit.getHealth() <= 5) {
            throw new Exception("Cannot add half dead unit to army.");
        }
        else if (unit.getAttack() <= 2) {
            throw new Exception("Applicants level is to low to enlist to this army. Come back at another time.");
        }
        else if (unit.getArmor() <= 2) {
            throw new Exception("Forgot your armor? Come back at another time.");
        }
        units.add(unit);
        return true;
    }

    /**
     * Method that adds a bunch of units to the army list.
     * @param unitsInput     Adds a list of units to this army.
     */
    public boolean addAll(ArrayList<Unit> unitsInput) throws Exception {
        if (!unitsInput.isEmpty()) {
            for (Unit u : unitsInput) {
                if (u.getHealth() < 1) {
                    throw new Exception("Cannot add dead unit to army.");
                }
                else if (u.getAttack() <= 2) {
                    throw new Exception("Applicants level is to low to enlist to this army. Come back at another time.");
                }
                else if (u.getArmor() <= 2) {
                    throw new Exception("Forgot your armor? Come back at another time.");
                }
                units.add(u);
            }
            return true;
            //TODO: Could use the addAll-method in ArrayList. Use this if its not important to check all units?
            //TODO: for-loop stops when Exception is thrown. Find another solution!
        }else {
            return false;
        }
    }

    /**
     * Method which deletes a unit from the army list.
     * @param unit  A unit to be removed from the army list.
     * @return      true if unit is found and removed. false if unit is not found.
     */
    public boolean remove(Unit unit) {
        if (!units.isEmpty()) {
            for (Unit u : units) {
                if (u.getName().equals(unit.getName())) {
                    units.remove(u);
                    return true;
                }
            }
        }
        return false;
        //TODO: Could this be done better by searching for a units name instead of the whole object Unit unit?
    }

    /**
     * Method that gives an indication whether the units-list contains units or not.
     * @return      true if the army list contains units. false if the list is empty.
     */
    public boolean hasUnits() {
        if (!units.isEmpty()) {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Method that returns a deep copy of the army list, containing units.
     * @return      ArrayList containing all units enlisted.
     */
    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> newArrayList = new ArrayList<Unit>();
        for (Unit u : units) {
            newArrayList.add(u);
        }
        return newArrayList;
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
        return "The mighty army of " + name + " consists of " + units.size() + " units.";
    }

    /**
     * Method that checks if an army-object is the same object another army-object.
     * @param o     Object to be compared to.
     * @return      True if the two objects are the same. False if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Army)) return false;
        Army army = (Army) o;
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
