package seedu.address;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Version;

public class MainAppTest {
    @Test
    public void getVersionNumber_correct() {
        assertEquals(MainApp.VERSION, new Version(1, 3, 1, true));

    }




}
