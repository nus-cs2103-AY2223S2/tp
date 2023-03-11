package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.jobs.DeliveryJob;
import seedu.address.testutil.TypicalDeliveryJobs;

public class DeliveryJobSystemTest {

    private DeliveryJobSystem ds;

    @BeforeEach
    void setUp() {
        ds = new DeliveryJobSystem();
    }

    @Test
    void testAddDeliveryJob() {
        DeliveryJob job = TypicalDeliveryJobs.JOBA;
        assertEquals(ds.getDeliveryJobList().size(), 0);
        ds.addDeliveryJob(job);
        assertEquals(ds.getDeliveryJobList().size(), 1);
    }

    @Test
    void testGetDeliveryJobList() {
        DeliveryJob job = TypicalDeliveryJobs.JOBA;
        ds.addDeliveryJob(job);
        assertEquals(ds.getDeliveryJobList().size(), 1);
        assertEquals(ds.getDeliveryJobList().get(0).toString(), job.toString());
    }
}
