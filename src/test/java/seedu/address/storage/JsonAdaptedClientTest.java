package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.BENSON;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Address;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String INVALID_APPOINTMENT_NAME = "#Meetuptalk";
    private static final String INVALID_MEETUP_DATE = "10-10-2023";

    private static final String INVALID_POLICY_NAME = "#HealthInsuance";
    private static final String INVALID_DATE = "10-10-2023";
    private static final String INVALID_FREQUENCY = "Biweekly";
    private static final String INVALID_PREMIUM = "Hello";

    //private static final String INVALID_POLICY = //; a policy with invalid frequency

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();

    private static final List<JsonAdaptedPolicy> VALID_POLICIES = StreamSupport.stream(
            BENSON.getPolicyList().spliterator(), false).map(JsonAdaptedPolicy::new)
        .collect(Collectors.toList());

    private static final String VALID_APPOINTMENT_NAME = BENSON.getAppointment().getAppointmentName().toString();
    private static final String VALID_MEETUP_DATE = BENSON.getAppointment().getMeetupDate().toString();

    private static final JsonAdaptedAppointment VALID_APPOINTMENT = new JsonAdaptedAppointment(
        VALID_APPOINTMENT_NAME, VALID_MEETUP_DATE);

    @Test
    public void toModelType_validClientDetails_returnsClient() throws IllegalValueException {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_POLICIES,
                VALID_APPOINTMENT);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_PHONE, VALID_EMAIL,
            VALID_ADDRESS, VALID_POLICIES, VALID_APPOINTMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_POLICIES,
                VALID_APPOINTMENT);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_POLICIES,
            VALID_APPOINTMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_POLICIES,
                VALID_APPOINTMENT);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_POLICIES,
            VALID_APPOINTMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_POLICIES,
                VALID_APPOINTMENT);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_POLICIES,
            VALID_APPOINTMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

}
