package edu.ntnu.idatt2001.runarin.wargames.frontend.unused;

import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.wargames.backend.armies.Battle;
import edu.ntnu.idatt2001.runarin.wargames.backend.exceptions.ArmyEmptyOfUnitsException;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.CommanderUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.InfantryUnit;
import edu.ntnu.idatt2001.runarin.wargames.backend.units.specialised.RangedUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This is a simple War Games-client which allows creating armies and add units to it.
 * This task did not have a clear description and is made out of my own imagination.
 *
 * It can create an infinite number of armies which is added to a list of armies.
 * A single battle between two armies can be simulated at the time, but the surviving units
 * from the winning army can battle again against a new army.
 *
 * @author Runar Indahl
 * @version 1.0
 * @since 2022-03-06
 */

public class WarGamesSimpleClient {

    private final HashMap<String, Army> armies = new HashMap<>();
    private final int CREATE_ARMY_AND_ADD_COMMANDER = 1;
    private final int ADD_UNITS_TO_AN_ARMY = 2;
    private final int ADD_STANDARD_SET_OF_UNITS_TO_ARMY = 3;
    private final int ADD_A_COMMANDER_UNIT_TO_AN_ARMY = 4;
    private final int ADD_OVERPOWERED_WARRIOR = 5;
    private final int DELETE_ARMY_FROM_RECORD = 6;
    private final int LIST_OF_ARMIES = 7;
    private final int START_BATTLE = 8;
    private final int EXIT = 9;

