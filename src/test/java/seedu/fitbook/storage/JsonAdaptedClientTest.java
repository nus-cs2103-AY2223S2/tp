package seedu.fitbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbook.storage.JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.client.TypicalClients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_CALORIE = " ";
    private static final String INVALID_WEIGHT = "0";
    private static final String INVALID_GENDER = " ";
    private static final String INVALID_APPOINTMENT = "af-15-2142";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();

    private static final List<JsonAdaptedAppointment> VALID_APPOINTMENTS = BENSON.getAppointments().stream()
            .map(JsonAdaptedAppointment::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_CALORIE = BENSON.getCalorie().toString();

    @Test
    public void toFitBookModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(BENSON);
        assertEquals(BENSON, client.toFitBookModelType());
    }

    @Test
    public void toFitBookModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedClient client =

                new JsonAdaptedClient(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_APPOINTMENTS,
                        VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test

    public void toFitBookModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }
    @Test
    public void toFitBookModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, INVALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_APPOINTMENTS, null, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }
    @Test
    public void toFitBookModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, INVALID_GENDER, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_APPOINTMENTS, VALID_WEIGHT, null, VALID_CALORIE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, invalidTags);
        assertThrows(IllegalValueException.class, client::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidCalorie_throwsIllegalValueException() {
        JsonAdaptedClient person =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, INVALID_CALORIE, VALID_TAGS);
        String expectedMessage = Calorie.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_nullCalorie_throwsIllegalValueException() {
        JsonAdaptedClient person =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_APPOINTMENTS, VALID_WEIGHT, VALID_GENDER, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Calorie.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toFitBookModelType);
    }

    @Test
    public void toFitBookModelType_invalidAppointments_throwsIllegalValueException() {
        List<JsonAdaptedAppointment> invalidAppointments = new ArrayList<>(VALID_APPOINTMENTS);
        invalidAppointments.add(new JsonAdaptedAppointment(INVALID_APPOINTMENT));
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        invalidAppointments, VALID_WEIGHT, VALID_GENDER, VALID_CALORIE, VALID_TAGS);
        assertThrows(IllegalValueException.class, client::toFitBookModelType);
    }
}
