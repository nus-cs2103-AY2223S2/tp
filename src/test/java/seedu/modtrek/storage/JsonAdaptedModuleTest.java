package seedu.modtrek.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modtrek.storage.JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.MA2002;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.modtrek.commons.exceptions.IllegalValueException;
import seedu.modtrek.model.module.Code;
import seedu.modtrek.model.module.Credit;
import seedu.modtrek.model.module.Grade;
import seedu.modtrek.model.module.SemYear;


public class JsonAdaptedModuleTest {
    private static final String INVALID_NAME = "CS1";
    private static final String INVALID_CREDIT = "+651234";
    private static final String INVALID_GRADE = " ";
    private static final String INVALID_SEMYEAR = "year 1";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = MA2002.getCode().toString();
    private static final String VALID_CREDIT = MA2002.getCredit().toString();
    private static final String VALID_SEMYEAR = MA2002.getSemYear().toString();
    private static final String VALID_GRADE = MA2002.getGrade().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MA2002.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(MA2002);
        assertEquals(MA2002, module.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(INVALID_NAME, VALID_CREDIT, VALID_GRADE, VALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = Code.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_CREDIT, VALID_GRADE, VALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Code.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidCredit_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, INVALID_CREDIT, VALID_GRADE, VALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = Credit.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCredit_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, null, VALID_GRADE, VALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Credit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidSemYear_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_CREDIT, VALID_GRADE, INVALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = SemYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullSemYear_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, VALID_CREDIT, VALID_GRADE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SemYear.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_CREDIT, VALID_SEMYEAR, INVALID_GRADE, VALID_TAGS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_NAME, VALID_CREDIT, null, VALID_SEMYEAR, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_NAME, VALID_CREDIT, VALID_GRADE, VALID_SEMYEAR, invalidTags);
        assertThrows(IllegalValueException.class, module::toModelType);
    }

}
