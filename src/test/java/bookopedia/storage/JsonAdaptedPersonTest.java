package bookopedia.storage;

import static bookopedia.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static bookopedia.testutil.Assert.assertThrows;
import static bookopedia.testutil.TypicalPersons.BENSON;
import static bookopedia.testutil.TypicalPersons.OPTIONAL_AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bookopedia.commons.exceptions.IllegalValueException;
import bookopedia.model.DeliveryStatus;
import bookopedia.model.person.Address;
import bookopedia.model.person.Email;
import bookopedia.model.person.Name;
import bookopedia.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_DELIVERY_STATUS = "HUH";
    private static final int INVALID_NO_OF_DELIVERY_ATTEMPTS_NEGATIVE = -1;

    private static final int INVALID_NO_OF_DELIVERY_ATTEMPTS_OVER = DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN + 1;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedParcel> VALID_TAGS = BENSON.getParcels().stream()
            .map(JsonAdaptedParcel::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedParcelIsFragile> VALID_FRAGILE = BENSON.getParcels().stream()
            .map(JsonAdaptedParcelIsFragile::new)
            .collect(Collectors.toList());

    private static final List<JsonAdaptedParcelIsBulky> VALID_BULKY = BENSON.getParcels().stream()
            .map(JsonAdaptedParcelIsBulky::new)
            .collect(Collectors.toList());
    private static final String VALID_DELIVERY_STATUS = "PENDING";
    private static final int VALID_NO_OF_DELIVERY_ATTEMPTS = DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN;

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());

        person = new JsonAdaptedPerson(OPTIONAL_AMY);
        assertEquals(OPTIONAL_AMY, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                    VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedParcel> invalidParcels = new ArrayList<>(VALID_TAGS);
        invalidParcels.add(new JsonAdaptedParcel(INVALID_TAG));

        List<JsonAdaptedParcelIsFragile> invalidParcelFragile = new ArrayList<>(VALID_FRAGILE);
        invalidParcelFragile.add(new JsonAdaptedParcelIsFragile(false));

        List<JsonAdaptedParcelIsBulky> invalidParcelBulky = new ArrayList<>(VALID_BULKY);
        invalidParcelBulky.add(new JsonAdaptedParcelIsBulky(false));

        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, invalidParcels, invalidParcelFragile, invalidParcelBulky,
                VALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        assertThrows(IllegalArgumentException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidDeliveryStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                INVALID_DELIVERY_STATUS, VALID_NO_OF_DELIVERY_ATTEMPTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullDeliveryStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                null, VALID_NO_OF_DELIVERY_ATTEMPTS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidNoOfDeliveryAttemptsNegative_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, INVALID_NO_OF_DELIVERY_ATTEMPTS_NEGATIVE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidNoOfDeliveryAttemptsOver_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                VALID_DELIVERY_STATUS, INVALID_NO_OF_DELIVERY_ATTEMPTS_OVER);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_deliveryAttemptsMismatchDeliveryStatusReturn_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_FRAGILE, VALID_BULKY,
                DeliveryStatus.FAILED.toString(), DeliveryStatus.NO_OF_ATTEMPTS_BEFORE_RETURN);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
