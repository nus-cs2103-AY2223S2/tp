package seedu.address.model.jobs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalDeliveryJobs;

public class DeliveryJobTest {

    private DeliveryJob job;

    @BeforeEach
    void setUp() {
        job = TypicalDeliveryJobs.JOBA;
    }

    @Test
    void testToString() {
        assertEquals(job.toString(), TypicalDeliveryJobs.JOBA.toString());
    }
}
