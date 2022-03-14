package edu.ntnu.idatt2001.runarin.units;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.runarin.units.specialised.InfantryUnit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class UnitTest {
    
    @Nested
    class UnitConstructorThrowsException {
        
        @Test
        void exceptionThrownIfUnitNameIsEmpty() {
        // Instantiating a unit with no name and expect illegal argument exception message.
            try {
                Unit someTestUnit = new InfantryUnit("", 1);
            } catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), "Name of the warrior cannot be empty.");
            }
        }

        @Test
        void exceptionThrownWhenHealthOfAUnitIsZero() {
            // Instantiating a unit with zero health and expect illegal argument exception message.
            try {
                Unit someTestUnit = new InfantryUnit("Grunt", 0);

            } catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), "Health points of the warrior must be above zero.");
            }
        }

        @Test
        void exceptionThrownWhenAttackPowerIsBelowZero() {
            // Instantiating a unit with negative attack power and expect illegal argument exception message.
            try {
                Unit someTestUnit = new InfantryUnit("Knight", 1, -1, 1);

            } catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), "Attack power of the warrior must be above zero.");
            }
        }

        @Test
        void exceptionThrownWhenArmorPointsIsBelowZero() {
            // Instantiating a unit with negative armor value and expect illegal argument exception message.
            try {
                Unit someTestUnit = new InfantryUnit("Knight", 1, 1, -1);

            } catch (IllegalArgumentException e) {
                assertEquals(e.getMessage(), "Armor points of the warrior cannot be below zero.");
            }
        }
    }

    @Nested
    class UnitAttacksAnotherUnit {

        @Test
        void unitLosesHealthWhenAttackedByAnotherUnitByAttackMethod() {
            Unit attacker = new Unit("Attacker", 100, 15, 0) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
            Unit defender = new Unit("Defender", 100, 15, 0) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
            // Defenders health before an attack is expected to be 100 HP.
            assertEquals(defender.getHealth(), 100);

            attacker.attack(defender);

            //Defenders health after being attacked is expected to be 85 HP.
            assertEquals(defender.getHealth(), 85);
        }

        @Test
        void unitDoesntLooseHealthWhenArmorPointsAreEqualToAttackPower() {
            Unit attacker = new Unit("Attacker", 100, 10, 1) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
            Unit defender = new Unit("Defender", 100, 1, 10) {
                @Override
                public int getAttackBonus() {
                    return 0;
                }

                @Override
                public int getResistBonus() {
                    return 0;
                }
            };
            // Defenders health before an attack is expected to be 100 HP.
            assertEquals(defender.getHealth(), 100);

            attacker.attack(defender);

            // Defenders health after the attack is also expected to be 100 HP.
            assertEquals(defender.getHealth(), 100);
        }
    }
}
