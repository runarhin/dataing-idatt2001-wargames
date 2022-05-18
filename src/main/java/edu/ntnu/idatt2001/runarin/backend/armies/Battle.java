package edu.ntnu.idatt2001.runarin.backend.armies;

import edu.ntnu.idatt2001.runarin.backend.exceptions.ArmyEmptyOfUnitsException;
import edu.ntnu.idatt2001.runarin.backend.filehandling.FileHandler;
import edu.ntnu.idatt2001.runarin.backend.units.TerrainType;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.CavalryUnit;
import edu.ntnu.idatt2001.runarin.backend.units.specialised.RangedUnit;
import edu.ntnu.idatt2001.runarin.backend.units.Unit;

import java.io.IOException;
import java.util.Random;

/**
 * A class Battle.
 * This class represents a battle between two armies.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class Battle {

    private final Army armyOne;
    private final Army armyTwo;

    /**
     * Constructor which instantiate a Battle between two armies.
     *
     * @param armyOne one out of two armies battling for survival.
     * @param armyTwo second out of two armies battling for survival.
     */
    public Battle(Army armyOne, Army armyTwo) throws IOException {
        if (armyOne == null || armyTwo == null)
            throw new IOException("Two armies must be initialised to run simulation.");
        if (armyOne.getName().equals(armyTwo.getName()))
            throw new IllegalArgumentException("An army cannot battle itself. Choose one other army.");
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Simulates a battle between two armies.
     * The method randomises which warrior to attack first for every round. When a warrior
     * dies, it is removed from the army's ArrayList and is replaced by a new warrior.
     * The simulation progresses until one of the armies has no units left.
     * The battle is logged to the file BattleLog.txt.
     *
     * @return the name of the winning army.
     */
    public Army simulate(TerrainType terrain) throws ArmyEmptyOfUnitsException, NullPointerException {
        if (armyOne == null) throw new NullPointerException("Army one is null.");
        if (armyTwo == null) throw new NullPointerException("Army two is null.");
        if (!armyOne.hasUnits()) throw new ArmyEmptyOfUnitsException(armyOne.getName() +
                " has no units left to fight in the simulation. " +
                "\nPress the \"Reinitialize armies\"-, \"Add...\" " +
                "or \"Add units from file\"-button to rebuild the army.");
        if (!armyTwo.hasUnits()) throw new ArmyEmptyOfUnitsException(armyTwo.getName() +
                " has no units left to fight in the simulation. " +
                "\nPress the \"Reinitialize armies\"-, \"Add...\" " +
                "or \"Add units from file\"-button to rebuild the army.");

        int roundCount = 0;
        Random rand = new Random();
        Unit warriorArmyOne = armyOne.getRandom();
        Unit warriorArmyTwo = armyTwo.getRandom();
        String clashReport;
        StringBuilder battleLog = new StringBuilder();

        // Loop run as long as there is units left in both armies.
        while (armyOne.hasUnits() & armyTwo.hasUnits()) {

            roundCount++;
            battleLog.append("\n\nRound ").append(roundCount);
            battleLog.append("      ").append(warriorArmyOne.getName())
                    .append(" [").append(warriorArmyOne.getHealth())
                    .append(" hp]").append("  VS.  ").append(warriorArmyTwo.getName())
                    .append(" [").append(warriorArmyTwo.getHealth()).append(" hp]");

            // Randomises who attacks first.
            if (rand.nextInt(0, 2) == 0) {
                if (warriorArmyOne.getHealth() > 0) {
                    clashReport = clash(warriorArmyOne, warriorArmyTwo, terrain);
                    battleLog.append(clashReport);
                }
                if (warriorArmyTwo.getHealth() > 0) {
                    clashReport = clash(warriorArmyTwo, warriorArmyOne, terrain);
                    battleLog.append(clashReport);
                }
            } else {
                if (warriorArmyTwo.getHealth() > 0) {
                    clashReport = clash(warriorArmyTwo, warriorArmyOne, terrain);
                    battleLog.append(clashReport);
                }
                if (warriorArmyOne.getHealth() > 0) {
                    clashReport = clash(warriorArmyOne, warriorArmyTwo, terrain);
                    battleLog.append(clashReport);
                }
            }
            // After an attack, see if a warrior died or not. If a warrior died, it is replaced.
            if (warriorArmyTwo.getHealth() <= 0) {
                battleLog.append("\n     ").append(warriorArmyTwo.getName())
                        .append(" died of fatal blow from ")
                        .append(warriorArmyOne.getName()).append("!");

                armyTwo.remove(warriorArmyTwo);
                if (armyTwo.hasUnits()) {
                    warriorArmyTwo = armyTwo.getRandom();
                }
                if (warriorArmyOne instanceof RangedUnit) {
                    ((RangedUnit) warriorArmyOne).resetAttacked();
                }
                if (warriorArmyOne instanceof CavalryUnit) {
                    ((CavalryUnit) warriorArmyOne).resetChargeAbility();
                }
            }
            if (warriorArmyOne.getHealth() <= 0) {
                battleLog.append("\n     ").append(warriorArmyOne.getName())
                        .append(" died of fatal blow from ")
                        .append(warriorArmyTwo.getName()).append("!");

                armyOne.remove(warriorArmyOne);
                if (armyOne.hasUnits()) {
                    warriorArmyOne = armyOne.getRandom();
                }
                if (warriorArmyTwo instanceof RangedUnit) {
                    ((RangedUnit) warriorArmyTwo).resetAttacked();
                }
                if (warriorArmyTwo instanceof CavalryUnit) {
                    ((CavalryUnit) warriorArmyTwo).resetChargeAbility();
                }
            }
        }

        if (!armyOne.hasUnits()) {
            battleLog.append("\n\n  ").append(armyTwo.getName())
                    .append(" wins the battle!"); // Add to log who the winner is.
            try {
                FileHandler.writeStringBuilderToFile(battleLog,"/BattleLog.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return armyTwo;
        } else {
            battleLog.append("\n\n  ").append(armyOne.getName())
                    .append(" wins the battle!"); // Add to log who the winner is.
            try {
                FileHandler.writeStringBuilderToFile(battleLog, "/BattleLog.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return armyOne;
        }
    }

    /**
     * Helper method to make the simulate()-method easier to read when randomizing attacks.
     *
     * @param attacker attacking unit.
     * @param defender defending unit.
     * @return string of text to be viewed either in terminal, battle log or GUI output window.
     */
    private String clash(Unit attacker, Unit defender, TerrainType terrain) {
        int defenderOldHealth = defender.getHealth();
        attacker.attack(defender, terrain);
        int defenderNewHealth = defender.getHealth();
        return "\n" + "     "
                + attacker.getName() + " strikes and deals " + (defenderOldHealth - defenderNewHealth)
                + " damage to " + defender.getName() + " [" + defenderNewHealth + " hp]";
    }

    /**
     * toString-method which return the names of the two clashing armies.
     *
     * @return names of the battling armies.
     */
    @Override
    public String toString() {
        return "Battle between " + armyOne + " and " + armyTwo + "";
    }
}