    public WarGamesSimpleClient() {
    }

    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n*** War Games Application ***\n");
        System.out.println("1. Create a new army by giving it a name and assign its leader.");
        System.out.println("2. Add units to one of the registered armies.");
        System.out.println("3. Add a standard set of units to an army.");
        System.out.println("4. Add a commander unit to an army.");
        System.out.println("5. Bring an overpowered warrior with specialised specs.");
        System.out.println("6. Delete a registered army.");
        System.out.println("7. List all armies on record.");
        System.out.println("8. Start a battle between two armies.");
        System.out.println("9. Exit the application.");
        System.out.println("\nPlease enter a number between 1 and 9.\n");
        Scanner input = new Scanner(System.in);
        if (input.hasNext()) {
            menuChoice = input.nextInt();
        } else {
            System.out.println("You must enter a number, not text.");
        }
        return menuChoice;
    }

    public void start() throws Exception {
        boolean finished = false;

        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice) {

                case CREATE_ARMY_AND_ADD_COMMANDER:
                    Scanner valueInputA = new Scanner(System.in);
                    System.out.println("Enter new army's name:");
                    String armyNameA = valueInputA.next();
                    System.out.println("Assign a leader the new army, name:");
                    String commanderUnitName = valueInputA.next();
                    this.addArmyToRecord(armyNameA,commanderUnitName);
                    break;

                case ADD_UNITS_TO_AN_ARMY:     // Add units to one of the registered armies.
                    Scanner valueInputB = new Scanner(System.in);
                    System.out.println("What army do you want to add units to?");
                    System.out.println("Available: " + armies.values());
                    String armyNameB = valueInputB.next();

                    System.out.println("Insert number of infantry units to add:");
                    int nrOfInfantryUnits = valueInputB.nextInt();

                    System.out.println("Insert number of ranged units to add:");
                    int nrOfRangedUnits = valueInputB.nextInt();

                    System.out.println("Insert number of cavalry units to add:");
                    int nrOfCavalryUnits = valueInputB.nextInt();

                    String infantryName = "Swordsman";  // Initial names that are overwritten
                    String rangedName = "Bowman";       // if connected with standard armies.
                    String cavalryName = "Horseman";
                    if ((armyNameB.contains("orde")) || (armyNameB.contains("rcish"))) {
                        infantryName = "Grunt";
                        rangedName = "Spearman";
                        cavalryName = "Raider";
                    } else if ((armyNameB.contains("lliance")) || (armyNameB.contains("uman"))) {
                        infantryName = "Footman";
                        rangedName = "Archer";
                        cavalryName = "Knight";
                    }
                    if (nrOfInfantryUnits > 0) {
                        this.addInfantryUnits(armyNameB, infantryName, nrOfInfantryUnits);
                    }
                    if (nrOfRangedUnits > 0) {
                        this.addRangedUnits(armyNameB, rangedName, nrOfRangedUnits);
                    }
                    if (nrOfCavalryUnits > 0) {
                        this.addCavalryUnits(armyNameB, cavalryName, nrOfCavalryUnits);
                    }
                    break;

                case ADD_A_COMMANDER_UNIT_TO_AN_ARMY:
                    Scanner valueInputC = new Scanner(System.in);
                    System.out.println("What army do you want to add the unit to?");
                    System.out.println("Available: " + armies.values());
                    String armyNameC = valueInputC.next();

                    System.out.println("Name of the commanding unit:");
                    String commanderName = valueInputC.next();
                    this.addCommanderUnit(armyNameC, commanderName);

                    break;

                case ADD_STANDARD_SET_OF_UNITS_TO_ARMY:
                    Scanner valueInputD = new Scanner(System.in);
                    System.out.println("What army do you want to add units to?");
                    System.out.println("Available: " + armies.values());
                    String armyNameD = valueInputD.nextLine();
                    this.addStandardSetOfUnitsToArmy(armies.get(armyNameD));
                    break;

                case ADD_OVERPOWERED_WARRIOR:     // Bring an overpowered warrior with specialised specs.
                    Scanner valueInputE = new Scanner(System.in);
                    System.out.println("What army do you want to add units to?");
                    System.out.println("Available: " + armies.values());
                    String armyNameE = valueInputE.nextLine();

                    System.out.println("Enter type of unit the super unit should be " +
                            "[infantry, ranged, cavalry, commander]:");
                    String unitType = valueInputE.nextLine();

                    System.out.println("Dedicate a name for your super unit:");
                    String unitName = valueInputE.nextLine();

                    System.out.println("Enter number of health points:");
                    int healthPoints = valueInputE.nextInt();

                    System.out.println("Enter number of attack power:");
                    int attackPower = valueInputE.nextInt();

                    System.out.println("Enter number of armor points:");
                    int armorPoints = valueInputE.nextInt();

                    System.out.println("Insert number of super units to add:");
                    int nrOfSuperUnits = valueInputE.nextInt();

                    this.addSuperUnit(armyNameE, unitType, unitName, healthPoints, attackPower,
                            armorPoints, nrOfSuperUnits);
                    break;

                case DELETE_ARMY_FROM_RECORD:     // Delete a registered army.
                    Scanner valueInputF = new Scanner(System.in);
                    if (!armies.isEmpty()) {
                        System.out.println("Enter army to delete from register: ");
                        System.out.println("Available: " + armies.values());
                        String armyNameF = valueInputF.nextLine();
                        this.deleteArmyFromRecord(armyNameF);
                    } else {
                        System.out.println("No elements in register yet to delete.");
                    }
                    break;

                case LIST_OF_ARMIES:     // List all armies on record.
                    if (!armies.isEmpty()) {
                        System.out.println("The armies registered are:");
                        this.displayRegisteredArmies();
                    } else {
                        System.out.println("No elements in register yet to display.");
                    }

                    break;

                case START_BATTLE:     // Start a battle between two armies.
                    Scanner valueInputG = new Scanner(System.in);
                    System.out.println("Choose two armies from the register of armies to battle each other."
                    + "\nThe available armies are: " + armies.values());

                    System.out.println("Enter name of first army:");
                    String armyOne = valueInputG.nextLine();

                    System.out.println("Enter name of second army:");
                    String armyTwo = valueInputG.nextLine();

                    if (armies.containsKey(armyOne) & armies.containsKey(armyTwo)) {
                        System.out.println("\n"
                                + this.startABattleBetweenTwoArmies(armies.get(armyOne),armies.get(armyTwo))
                                + " wins the battle!");
                    } else {
                        System.out.println("Two valid armies must be selected before a battle can be initialised.");
                    }
                    break;

                default:
                    System.out.println("Unrecognized menu choice selected..");
                    break;

                case EXIT:
                    System.out.println("Thank you for using the War Games Application!\n");
                    finished = true;
            }
        }
    }

    public void addArmyToRecord(String armyName, String commandingUnit) {
        if (!armies.containsKey(armyName)) {
            armies.put(armyName,new Army(armyName));
            System.out.println(armyName + " added to army records.");
            armies.get(armyName).addUnit(new CommanderUnit(commandingUnit, 180));
        } else {
            System.out.println("Army already exists in the record. Try another name.");
        }
    }

    public void addInfantryUnits(String armyName, String unitName, int numberOfUnits) {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);

            for (int i = 0; i < numberOfUnits; i++) {
                army.addUnit(new InfantryUnit(unitName, 100));
            }
        }
    }

    public void addRangedUnits(String armyName, String unitName, int numberOfUnits) {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);

            for (int i = 0; i < numberOfUnits; i++) {
                army.addUnit(new RangedUnit(unitName, 100));
            }
        }
    }

    public void addCavalryUnits(String armyName, String unitName, int numberOfUnits) {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);

            for (int i = 0; i < numberOfUnits; i++) {
                army.addUnit(new CavalryUnit(unitName, 100));
            }
        }
    }

    public void addCommanderUnit(String armyName, String unitName) {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);
            army.addUnit(new CommanderUnit(unitName, 180));
        }
    }

    public void addStandardSetOfUnitsToArmy(Army army) throws Exception {
        if ((army.getName().contains("orde")) || (army.getName().contains("rcish"))) {
            // Adds x number of grunt units to the horde army.
            for (int i = 0; i < 500; i++) {
                army.addUnit(new InfantryUnit("Grunt", 100));
            }
            // Adds x number of spearman units to the horde army.
            for (int i = 0; i < 200; i++) {
                army.addUnit(new RangedUnit("Spearman", 100));
            }
            // Adds x number of raider units to the horde army.
            for (int i = 0; i < 100; i++) {
                army.addUnit(new CavalryUnit("Raider", 100));
            }
        }
        else if ((army.getName().contains("lliance")) || (army.getName().contains("uman"))) {
            // Adds x number of footman units to the alliance army.
            for (int i = 0; i < 500; i++) {
                army.addUnit(new InfantryUnit("Footman", 100));
            }
            // Adds x number of archer units to the alliance army.
            for (int i = 0; i < 200; i++) {
                army.addUnit(new RangedUnit("Archer", 100));
            }
            // Adds x number of knight units to the alliance army.
            for (int i = 0; i < 100; i++) {
                army.addUnit(new CavalryUnit("Knight", 100));
            }
        }
        else {
            throw new Exception("The given army does not contain one of the standardised army names. " +
                    "This method does therefore not support this army.");
        }
    }

    public void addSuperUnit(String armyName, String unitType, String unitName, int health,
                                   int attack, int armor, int numberOfUnits) {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);

            if ((unitType.contains("infantry")) || (unitType.contains("nfan")) || (unitType.contains("ntry"))) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnit(new InfantryUnit(unitName, health, attack, armor));
                }
            }
            else if ((unitType.equalsIgnoreCase("ranged"))
                                || (unitType.contains("ange")) || (unitType.contains("nged"))) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnit(new RangedUnit(unitName, health, attack, armor));
                }
            }
            else if ((unitType.equalsIgnoreCase("cavalry"))
                                || (unitType.contains("aval")) || (unitType.contains("alry"))) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnit(new CavalryUnit(unitName, health, attack, armor));
                }
            }
            else if ((unitType.equalsIgnoreCase("commander"))
                                || (unitType.contains("omman")) || (unitType.contains("nder"))) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.addUnit(new CommanderUnit(unitName, health, attack, armor));
                }
            }
        }
    }

    public void deleteArmyFromRecord(String armyName) {
        armies.remove(armyName);
    }

    public void displayRegisteredArmies() {
        int nr = 1;
        if (!armies.isEmpty()) {
            for (Army a : armies.values()) {
                System.out.println(nr + ". " + a.getName() + ", " + a.getAllUnits().size() + " unit(s).");
                nr++;
            }
        }
    }

    public Army startABattleBetweenTwoArmies(Army armyOne, Army armyTwo) throws ArmyEmptyOfUnitsException {
        Battle battle = null;
        try {
            battle = new Battle(armyOne,armyTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return battle.simulate(null);
    }

    public static void main(String[] args) throws Exception {
        WarGamesSimpleClient wga = new WarGamesSimpleClient();
        wga.start();
    }
}
