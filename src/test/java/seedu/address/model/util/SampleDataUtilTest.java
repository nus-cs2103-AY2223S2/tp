package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyEduMate;

public class SampleDataUtilTest {
    @Test
    public void getSampleEduMate() {
        int size = 5;
        ReadOnlyEduMate sampleEm = SampleDataUtil.getSampleEduMate(size);
        assertEquals(sampleEm.getPersonList().size(), size);
    }
}
