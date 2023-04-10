package seedu.address.logic.commands.jobs;

import seedu.address.logic.commands.person.ClearCommand;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ImportDeliveryJobCommandTest {
    @Test
    public void constructor_nullJob_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportDeliveryJobCommand(null));
    }

    @Test
    public void equals() throws IOException {
        File file = new File("testimportfile.csv");
        final ImportDeliveryJobCommand importCommand = new ImportDeliveryJobCommand(file);

        assertTrue(importCommand.equals(importCommand));
        assertFalse(importCommand.equals(null));
        assertFalse(importCommand.equals(new ClearCommand()));

        File testFile = new File("test.txt");
        testFile.createNewFile();
        assertFalse(importCommand.equals(new ImportDeliveryJobCommand(testFile)));
    }
}
