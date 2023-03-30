package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class UploadCsvCommandTest {

    @Test
    public void constructor_nullCommand_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new UploadCsvCommand(null));
    }
}
