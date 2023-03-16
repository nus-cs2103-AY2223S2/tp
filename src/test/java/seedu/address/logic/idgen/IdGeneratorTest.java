package seedu.address.logic.idgen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class IdGeneratorTest {

    @Test
    void generateCustomerId() {
        IdGenerator.reset();
        int a = IdGenerator.generateCustomerId();
        int b = IdGenerator.generateCustomerId();
        assertEquals(1, a);
        assertEquals(2, b);
        IdGenerator.setCustomerIdUnused(b);
        int c = IdGenerator.generateCustomerId();
        assertEquals(2, c);
    }

    @Test
    void generateVehicleId() {
        IdGenerator.reset();
        int a = IdGenerator.generateVehicleId();
        int b = IdGenerator.generateVehicleId();
        assertEquals(1, a);
        assertEquals(2, b);
        IdGenerator.setVehicleIdUnused(b);
        int c = IdGenerator.generateVehicleId();
        assertEquals(2, c);
    }

    @Test
    void generateServiceId() {
        IdGenerator.reset();
        int a = IdGenerator.generateServiceId();
        int b = IdGenerator.generateServiceId();
        assertEquals(1, a);
        assertEquals(2, b);
        IdGenerator.setServiceIdUnused(b);
        int c = IdGenerator.generateServiceId();
        assertEquals(2, c);
    }

    @Test
    void generateAppointmentId() {
        IdGenerator.reset();
        int a = IdGenerator.generateAppointmentId();
        int b = IdGenerator.generateAppointmentId();
        assertEquals(1, a);
        assertEquals(2, b);
        IdGenerator.setAppointmentIdUnused(b);
        int c = IdGenerator.generateAppointmentId();
        assertEquals(2, c);
    }

    @Test
    void saveState() throws IOException, ClassCastException, ClassNotFoundException {
        IdGenerator.reset();
        Path path = Path.of("src", "test", "data", "IdGeneratorStateTest");
        int a = IdGenerator.generateVehicleId();
        int b = IdGenerator.generateCustomerId();
        int c = IdGenerator.generateServiceId();
        IdGenerator.setCustomerIdUnused(b);
        IdGenerator.saveState(path);
        IdGenerator.generateCustomerId();
        IdGenerator.loadState(path);
        int d = IdGenerator.generateCustomerId();
        assertEquals(b, d);
    }
}
