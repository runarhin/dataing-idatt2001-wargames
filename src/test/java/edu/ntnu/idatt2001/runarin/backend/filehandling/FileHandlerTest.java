package edu.ntnu.idatt2001.runarin.backend.filehandling;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class FileHandlerTest {

    @Test
    public void writeStringBuilderToFileThrowsExceptionIfBattleLogIsEmpty() {
            /*
            Test asserts that an empty StringBuilder cannot be written to a file,
            without exception being thrown.
             */
        try {
            StringBuilder stringBuilder = new StringBuilder();
            assertTrue(stringBuilder.isEmpty());
            String fileName = "TestBattleLog-empty.txt";
            FileHandler.writeStringBuilderToFile(stringBuilder, fileName);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Battle log is empty and therefore cannot be written to file.", e.getMessage());
        } catch (IOException e) {
            assertNull(e.getMessage());
        }
    }
}
