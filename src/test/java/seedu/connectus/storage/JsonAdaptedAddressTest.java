package seedu.connectus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.connectus.commons.exceptions.IllegalValueException;
import seedu.connectus.model.person.Address;

public class JsonAdaptedAddressTest {
    @Test
    public void testGetAddress() {
        JsonAdaptedAddress address = new JsonAdaptedAddress("123 Main Street");
        assertEquals("123 Main Street", address.getAddress());

        JsonAdaptedAddress addressWithoutValue = new JsonAdaptedAddress();
        assertNull(addressWithoutValue.getAddress());
    }

    @Test
    public void toModelType_withValidAddress_returnsOptionalOfAddress() throws IllegalValueException {
        String validAddressString = "Blk 123, Clementi Street 14, #12-04";
        JsonAdaptedAddress jsonAdaptedAddress = new JsonAdaptedAddress(validAddressString);
        Optional<Address> optionalAddress = jsonAdaptedAddress.toModelType();
        assertTrue(optionalAddress.isPresent());
        assertEquals(validAddressString, optionalAddress.get().getValue());
    }
}
