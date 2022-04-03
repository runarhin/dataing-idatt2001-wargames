package edu.ntnu.idatt2001.runarin.backend.filehandling;

import edu.ntnu.idatt2001.runarin.backend.Army;
import edu.ntnu.idatt2001.runarin.backend.exceptions.CorruptedArmyFileException;
import edu.ntnu.idatt2001.runarin.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.RangedUnit;

import java.io.*;
import java.util.ArrayList;

/**
 * Class handles reading and writing of files regarding data in the Army class.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-04-03
 */
public class FileHandler {

    /**
     * Creates a new, or writes to existing, csv.-file information
     * about the army and its units to a wanted location.
     * The file name is the same as the army's name.
     *
     * @param filePath Path to where the generated file is to be stored. The file-name
     *                 itself is generated based on the army name.
     * @throws IOException thrown from FileWriter-object.
     * @see Army
     */
    public static void writeArmyToFile(Army army, String filePath) throws IOException {
        if (army == null) throw new IllegalArgumentException("Parameter for Army class is missing.");

        String csvFile = filePath + "/" + army.getName().replace(" ", "") + ".csv";
        File file = new File(csvFile);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        // Writes the army name to the file.
        bw.write(army.getName());
        bw.newLine();

        // Writes all the specialised units to the file.
        for (int i = 0; i < army.getCommanderUnits().size(); i++) {
            bw.write("CommanderUnit" + ","
                    + army.getCommanderUnits().get(i).getName() + ","
                    + army.getCommanderUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < army.getCavalryUnits().size(); i++) {
            bw.write("CavalryUnit" + ","
                    + army.getCavalryUnits().get(i).getName() + ","
                    + army.getCavalryUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < army.getRangedUnits().size(); i++) {
            bw.write("RangedUnit" + ","
                    + army.getRangedUnits().get(i).getName() + ","
                    + army.getRangedUnits().get(i).getHealth());
            bw.newLine();
        }
        for (int i = 0; i < army.getInfantryUnits().size(); i++) {
            bw.write("InfantryUnit" + ","
                    + army.getInfantryUnits().get(i).getName() + ","
                    + army.getInfantryUnits().get(i).getHealth());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    /**
     * Reads a file containing units and returns an ArrayList with these units.
     * Throws CorruptedArmyFileException if the file is corrupt.
     * Only returns units to the list if there are no corruptions in the file.
     *
     * @param file Path and name to the file to be read.
     * @throws CorruptedArmyFileException Throws exception if the file data is corrupted.
     */
    public static ArrayList<Unit> readArmyFromFile(Army army, String file) throws IOException {
        if (army == null) throw new IllegalArgumentException("Parameter for Army class is missing.");
        if (file.isBlank()) throw new IllegalArgumentException("Parameter 'file' cannot be blank.");
        if (!file.contains(".csv")) throw new IllegalArgumentException("Parameter 'file' must be of a .csv-format");

        ArrayList<Unit> importUnitsList = new ArrayList<>();
        int i = 1;

        try (BufferedReader br = new BufferedReader((new FileReader(file)))) {

            // Iterates readLine() to the first line, then see if the file contains the correct army.
            String line = br.readLine();
            if (!line.equals(army.getName())) throw new IOException("File refers to the wrong army.");

            // readLine() then iterates from line 2.
            while ((line = br.readLine()) != null) {

                String[] unit = line.split(",");
                if (unit.length != 3)
                    throw new CorruptedArmyFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': does not follow the correct format of three columns.");

                String unitType = unit[0];
                String unitName = unit[1];
                int unitHealth = Integer.parseInt(unit[2]);

                if (!(unitType.contains("CommanderUnit") || unitType.contains("CavalryUnit")
                        || unitType.contains("RangedUnit") || unitType.contains("InfantryUnit")))
                    throw new CorruptedArmyFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': A unit with an invalid unit type occurred in file.");
                if (unitName.isBlank())
                    throw new CorruptedArmyFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': A unit with no name occurred in file.");
                if (unitHealth <= 0)
                    throw new CorruptedArmyFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': A unit with invalid health occurred in file.");

                switch (unitType) {
                    case "CommanderUnit" -> importUnitsList.add(new CommanderUnit(unitName, unitHealth));
                    case "CavalryUnit" -> importUnitsList.add(new CavalryUnit(unitName, unitHealth));
                    case "RangedUnit" -> importUnitsList.add(new RangedUnit(unitName, unitHealth));
                    case "InfantryUnit" -> importUnitsList.add(new InfantryUnit(unitName, unitHealth));
                }
                i++;
            }
        }
        return importUnitsList;
    }
}