package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class CronTest {
    @Test
    public void singleton_test() {
        assertEquals(Cron.getInstance().hashCode(), Cron.getInstance().hashCode());
    }

    @Test
    public void shutdown() {
        Cron cron = Cron.getInstance();
        cron.stop();
        assertFalse(cron.isRunning());
    }
}
