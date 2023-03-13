package codoc.storage;

import static codoc.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static codoc.testutil.Assert.assertThrows;
import static codoc.testutil.TypicalPersons.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import codoc.commons.exceptions.IllegalValueException;
import codoc.model.person.Address;
import codoc.model.person.Email;
import codoc.model.person.Github;
import codoc.model.person.Name;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GITHUB = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MODULE = "CS!!0!S";
    private static final String INVALID_SKILL = "Àpython";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GITHUB = BENSON.getGithub().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedSkill> VALID_SKILLS = BENSON.getSkills().stream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedModule> VALID_MODULES = BENSON.getModules().stream()
            .map(JsonAdaptedModule::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_GITHUB, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKILLS, VALID_MODULES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_GITHUB, VALID_EMAIL, VALID_ADDRESS,
                VALID_SKILLS, VALID_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithub_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_GITHUB, VALID_EMAIL, VALID_ADDRESS,
                        VALID_SKILLS, VALID_MODULES);
        String expectedMessage = Github.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GITHUB, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_SKILLS, VALID_MODULES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_GITHUB, null, VALID_ADDRESS,
                VALID_SKILLS, VALID_MODULES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GITHUB, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_SKILLS, VALID_MODULES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSkills_throwsIllegalValueException() {
        List<JsonAdaptedSkill> invalidSkills = new ArrayList<>(VALID_SKILLS);
        invalidSkills.add(new JsonAdaptedSkill(INVALID_SKILL));
        List<JsonAdaptedModule> invalidMods = new ArrayList<>(VALID_MODULES);
        invalidMods.add(new JsonAdaptedModule(INVALID_MODULE));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_GITHUB, VALID_EMAIL, VALID_ADDRESS,
                        invalidSkills, invalidMods);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
