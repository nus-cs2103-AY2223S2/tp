package seedu.address.model.jobs;

import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryJobTest {

    private DeliveryJob job;

    @BeforeEach
    void setUp() {
        job = new DeliveryJob(
            ALICE,
            null,
            null,
            0);
    }

    @Test
    void testToString() {
        System.out.println(job);
    }
}
