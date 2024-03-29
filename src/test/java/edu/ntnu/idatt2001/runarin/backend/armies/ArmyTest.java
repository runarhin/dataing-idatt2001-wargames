package edu.ntnu.idatt2001.runarin.backend.armies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.ntnu.idatt2001.runarin.backend.exceptions.CorruptedFileException;
import edu.ntnu.idatt2001.runarin.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.backend.armies.units.Unit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.backend.armies.units.specialised.RangedUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArmyTest {

    Army army;
    @BeforeEach
    public void addInitialUnitsToArmy() {
        /*
        This code is run before each of these nested tests and initialises an army
        containing all varieties of units.
         */
        army = new Army("Test Army");

        for (int i = 0; i < 3; i++) {
            army.addUnit(new InfantryUnit("Grunt",100));
        }
        for (int i = 0; i < 3; i++) {
            army.addUnit(new RangedUnit("Spearman",100));
        }
        for (int i = 0; i < 3; i++) {
            army.addUnit(new CavalryUnit("Raider",100));
        }
        army.addUnit(new CommanderUnit("Gul'dan",180));

        assertEquals(10, army.getUnits().size());
    }

    @Nested
    public class readAndWriteArmyFromAndToCSVFile {

        @Test
        public void writeArmyToFile() {
            /*
            This test writes information about an army to a csv-file and checks that no exceptions are thrown.
            Then reads the file and asserts its content.
             */
            try {
                army.writeArmyToFile("src/main/resources/test-files");

                // Asserts that the content of the file written to is correct.
                String file = "src/main/resources/test-files/TestArmy.csv";
                try (BufferedReader br = new BufferedReader((new FileReader(file)))) {

                    // Iterates readLine() to the first line, then see if the file contains the correct army.
                    String line = br.readLine();
                    assertEquals(line, army.getName());

                    int i = 1;
                    // readLine() then iterates from second line.
                    while ((line = br.readLine()) != null) {

                        String[] unit = line.split(",");
                        String unitType = unit[0];
                        String unitName = unit[1];
                        int unitHealth = Integer.parseInt(unit[2]);

                        if (i == 1) {
                            assertEquals("CommanderUnit", unitType);
                            assertEquals("Gul'dan", unitName);
                            assertEquals(180, unitHealth);
                        }
                        if ((i == 2) || (i == 3) || (i == 4)) {
                            assertEquals("CavalryUnit", unitType);
                            assertEquals("Raider", unitName);
                            assertEquals(100, unitHealth);
                        }
                        if ((i == 5) || (i == 6) || (i == 7)) {
                            assertEquals("RangedUnit", unitType);
                            assertEquals("Spearman", unitName);
                            assertEquals(100, unitHealth);
                        }
                        if ((i == 8) || (i == 9) || (i == 10)) {
                            assertEquals("InfantryUnit", unitType);
                            assertEquals("Grunt", unitName);
                            assertEquals(100, unitHealth);
                        }
                        i++;
                    }
                }
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readArmyNameFromFile() {
            /*
            This test asserts that the army name is returned from a file.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-valid.csv";
                String armyName = FileHandler.readArmyNameFromFile(file);
                assertEquals("Test Army", armyName);
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readArmyNameFromFileThrowsExceptionOnInvalidFileFormat() {
            /*
            This test asserts that the input file must be csv, if not an exception is thrown.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupt-file-format.txt";
                String armyName = FileHandler.readArmyNameFromFile(file);
                fail();
            } catch (IllegalArgumentException e) {
                assertEquals("Parameter 'file' must be of a .csv-format", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readArmyNameFromFileThrowsExceptionOnBlankArmyName() {
            /*
            This test asserts that the army name is not blank, if so an exception is thrown.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupt-blank-army-name.csv";
                String armyName = FileHandler.readArmyNameFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("Army name is blank in the given file.", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readArmyNameFromFileThrowsExceptionOnInvalidCharactersInArmyName() {
            /*
            This test asserts that the army name does not contain invalid characters,
            if so an exception is thrown.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupt-comma-in-army-name.csv";
                String armyName = FileHandler.readArmyNameFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("Army name is corrupt in file.", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readContentOfFileAndAddItsContentOfUnitsToArmy() {
            /*
            This test reads the content of a file and asserts that it follows the correct format,
            by adding units to the army from the import file.
             */
            try {
                assertEquals(10, army.getUnits().size());

                String file = "src/main/resources/test-files/TestArmy-valid.csv";
                army.addUnitsFromFile(file);

                assertEquals(19, army.getUnits().size());
                assertEquals("""
                                [
                                Grunt  [100 hp]  InfantryUnit,\s
                                Grunt  [100 hp]  InfantryUnit,\s
                                Grunt  [100 hp]  InfantryUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Gul'dan  [180 hp]  CommanderUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Raider  [100 hp]  CavalryUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Spearman  [100 hp]  RangedUnit,\s
                                Grunt  [100 hp]  InfantryUnit,\s
                                Grunt  [100 hp]  InfantryUnit,\s
                                Grunt  [100 hp]  InfantryUnit]""",
                        army.getUnits().toString());

            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsNonCSVFileAndIllegalArgumentExceptionIsThrown() {
            /*
            Test asserts that IllegalArgumentException is thrown when the parameter-file is not of .csv-format.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy.word";
                army.addUnitsFromFile(file);
                fail();
            } catch (IllegalArgumentException e) {
                assertEquals("Parameter 'file' must be of a .csv-format", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readUnitsFromFileThrowsCorruptedFileException() {
            /*
            This test asserts that the army name does not contain invalid characters
            before importing units. Else an exception is thrown.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupt-comma-in-army-name.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("Army name is corrupt in file.", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsCommaCorruptedFileAndCorruptedArmyFileExceptionIsThrown() {
            /*
            Test asserts that CorruptedArmyFileException is thrown when there is a comma missing in the file.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupted-comma.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("""
                        Corrupted data in file: 'src/main/resources/test-files/TestArmy-corrupted-comma.csv'
                        
                        In line 3: does not follow the correct format of three columns.""", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsInvalidUnitTypeAndCorruptedArmyFileExceptionIsThrown() {
            /*
            Test asserts that CorruptedArmyFileException is thrown when an invalid unit type is in the file.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupted-unitType.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("""
                        Corrupted data in file: 'src/main/resources/test-files/TestArmy-corrupted-unitType.csv'
                        
                        In line 2: A unit with an invalid unit type occurred in file.""", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsInvalidUnitNameAndCorruptedArmyFileExceptionIsThrown() {
            /*
            Test asserts that CorruptedArmyFileException is thrown when an invalid unit name is in the file.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupted-unitName.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("""
                        Corrupted data in file: 'src/main/resources/test-files/TestArmy-corrupted-unitName.csv'
                        
                        In line 7: A unit with no name occurred in file.""", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsInvalidUnitHealthAndCorruptedArmyFileExceptionIsThrown() {
            /*
            Test asserts that CorruptedArmyFileException is thrown when an invalid unit health is in the file.
             */
            try {
                String file = "src/main/resources/test-files/TestArmy-corrupted-unitHealth.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("""
                        Corrupted data in file: 'src/main/resources/test-files/TestArmy-corrupted-unitHealth.csv'
                        
                        In line 1: A unit with invalid health occurred in file.""", e.getMessage());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }

        @Test
        public void readsInvalidFileAndAssertThatUnitsAreNotAddedToTheArmy() {
            /*
            Test asserts that units are not added to the army if the file is corrupt.
             */
            try {
                assertEquals(10, army.getUnits().size());
                String file = "src/main/resources/test-files/TestArmy-corrupted-unitHealth.csv";
                army.addUnitsFromFile(file);
                fail();
            } catch (CorruptedFileException e) {
                assertEquals("""
                        Corrupted data in file: 'src/main/resources/test-files/TestArmy-corrupted-unitHealth.csv'
                            
                        In line 1: A unit with invalid health occurred in file.""", e.getMessage());
                assertEquals(10, army.getUnits().size());
            } catch (IOException e) {
                assertNull(e.getMessage());
            }
        }
    }

    @Nested
    public class getSpecialisedUnits {

        @Test
        public void getInfantryUnitListFromArmyListOfUnits() {
            /*
            Asserts that the filtered units list only contains the specified instance of infantry units.
             */
            assertEquals(3, army.getInfantryUnits().size());
            assertEquals("""
                            [
                            Grunt  [100 hp]  InfantryUnit,\s
                            Grunt  [100 hp]  InfantryUnit,\s
                            Grunt  [100 hp]  InfantryUnit]""",
                    army.getInfantryUnits().toString());
        }

        @Test
        public void getRangedUnitListFromArmyListOfUnits() {
            /*
            Asserts that the filtered units list only contains the specified instance of ranged units.
             */
            assertEquals(3, army.getRangedUnits().size());
            assertEquals("""
                            [
                            Spearman  [100 hp]  RangedUnit,\s
                            Spearman  [100 hp]  RangedUnit,\s
                            Spearman  [100 hp]  RangedUnit]""",
                    army.getRangedUnits().toString());
        }

        @Test
        public void getCavalryUnitListFromArmyListOfUnits() {
            /*
            Asserts that the filtered units list only contains the specified instance of cavalry units.
             */
            assertEquals(3, army.getCavalryUnits().size());
            assertEquals("""
                            [
                            Raider  [100 hp]  CavalryUnit,\s
                            Raider  [100 hp]  CavalryUnit,\s
                            Raider  [100 hp]  CavalryUnit]""",
                    army.getCavalryUnits().toString());
        }

        @Test
        public void getCommanderUnitListFromArmyListOfUnits() {
            /*
            Asserts that the filtered units list only contains the specified instance of commander units.
             */
            assertEquals(1, army.getCommanderUnits().size());
            assertEquals("[\nGul'dan  [180 hp]  CommanderUnit]",
                    army.getCommanderUnits().toString());
        }
    }

    @Nested
    public class addUnitsToAnArmy {

        @Test
        public void addAListOfUnitsToAnArmyByAddingAListOfUnitsInTheConstructor() {
            /*
            This test adds units to an army and asserts that the right amount of
            units are in the list.
             */
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);

            Army testArmy = new Army("The Alliance", testArrayList);

            // The list contain units:  Expect true.
            assertTrue(testArmy.hasUnits());

            // Number of units in the list:  Expects 2 units to be added and not 1 or 3.
            assertEquals(2, testArmy.getUnits().size());
            assertNotEquals(1, testArmy.getUnits().size());
            assertNotEquals(3, testArmy.getUnits().size());
        }

        @Test
        public void addListOfUnitsWithAddUnitsFromListMethod() {
            /*
            This test asserts the method addUnitsFromList by checking the number of units in the army.
             */
            ArrayList<Unit> testArrayList = new ArrayList<>();

            Unit testUnit1 = new CommanderUnit("Mountain King", 180);
            Unit testUnit2 = new InfantryUnit("Footman", 100);
            Unit testUnit3 = new CavalryUnit("Knight", 100);

            testArrayList.add(testUnit1);
            testArrayList.add(testUnit2);
            testArrayList.add(testUnit3);

            Army alliance = new Army("The Alliance"); // Simplified constructor.

            alliance.addUnitsFromList(testArrayList);

            // Expects now that the army have 3 units in it.
            assertEquals(3, alliance.getUnits().size());
        }

        @Test
        public void removeASingleUnitFromUnitsList() {
            /*
            This test asserts that only a single unit is removed from the army.
             */
            Army horde = new Army("The Horde");

            Unit testUnit1 = new InfantryUnit("Grunt", 100);
            Unit testUnit2 = new InfantryUnit("Grunt", 100);
            Unit testUnit3 = new InfantryUnit("Grunt", 100);

            horde.addUnit(testUnit1);
            horde.addUnit(testUnit2);
            horde.addUnit(testUnit3);

            horde.remove(testUnit2);

            assertEquals(2, horde.getUnits().size());
            assertFalse(horde.getUnits().contains(testUnit2));
        }

        @Test
        public void removeUnitsFromAnArmyAndSeeThatTheUnitListEventuallyGetsEmpty() {
            /*
            This test removes a unit from a list one at the time and asserts that the number of units decreases.
             */
            Army horde = new Army("The Horde");               // Simplified constructor.

            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 2; i++) {
                horde.addUnit(new InfantryUnit("Grunt", 100));
            }
            assertEquals(2, horde.getUnits().size());   // Expects that the army now have 2 units in it.

            // Removes 1 unit from the army:
            horde.remove(horde.getRandom());
            assertEquals(1, horde.getUnits().size());   // Expects that the army now have 1 unit left.
            assertFalse(horde.getUnits().isEmpty());             // And that the list is not empty.

            // Removes the last unit in the army:
            horde.remove(horde.getRandom());
            assertEquals(0, horde.getUnits().size());   // Expects that the army now have no units left.
            assertTrue(horde.getUnits().isEmpty());              // And that the list now is empty.
        }
    }

    @Test
    public void accessMethodGetNameReturnExpectedName() {
        Army horde = new Army("The Horde");
        assertEquals("The Horde", horde.getName());
    }

    @Test
    public void throwsIllegalArgumentExceptionIfArmyNameIsBlank() {
        try {
            Army testArmy = new Army("  ");
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Army name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void methodGetAllUnitsReturnAllTypesOfUnitsToTheDeepCopiedList() {
        /*
        This test first adds units to the army list and then checks that all units are returned to
        the deep copied list.
         */
        Army horde = new Army("The Horde");
        horde.addUnit(new InfantryUnit("Grunt", 100));
        horde.addUnit(new RangedUnit("Spearman", 100));
        horde.addUnit(new CavalryUnit("Raider", 100));
        horde.addUnit(new CommanderUnit("Gul'dan", 180));

        ArrayList<Unit> testArrayList = horde.getUnits();

        assertEquals("\nGrunt  [100 hp]  InfantryUnit",
                testArrayList.get(0).toString());
        assertEquals("\nSpearman  [100 hp]  RangedUnit",
                testArrayList.get(1).toString());
        assertEquals("\nRaider  [100 hp]  CavalryUnit",
                testArrayList.get(2).toString());
        assertEquals("\nGul'dan  [180 hp]  CommanderUnit",
                testArrayList.get(3).toString());
    }

    @Test
    public void equalsMethodTestedWithTwoArmies() {
        Army army1 = new Army("The Horde");
        Army army2 = new Army("The Horde");
        Army army3 = new Army("The Alliance");

        Unit testUnit1 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit2 = new InfantryUnit("SomeUnit", 100);
        Unit testUnit3 = new InfantryUnit("SomeUnit", 100);

        army1.addUnit(testUnit1);
        army1.addUnit(testUnit2);

        army2.addUnit(testUnit1);
        army2.addUnit(testUnit2);

        army3.addUnit(testUnit3);

        assertEquals(army1, army2);              // Army 1 and 2 equals that their values are the same,
        assertNotSame(army1, army2);             // but they are in reality two different armies.

        assertNotEquals(army1, army3);           // Army 1 and Army 3 differs on value and being different armies.
    }

    @Test
    public void hashCodeIsDifferentBetweenArmies() {
        Army army1 = new Army("The Horde");
        Army army2 = new Army("The Horde");
        Army army3 = new Army("The Alliance");

        // army1 and army2 is saved on the same hashCode as they have the same name.
        assertEquals(army1.hashCode(), army2.hashCode());

        // army1 and army3 differs as they have different names.
        assertNotEquals(army1.hashCode(), army3.hashCode());
    }

    @Test
    public void hashCodeIsDifferentForANumberOfUnitsInAnArmy() {
        Army testArmy = new Army("The Horde");

        testArmy.addUnit(new InfantryUnit("SomeUnit",100));
        testArmy.addUnit(new InfantryUnit("SomeUnit",100));
        testArmy.addUnit(new InfantryUnit("SomeUnit",100));
        testArmy.addUnit(new InfantryUnit("SomeUnit",100));
        testArmy.addUnit(new InfantryUnit("SomeUnit",100));
        testArmy.addUnit(new InfantryUnit("SomeUnit",100));

        // Expects there to be six units in the army.
        assertEquals(6, testArmy.getUnits().size());

        ArrayList<Unit> armyList = testArmy.getUnits();

        // Expects none of the units to have the same hashCode.
        for (int i = 0; i < testArmy.getUnits().size(); i++) {
            for (int j = 0; j < testArmy.getUnits().size(); j++) {
                if (i == j) {
                    assertEquals(armyList.get(i).hashCode(), armyList.get(j).hashCode());
                } else {
                    assertNotEquals(armyList.get(i).hashCode(), armyList.get(j).hashCode());
                }
            }
        }
    }
}
