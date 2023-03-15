package seedu.modtrek.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SampleDataUtilTest {

    @Test
    public void getSampleDegreeProgression() {
        // Sample should be an empty list of modules
        assertEquals("0 modules",
                SampleDataUtil.getSampleDegreeProgression().toString());
    }
}
