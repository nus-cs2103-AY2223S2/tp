package seedu.dengue.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.dengue.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.dengue.commons.exceptions.IllegalValueException;
import seedu.dengue.model.person.Age;
import seedu.dengue.model.person.Date;
import seedu.dengue.model.person.Name;
import seedu.dengue.model.person.Postal;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_POSTAL = "+651234";
    private static final String INVALID_AGE = " ";
    private static final String INVALID_DATE = "example.com";
    private static final String INVALID_VARIANT = "#DENV1";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_POSTAL = BENSON.getPostal().toString();
    private static final String VALID_DATE = BENSON.getDate().toString();
    private static final String VALID_AGE = BENSON.getAge().toString();
    private static final List<JsonAdaptedVariant> VALID_VARIANTS = BENSON.getVariants().stream()
            .map(JsonAdaptedVariant::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_POSTAL, VALID_DATE, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_POSTAL, VALID_DATE, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPostal_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_POSTAL, VALID_DATE, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = Postal.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPostal_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_DATE, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Postal.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_POSTAL, INVALID_DATE, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_POSTAL, null, VALID_AGE, VALID_VARIANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_POSTAL, VALID_DATE, INVALID_AGE, VALID_VARIANTS);
        String expectedMessage = Age.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_POSTAL, VALID_DATE, null, VALID_VARIANTS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidVariants_throwsIllegalValueException() {
        List<JsonAdaptedVariant> invalidVariants = new ArrayList<>(VALID_VARIANTS);
        invalidVariants.add(new JsonAdaptedVariant(INVALID_VARIANT));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_POSTAL, VALID_DATE, VALID_AGE, invalidVariants);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
