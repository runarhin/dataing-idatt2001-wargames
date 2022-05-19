package edu.ntnu.idatt2001.runarin.backend.exceptions;

import java.io.IOException;

/**
 * Exception handling for showing dialog window in the War Games GUI. This exception is thrown
 * when the system tries to initialise a simulation while an army has no units.
 *
 * @author Runar Indahl
 * @version 3.0
 * @since 2022-04-21
 */
public class ArmyEmptyOfUnitsException extends IOException {
    /**
     * Instantiates a new ArmyEmptyOfUnitsException-object inherited
     * by IOException super class. Specifies the Exception message.
     *
     * @param message message from the super class, IOException.
     */
    public ArmyEmptyOfUnitsException(String message) {
        super(message);
    }
}
