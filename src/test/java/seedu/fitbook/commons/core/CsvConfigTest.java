package seedu.fitbook.commons.core;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CsvConfigTest {
    @Test
    public void equalsMethod() {
        CsvConfig defaultCsvConfig = new CsvConfig();
        assertNotNull(defaultCsvConfig);
        assertTrue(defaultCsvConfig.equals(defaultCsvConfig));
    }
}
