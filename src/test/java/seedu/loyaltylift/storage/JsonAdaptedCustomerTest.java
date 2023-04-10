package seedu.loyaltylift.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalCustomers.BENSON;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.attribute.Note;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Marked;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_CUSTOMER_TYPE = "ind";
    private static final Integer INVALID_POINTS = 1000000;
    private static final Integer INVALID_CUMULATIVE_POINTS = 1000000;

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_CUSTOMER_TYPE = "INDIVIDUAL";
    private static final Integer VALID_POINTS = BENSON.getPoints().value;
    private static final Integer VALID_CUMULATIVE_POINTS = BENSON.getPoints().cumulative;
    private static final Boolean VALID_MARKED = BENSON.getMarked().value;
    private static final String VALID_NOTE = BENSON.getNote().value;

    @Test
    public void toModelType_validCustomerDetails_returnsCustomer() throws Exception {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(BENSON);
        assertEquals(BENSON, customer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, INVALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, null, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, INVALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                INVALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                null, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, INVALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, null, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullCustomerType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(null, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(INVALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_nullPoints_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, null, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPoints_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, INVALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_nullCumulativePoints_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, null, VALID_MARKED,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidCumulativePoints_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, INVALID_CUMULATIVE_POINTS, VALID_MARKED,
                VALID_NOTE);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_nullMarked_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, null,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Marked.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    public void toModelType_nullNote_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_POINTS, VALID_CUMULATIVE_POINTS, VALID_MARKED,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }
}
