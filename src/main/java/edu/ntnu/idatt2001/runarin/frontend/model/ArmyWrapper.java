package edu.ntnu.idatt2001.runarin.frontend.model;

import edu.ntnu.idatt2001.runarin.backend.armies.Army;
import edu.ntnu.idatt2001.runarin.backend.armies.units.Unit;
import javafx.beans.property.*;
import javafx.collections.FXCollections;

/**
 * This class functions as a wrapper for the Army-class.
 *
 * The wrapper makes it possible to hold data from an army
 * in an alternative format. In this case that means that
 * the application view can update its numbers and list
 * values compatible with a listener.
 *
 * @author Runar Indahl
 * @version 4.0
 * @since 2022-05-18
 */
public class ArmyWrapper {

    private Army army;
    private final ListProperty<Unit> units = new SimpleListProperty<>();
    private final IntegerProperty totalNrOfUnits = new SimpleIntegerProperty();
    private final IntegerProperty totalNrOfInfantry = new SimpleIntegerProperty();
    private final IntegerProperty totalNrOfRanged = new SimpleIntegerProperty();
    private final IntegerProperty totalNrOfCavalry = new SimpleIntegerProperty();
    private final IntegerProperty totalNrOfCommander = new SimpleIntegerProperty();

    /**
     * Instantiates a wrapper for an army.
     *
     * @param army the army to be wrapped.
     */
    public ArmyWrapper(Army army) {
        this.army = army;
    }

    /**
     * Method that updates all values for an army which is presented in the application's view.
     */
    public void updateViewContent() {
        totalNrOfUnits.setValue(army.getUnits().size());
        totalNrOfInfantry.setValue(army.getInfantryUnits().size());
        totalNrOfRanged.setValue(army.getRangedUnits().size());
        totalNrOfCavalry.setValue(army.getCavalryUnits().size());
        totalNrOfCommander.setValue(army.getCommanderUnits().size());

        units.setValue(FXCollections.observableArrayList(army.getUnits()));

        //TODO: Find out why:
        // For some reason, the following line that is calling units must
        // be here in order for the InvalidationListener to work.
        // The toString() itself is not used for anything.
        units.toString();
    }

    /**
     * Set-method to set an army to the wrapper.
     * Updates view content after new army is set.
     *
     * @param army army object to be wrapped.
     */
    public void setArmy(Army army) {
        this.army = army;
        updateViewContent();
    }

    /**
     * Return wrapped army.
     *
     * @return wrapped army.
     */
    public Army getArmy() {
        updateViewContent();
        return army;
    }

    /**
     * Return wrapped list of units as ListProperty<Unit>.
     *
     * @return units, ListProperty<Unit>
     */
    public ListProperty<Unit> getUnits() {
        return units;
    }

    /**
     * Return totalNrOfUnits wrapped as IntegerProperty.
     *
     * @return totalNrOfUnits, IntegerProperty
     */
    public IntegerProperty getTotalNrOfUnitsProperty() {
        return totalNrOfUnits;
    }

    /**
     * Return totalNrOfInfantry wrapped as IntegerProperty.
     *
     * @return totalNrOfInfantry, IntegerProperty
     */
    public IntegerProperty getTotalNrOfInfantryProperty() {
        return totalNrOfInfantry;
    }

    /**
     * Return totalNrOfRanged wrapped as IntegerProperty.
     *
     * @return totalNrOfRanged, IntegerProperty
     */
    public IntegerProperty getTotalNrOfRangedProperty() {
        return totalNrOfRanged;
    }

    /**
     * Return totalNrOfCavalry wrapped as IntegerProperty.
     *
     * @return totalNrOfCavalry, IntegerProperty
     */
    public IntegerProperty getTotalNrOfCavalryProperty() {
        return totalNrOfCavalry;
    }

    /**
     * Return totalNrOfCommander wrapped as IntegerProperty.
     *
     * @return totalNrOfCommander, IntegerProperty
     */
    public IntegerProperty getTotalNrOfCommanderProperty() {
        return totalNrOfCommander;
    }
}
