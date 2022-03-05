package edu.idatt2001.A2;

import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    void exceptionHandlingInConstructorsNameEmpty() {
        System.out.println("The nameless warrior test, first constructor:");
        try {
            Unit someTestUnit = new InfantryUnit("", 1, 1, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nThe nameless warrior test, simplified constructor:");
        try {
            Unit someTestUnit = new InfantryUnit("", 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exceptionHandlingInConstructorsHealthZero() {
        System.out.println("The dead knight test, first constructor:");
        try {
            Unit someTestUnit = new InfantryUnit("Knight", 0, 1, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\nThe dead knight test, simplified constructor:");
        try {
            Unit someTestUnit = new InfantryUnit("Knight", 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exceptionHandlingInConstructorsAttackPowerBelowZero() {
        System.out.println("The weaponless knight test");
        try {
            Unit someTestUnit = new InfantryUnit("Knight", 1, -1, 1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void exceptionHandlingInConstructorsArmorPointsBelowZero() {
        System.out.println("The naked knight test");
        try {
            Unit someTestUnit = new InfantryUnit("Knight", 1, 1, -1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void unitLosesHealthWhenAttackedByAnotherUnitByAttackMethod() {
        try {
            Unit someTestFootman = new InfantryUnit("Footman", 100, 15, 10);
            Unit someTestGrunt = new InfantryUnit("Grunt", 100, 15, 10);

            // Grunts health before footman's attack is expected to be 100 HP.
            assert(someTestGrunt.getHealth() == 100);

            someTestFootman.attack(someTestGrunt);

            // Grunts health after being attacked is expected to be 94 HP.
            assert(someTestGrunt.getHealth() == 94);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void returnToString() {
        try {
            Unit someTestUnit1 = new InfantryUnit("Footman", 120, 13, 12);
            Unit someTestUnit2 = new InfantryUnit("Footman", 90);
            System.out.println("\n" + someTestUnit1.toString());
            System.out.println("\n" + someTestUnit2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
