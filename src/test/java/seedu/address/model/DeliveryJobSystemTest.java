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
        assertEquals(0, ds.getDeliveryJobList().size());
        ds.addDeliveryJob(job);
        assertEquals(1, ds.getDeliveryJobList().size());
    }

    @Test
    void testGetDeliveryJobList() {
        DeliveryJob job = TypicalDeliveryJobs.JOBA;
        ds.addDeliveryJob(job);
        assertEquals(1, ds.getDeliveryJobList().size());
        assertEquals(ds.getDeliveryJobList().get(0).toString(), job.toString());
    }

    @Test
    void testRemoveDeliveryJob() {
        DeliveryJob job = TypicalDeliveryJobs.JOBA;
        ds.addDeliveryJob(job);
        assertEquals(1, ds.getDeliveryJobList().size());
        ds.removeDeliveryJob(job);
        assertEquals(0, ds.getDeliveryJobList().size());
    }

    @Test
    void testResetData() {
        DeliveryJobSystem djs = new DeliveryJobSystem();
        djs.addDeliveryJob(TypicalDeliveryJobs.JOBA);
        djs.addDeliveryJob(TypicalDeliveryJobs.JOBB);
        assertEquals(2, djs.getDeliveryJobList().size());
        assertEquals(0, ds.getDeliveryJobList().size());
        ds.resetData(djs);
        assertEquals(2, ds.getDeliveryJobList().size());
    }
}
