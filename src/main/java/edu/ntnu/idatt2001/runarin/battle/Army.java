package edu.ntnu.idatt2001.runarin.battle;

import edu.ntnu.idatt2001.runarin.battle.units.Unit;
import edu.ntnu.idatt2001.runarin.battle.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.battle.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.battle.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.battle.units.specialised.RangedUnit;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * A class Army.
 * This class represents an army with a name and a list of warrior units.
 *
 * @version 1.0.1
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
    public boolean addUnit(Unit unit) {
        return units.add(unit);
    }

    /**
     * Method that adds a number of units to the army list.
     * @param unitsInput    A list of units to add to army.
     */
    public boolean addUnitsFromList(ArrayList<Unit> unitsInput) {
        return units.addAll(unitsInput);
    }

    /**
     * Method which deletes a unit from the army list.
     * @param unit      A unit to be removed from the army list.
     */
    public boolean remove(Unit unit) {
        if (units.contains(unit)) {
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
     * Method that returns a deep copy of an armies unit list.
     * @return      ArrayList containing all units enlisted.
     */
    public ArrayList<Unit> getAllUnits() {
        ArrayList<Unit> newArrayList = new ArrayList<>();
        for (Unit u : units) {
            if (u instanceof InfantryUnit) {
                newArrayList.add(new InfantryUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof RangedUnit) {
                newArrayList.add(new RangedUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof CommanderUnit) {
                newArrayList.add(new CommanderUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
            else if (u instanceof CavalryUnit) {
                newArrayList.add(new CavalryUnit(u.getName(),u.getHealth(),u.getAttack(), u.getArmor()));
            }
        }
        return newArrayList;
    }

    /**
     * Method which streams through the army's units list and filter on instances of infantry units.
     * @return      An ArrayList containing only infantry units.
     */
    public ArrayList<Unit> getInfantryUnits() {
        List<Unit> infantryList
                = units.stream()
                        .filter(unit -> unit instanceof InfantryUnit)
                        .collect(Collectors.toList());
        return (ArrayList<Unit>) infantryList;
    }

    /**
     * Method which streams through the army's units list and filter on instances of ranged units.
     * @return      An ArrayList containing only ranged units.
     */
    public ArrayList<Unit> getRangedUnits() {
        List<Unit> rangedList
                = units.stream()
                        .filter(unit -> unit instanceof RangedUnit)
                        .collect(Collectors.toList());
        return (ArrayList<Unit>) rangedList;
    }

    /**
     * Method which streams through the army's units list and filter on instances of cavalry units.
     * @return      An ArrayList containing only cavalry units.
     */
    public ArrayList<Unit> getCavalryUnits() {
        List<Unit> cavalryList
                = units.stream()
                        .filter(unit -> ((unit instanceof CavalryUnit) & !(unit instanceof CommanderUnit)))
                        .collect(Collectors.toList());
        return (ArrayList<Unit>) cavalryList;
    }

    /**
     * Method which streams through the army's units list and filter on instances of commander units.
     * @return      An ArrayList containing only commander units.
     */
    public ArrayList<Unit> getCommanderUnits() {
        List<Unit> commanderList
                = units.stream()
                .filter(unit -> unit instanceof CommanderUnit)
                .collect(Collectors.toList());
        return (ArrayList<Unit>) commanderList;
    }

    /**
     * Method which writes information about the army and its units to a csv file
     * to path: src/main/resources/army-files/
     * @throws      IOException if error occurs.
     */
    public void writeArmyToFile() throws IOException {

        String csvFile = "src/main/resources/army-files/" + name.replace(" ", "") + "-export.csv";
        File file = new File(csvFile);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        // Writes the army name to the file.
        bw.write(name);
        bw.newLine();

        // Writes all the specialised units to the file.
        for (int i = 0; i < getCommanderUnits().size(); i++) {
            bw.write("CommanderUnit" + ","
                    + getCommanderUnits().get(i).getName() + ","
                    + getCommanderUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < getCavalryUnits().size(); i++) {
            bw.write("CavalryUnit" + ","
                    + getCavalryUnits().get(i).getName() + ","
                    + getCavalryUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < getRangedUnits().size(); i++) {
            bw.write("RangedUnit" + ","
                    + getRangedUnits().get(i).getName() + ","
                    + getRangedUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < getInfantryUnits().size(); i++) {
            bw.write("InfantryUnit" + ","
                    + getInfantryUnits().get(i).getName() + ","
                    + getInfantryUnits().get(i).getHealth());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    /**
     * Method which reads a file containing units and adds these units if they coherce with the correct army.
     * @param filePath      Filepath and file name to where the file is stored.
     * @throws IOException  Throws IOException if the army name is different in the import file to the Army object.
     */
    public void readAndAddUnitsFromFile(String filePath) throws IOException {

        try (BufferedReader br = new BufferedReader((new FileReader(filePath)))) {

            // Iterates readLine() to the first line, then see if the file contains the correct army.
            String line = br.readLine();
            if (!line.equals(name)) throw new IOException("File refers to wrong army.");

            while ((line = br.readLine()) != null) {

                String[] unit = line.split(",");
                String unitType = unit[0];
                String unitName = unit[1];
                int unitHealth = Integer.parseInt(unit[2]);

                switch (unitType) {
                    case "CommanderUnit" -> units.add(new CommanderUnit(unitName, unitHealth));
                    case "CavalryUnit" -> units.add(new CavalryUnit(unitName, unitHealth));
                    case "RangedUnit" -> units.add(new RangedUnit(unitName, unitHealth));
                    case "InfantryUnit" -> units.add(new InfantryUnit(unitName, unitHealth));
                }
            }
        }
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
