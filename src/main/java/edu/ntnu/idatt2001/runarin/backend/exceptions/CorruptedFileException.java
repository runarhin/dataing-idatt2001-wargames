package edu.ntnu.idatt2001.runarin.backend.exceptions;

import java.io.IOException;

/**
 * Exception handling for corrupt data read from army CSV files. This exception is thrown
 * when the system tries to use an Army file which is not in the correct format.
 *
 * @author Runar Indahl
 * @version 2.0
 * @since 2022-04-03
 */
public class CorruptedFileException extends IOException {

    /**
     * Instantiates a new CorruptFileException-object inherited by
     * IOException super class. Specifies the Exception message.
     *
     * @param message message from the super class, IOException.
     */
    public CorruptedFileException(String message) {
        super(message);
    }
}
