package seedu.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.internship.commons.core.Version;

public class MainAppTest {
    @Test
    public void getVersionNumber_correct() {
        assertEquals(MainApp.VERSION, new Version(1, 3, 3, true));

    }
}
