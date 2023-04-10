package seedu.address.model.entity.shop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.entity.person.Address;
import seedu.address.model.entity.person.Email;
import seedu.address.model.entity.person.Name;
import seedu.address.model.entity.person.Phone;
import seedu.address.model.entity.shop.exception.DuplicateEmailException;
import seedu.address.model.entity.shop.exception.DuplicatePhoneNumberException;
import seedu.address.model.entity.shop.exception.DuplicatePlateNumberException;
import seedu.address.model.entity.shop.exception.NoNextStateException;
import seedu.address.model.entity.shop.exception.NoPrevStateException;
import seedu.address.model.service.VehicleType;

class ShopTest {
    @Test
    public void testSimpleOperation()
            throws Exception {
        Shop shop = new Shop();
        shop.addCustomer(new Name("James"), new Phone("8797986"), new Email("test@gmail.com"),
                new Address("testAddress"), new HashSet<>());
        shop.addVehicle(1, "testPlate", "blue", "toyota", VehicleType.CAR);
        shop.addService(1, Optional.empty(),
                "test", Optional.empty(),
                Optional.empty());
        shop.addAppointment(1, LocalDateTime.now());
        shop.revert();
        assertTrue(shop.getAppointmentList().isEmpty());
        shop.revert();
        assertTrue(shop.getServiceList().isEmpty());
        shop.revert();
        assertTrue(shop.getVehicleList().isEmpty());
        shop.revert();
        assertTrue(shop.getCustomerList().isEmpty());
        assertThrows(NoPrevStateException.class, shop::revert);
        shop.redo();
        assertEquals(1, shop.getCustomerList().size());
        shop.redo();
        assertEquals(1, shop.getVehicleList().size());
        shop.redo();
        assertEquals(1, shop.getServiceList().size());
        shop.redo();
        assertEquals(1, shop.getAppointmentList().size());
        assertThrows(NoNextStateException.class, shop::redo);
        shop.removeCustomer(1);
        assertTrue(shop.getCustomerList().isEmpty());
        assertTrue(shop.getVehicleList().isEmpty());
        assertTrue(shop.getServiceList().isEmpty());
        assertTrue(shop.getAppointmentList().isEmpty());
    }

    @Test
    public void testDuplicateEmail() throws Exception {
        Shop shop = new Shop();
        shop.addCustomer(new Name("James"), new Phone("8797986"), new Email("test@gmail.com"),
                new Address("testAddress"), new HashSet<>());
        assertThrows(DuplicateEmailException.class, () -> shop.addCustomer(new Name("asdfdasf"), new Phone("87234326"),
                new Email("test@gmail.com"), new Address("testAddress"), new HashSet<>()));
    }

    @Test
    public void testDuplicatePhone() throws Exception {
        Shop shop = new Shop();
        shop.addCustomer(new Name("James"), new Phone("8797986"), new Email("test@gmail.com"),
                new Address("testAddress"), new HashSet<>());
        assertThrows(DuplicatePhoneNumberException.class, () -> shop.addCustomer(new Name("asdfdasf"),
                new Phone("8797986"),
                new Email("fasddsf@gmail.com"), new Address("testAddress"), new HashSet<>()));
    }

    @Test
    public void testDuplicatePlate() throws Exception {
        Shop shop = new Shop();
        shop.addCustomer(new Name("James"), new Phone("8797986"), new Email("test@gmail.com"),
                new Address("testAddress"), new HashSet<>());
        shop.addVehicle(1, "testPlate", "blue", "toyota", VehicleType.CAR);
        assertThrows(DuplicatePlateNumberException.class, () -> shop.addVehicle(1, "TESTPLATE", "blue", "toyota",
                VehicleType.CAR));
    }
}
