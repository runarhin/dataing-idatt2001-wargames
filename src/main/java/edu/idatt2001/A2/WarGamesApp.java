package edu.idatt2001.A2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class WarGamesApp {

    private final HashMap<String, Army> armies = new HashMap<>();
    private final int CREATE_ARMY_AND_ADD_COMMANDER = 1;
    private final int ADD_UNITS_TO_AN_ARMY = 2;
    private final int ADD_OVERPOWERED_WARRIOR = 3;
    private final int DELETE_ARMY_FROM_RECORD = 4;
    private final int STATUS_OF_ARMIES = 5;
    private final int START_BATTLE = 6;
    private final int EXIT = 9;


    public WarGamesApp() {
    }

    public void init() {
        //TODO: Fill armies with a commander?
    }

    private int showMenu() {
        int menuChoice = 0;
        System.out.println("\n*** War Games Application ***\n");
        System.out.println("1. Create a new army by giving it a name and assign its leader.");
        System.out.println("2. Add units to one of the registered armies.");
        System.out.println("3. Bring an overpowered warrior with specialised specs.");
        System.out.println("4. Delete a registered army.");
        System.out.println("5. Status of all armies on record.");
        System.out.println("6. Start a battle between two armies.");
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

    public void start() {
        boolean finished = false;

        while (!finished) {
            int menuChoice = this.showMenu();
            switch (menuChoice) {
                case 1:
                    /*this.addArmyToRecord(String armyName);
                    this.addUnits(String armyName);
                    break;
                case 2:
                    this.addGeneralUnits(String armyName, Unit unitType, String unitName, int numberOfUnits);
                    break;
                case 3:
                    this.addOverPoweredUnit(String armyName, String warriorName, int hp, int attack, int armor);
                    break;
                case 4:
                    this.deleteArmyFromRecord(Army armyName);
                    break;
                case 5:
                    this.statusOfAllArmies();
                    break;
                case 6:
                    this.startABattleBetweenTwoArmies(Army armyOne, Army armyTwo);
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;*/
                case 9:
                    System.out.println("Thank you for using the War Games Application!\n");
                    finished = true;
            }
        }
    }

    //TODO: Add some methods which add armies and units to them and so on..

    public void addArmyToRecord(String armyName, String commandingUnit) throws Exception {
        if (!armies.containsKey(armyName)) {
            armies.put(armyName,new Army(armyName));
            System.out.println(armyName + " added to army records.");
            armies.get(armyName).add(new CommanderUnit(commandingUnit, 180));
        } else {
            System.out.println("Army already exists in the record. Try another name.");
        }
    }

    public void addGeneralUnits(String armyName, Unit unitType, String unitName, int numberOfUnits) throws Exception {
        if (armies.containsKey(armyName)) {
            Army army = armies.get(armyName);

            if ((unitType instanceof InfantryUnit)) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.add(new InfantryUnit(unitName, 100));
                }
            } else if ((unitType instanceof RangedUnit)) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.add(new RangedUnit(unitName, 100));
                }
            } else if ((unitType instanceof CavalryUnit)) {
                for (int i = 0; i < numberOfUnits; i++) {
                    army.add(new CavalryUnit(unitName, 100));
                }
            } else {
                System.out.println("Unit type does not exist.");
            }
        }
    }

    public void addOverPoweredUnit() {
    }

    public void deleteArmyFromRecord(String armyName) {
    }

    public void startABattleBetweenTwoArmies(Army armyOne, Army armyTwo) {
    }

    public void displayArmies(Army army) {
    }

    public void fillArmyWithStandardSetOfUnits(Army army) {
    }



    public static void main(String[] args) {
        //TODO: insert some code here.
        WarGamesApp wga = new WarGamesApp();
        wga.start();

    }
}
