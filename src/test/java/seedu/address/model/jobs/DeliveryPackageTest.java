package seedu.address.model.jobs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeliveryPackageTest {

    private DeliveryPackage item;

    @BeforeEach
    void setUp() {
        item = new DeliveryPackage(
            0,
            0,
            0,
            0);
    }

    @Test
    void testToString() {
        System.out.println(item);
    }
}
