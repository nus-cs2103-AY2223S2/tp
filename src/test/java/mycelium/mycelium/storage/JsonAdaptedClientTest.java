package mycelium.mycelium.storage;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalPersons.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.model.person.Email;
import mycelium.mycelium.model.person.Name;



public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "K@NY3 W3ST";
    private static final String INVALID_MOBILE_NUMBER = "+651234";
    private static final String INVALID_EMAIL = "12312113";
    private static final String INVALID_YEAR_OF_BIRTH = "123";
    // TODO INVALID_SOURCE
    private static final String VALID_NAME = WEST.getName().toString();
    private static final String VALID_EMAIL = WEST.getEmail().toString();
    private static final String VALID_YEAR_OF_BIRTH = WEST.getYearOfBirth().toString();
    private static final String VALID_SOURCE = WEST.getSource().toString();
    private static final String VALID_MOBILE_NUMBER = WEST.getMobileNumber().toString();


    @Test
    public void toModelType_validClientDetails_returnsClient() throws Exception {
        JsonAdaptedClient client = new JsonAdaptedClient(WEST);
        assertEquals(WEST, client.toModelType());
    }
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(INVALID_NAME, VALID_EMAIL, VALID_YEAR_OF_BIRTH,
                        VALID_SOURCE, VALID_MOBILE_NUMBER);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(null, VALID_EMAIL, VALID_YEAR_OF_BIRTH,
                VALID_SOURCE, VALID_MOBILE_NUMBER);
        String
                expectedMessage =
                String.format(JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedClient client =
                new JsonAdaptedClient(VALID_NAME, INVALID_EMAIL, VALID_YEAR_OF_BIRTH,
                        VALID_SOURCE, VALID_MOBILE_NUMBER);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, null, VALID_YEAR_OF_BIRTH,
                VALID_SOURCE, VALID_MOBILE_NUMBER);
        String
                expectedMessage =
                String.format(JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_nullYearOfBirth_throwsIllegalValueException() {
        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, null,
                VALID_SOURCE, VALID_MOBILE_NUMBER);
        String
                expectedMessage =
                String.format(JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT, YearOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    //    @Test
    //    public void toModelType_invalidYearOfBirth_throwsIllegalValueException() {
    //        JsonAdaptedClient client =
    //                new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, INVALID_YEAR_OF_BIRTH,
    //                        VALID_SOURCE, VALID_MOBILE_NUMBER);
    //        String expectedMessage = YearOfBirth.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_invalidMobileNumber_throwsIllegalValueException() {
    //        JsonAdaptedClient client =
    //                new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, VALID_YEAR_OF_BIRTH,
    //                        VALID_SOURCE, INVALID_MOBILE_NUMBER);
    //        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
    //        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    //    }
    //
    //    @Test
    //    public void toModelType_nullMobileNumber_throwsIllegalValueException() {
    //        JsonAdaptedClient client = new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, VALID_YEAR_OF_BIRTH,
    //                VALID_SOURCE, null);
    //        String
    //                expectedMessage =
    //                String.format(JsonAdaptedClient.MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
    //        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    //    }
}
