package edu.ntnu.idatt2001.runarin.wargames.backend.filehandling;

import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.wargames.backend.exceptions.CorruptedFileException;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.Unit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.RangedUnit;

import java.io.*;
import java.util.ArrayList;

/**
 * Class handles reading and writing of files regarding data in the Army class.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-04-11
 */
public class FileHandler {

    /**
     * Creates a new, or writes to existing, csv.-file information
     * about the army and its units to a wanted location.
     * The file name is the same as the army's name.
     *
     * @param filePath path to where the generated file is to be stored. The file-name
     *                 itself is generated based on the army name.
     * @throws IOException thrown from FileWriter-object.
     * @see Army class.
     */
    public static void writeArmyToFile(Army army, String filePath) throws IOException {
        if (army == null) throw new IllegalArgumentException("Parameter for Army class is missing.");

        ArrayList<Unit> units = new ArrayList<>(army.getCommanderUnits());
        units.addAll(army.getCavalryUnits());
        units.addAll(army.getRangedUnits());
        units.addAll(army.getInfantryUnits());

        String csvFile = filePath + "/" + army.getName().replace(" ", "") + ".csv";
        File file = new File(csvFile);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        // Writes the army name to the file.
        bw.write(army.getName());
        bw.newLine();

        // Writes all the specialised units to the file.
        for (Unit unit : units) {
            if (unit instanceof CommanderUnit) {
                bw.write("CommanderUnit" + "," + unit.getName() + "," + unit.getHealth());
                bw.newLine();
            } else if (unit instanceof CavalryUnit) {
                bw.write("CavalryUnit" + "," + unit.getName() + "," + unit.getHealth());
                bw.newLine();
            } else if (unit instanceof RangedUnit) {
                bw.write("RangedUnit" + "," + unit.getName() + "," + unit.getHealth());
                bw.newLine();
            } else if (unit instanceof InfantryUnit) {
                bw.write("InfantryUnit" + "," + unit.getName() + "," + unit.getHealth());
                bw.newLine();
            }
        }
        bw.close();
        fw.close();
    }

    /**
     * Returns only the army name from a file.
     *
     * @param file path and file name of the file to be read.
     * @return army name.
     * @throws IOException if army name s blank.
     */
    public static String readArmyNameFromFile(String file) throws IOException {
        if (file.isBlank()) throw new IllegalArgumentException("Parameter 'file' cannot be blank.");

        String armyName;
        try (BufferedReader br = new BufferedReader((new FileReader(file)))) {
            // Reads the first line in the file which contains the army name.
            armyName = br.readLine();
            if (armyName.isBlank()) throw new IOException("Army name is blank in the given file.");
        }
        return armyName;
    }

    /**
     * Reads a file containing units and returns an ArrayList with these units.
     * Throws CorruptedArmyFileException if the file is corrupt.
     * Only returns units to the list if there are no corruptions in the file.
     *
     * @param file path and file name of the file to be read.
     * @throws CorruptedFileException throws exception if the file data is corrupted.
     */
    public static ArrayList<Unit> readUnitsFromFile(Army army, String file) throws IOException {
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
                    throw new CorruptedFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': does not follow the correct format of three columns.");

                String unitType = unit[0];
                String unitName = unit[1];
                int unitHealth = Integer.parseInt(unit[2]);

                if (!(unitType.contains("CommanderUnit") || unitType.contains("CavalryUnit")
                        || unitType.contains("RangedUnit") || unitType.contains("InfantryUnit")))
                    throw new CorruptedFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': A unit with an invalid unit type occurred in file.");
                if (unitName.isBlank())
                    throw new CorruptedFileException("Corrupted data in file:\n    '" + file +
                            "'\n    In line '" + i + "': A unit with no name occurred in file.");
                if (unitHealth <= 0)
                    throw new CorruptedFileException("Corrupted data in file:\n    '" + file +
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

    /**
     * Writes string from StringBuilder to a .txt-file named BattleLog.txt.
     *
     * @param battleLog the battle log in StringBuilder format to be written to a .txt-file.
     * @throws IOException thrown from FileWriter-object.
     * @see Battle class.
     */
    public static void writeBattleLogToFile(StringBuilder battleLog, String fileName) throws IOException {
        if (battleLog.isEmpty()) throw new IOException("Battle log is empty.");

        String filePath = "src/main/resources/battle-files" + fileName;
        File file = new File(filePath);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        // Writes the battle log to file.
        bw.write(battleLog.toString());

        bw.close();
        fw.close();
    }

    /**
     * Returns a StringBuilder containing text information from a file.
     *
     * @return StringBuilder containing text information from a file.
     * @throws IOException thrown by BufferedReader.
     */
    public static StringBuilder readBattleLogFromFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        String file = "src/main/resources/battle-files/BattleLog.txt";
        try (BufferedReader br = new BufferedReader((new FileReader(file)))) {

            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        }
        return stringBuilder;
    }
}
