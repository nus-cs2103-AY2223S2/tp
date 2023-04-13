package mycelium.mycelium.storage;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import mycelium.mycelium.commons.exceptions.IllegalValueException;
import mycelium.mycelium.commons.util.JsonUtil;
import mycelium.mycelium.model.client.Client;
import mycelium.mycelium.model.client.Email;
import mycelium.mycelium.model.client.Name;
import mycelium.mycelium.model.client.Phone;
import mycelium.mycelium.model.client.YearOfBirth;
import mycelium.mycelium.testutil.ClientBuilder;

public class JsonAdaptedClientTest {
    private static final String INVALID_NAME = "   ";
    private static final String INVALID_MOBILE_NUMBER = "+651234";
    private static final String INVALID_EMAIL = "12312113";
    private static final String INVALID_YEAR_OF_BIRTH = "123";
    private static final String VALID_NAME = WEST.getName().toString();
    private static final String VALID_EMAIL = WEST.getEmail().toString();
    private static final String VALID_YEAR_OF_BIRTH = WEST.getYearOfBirth().get().value;
    private static final String VALID_SOURCE = WEST.getSource().get().toString();
    private static final String VALID_MOBILE_NUMBER = WEST.getMobileNumber().get().value;

    @Test
    public void basicSerialization() throws JsonProcessingException, IOException {
        Client client = new ClientBuilder().build();
        JsonAdaptedClient se = new JsonAdaptedClient(client);
        String jsonStr = JsonUtil.toJsonString(se);
        JsonAdaptedClient de = JsonUtil.fromJsonString(jsonStr, JsonAdaptedClient.class);
        assertEquals(se, de);
    }

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
            String.format(
                JsonAdaptedEntity.MISSING_FIELD_MESSAGE_FORMAT,
                Client.class.getSimpleName(),
                Name.class.getSimpleName());
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
            String.format(
                JsonAdaptedEntity.MISSING_FIELD_MESSAGE_FORMAT,
                Client.class.getSimpleName(),
                Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidYearOfBirth_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, INVALID_YEAR_OF_BIRTH,
                VALID_SOURCE, VALID_MOBILE_NUMBER);
        String expectedMessage = YearOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

    @Test
    public void toModelType_invalidMobileNumber_throwsIllegalValueException() {
        JsonAdaptedClient client =
            new JsonAdaptedClient(VALID_NAME, VALID_EMAIL, VALID_YEAR_OF_BIRTH,
                VALID_SOURCE, INVALID_MOBILE_NUMBER);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, client::toModelType);
    }

}
