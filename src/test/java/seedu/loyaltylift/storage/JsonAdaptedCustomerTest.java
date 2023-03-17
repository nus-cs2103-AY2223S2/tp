package seedu.loyaltylift.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.loyaltylift.storage.JsonAdaptedCustomer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalCustomers.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.customer.Email;
import seedu.loyaltylift.model.customer.Phone;
import seedu.loyaltylift.model.customer.Points;

public class JsonAdaptedCustomerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CUSTOMER_TYPE = "ind";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final Integer VALID_POINTS = BENSON.getPoints().value;
    private static final String VALID_CUSTOMER_TYPE = "INDIVIDUAL";

    @Test
    public void toModelType_validCustomerDetails_returnsCustomer() throws Exception {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(BENSON);
        assertEquals(BENSON, customer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, INVALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, null, VALID_TAGS, VALID_POINTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags, VALID_POINTS);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_nullCustomerType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(null, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_invalidCustomerType_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(INVALID_CUSTOMER_TYPE, VALID_NAME,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, VALID_POINTS);
        assertThrows(IllegalValueException.class, customer::toModelType);
    }

    @Test
    public void toModelType_nullPoints_throwsIllegalValueException() {
        JsonAdaptedCustomer customer = new JsonAdaptedCustomer(VALID_CUSTOMER_TYPE,
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, customer::toModelType);
    }
}
