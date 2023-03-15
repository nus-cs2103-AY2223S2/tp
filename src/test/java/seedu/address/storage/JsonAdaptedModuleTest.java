package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.TypicalModules;

// TODO: test when duplictes are detected in lectures
// TODO: test when null is detected in lectures
public class JsonAdaptedModuleTest {

    private static final String INVALID_CODE = "Lorem";
    private static final String INVALID_TAG = "H@rd";

    private static final String VALID_CODE = TypicalModules.CS2040S.getCode().code;
    private static final String VALID_NAME = TypicalModules.CS2040S.getName().name;
    private static final List<JsonAdaptedLecture> VALID_LECTURES = TypicalModules.CS2040S.getLectureList().stream()
            .map(JsonAdaptedLecture::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalModules.CS2040S.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule module = new JsonAdaptedModule(TypicalModules.CS2040S);
        assertEquals(TypicalModules.CS2040S, module.toModelType());
    }

    @Test
    public void toModelType_invalidCode_throwsIllegalArgumentException() {
        JsonAdaptedModule module = new JsonAdaptedModule(INVALID_CODE, VALID_NAME, VALID_LECTURES, VALID_TAGS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullCode_throwsIllegalArgumentException() {
        JsonAdaptedModule module = new JsonAdaptedModule(null, VALID_NAME, VALID_LECTURES, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT, "code");
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalArgumentException() {
        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, null, VALID_LECTURES, VALID_TAGS);
        String expectedMessage = String.format(JsonAdaptedModule.MISSING_FIELD_MESSAGE_FORMAT, "name");
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule module =
                new JsonAdaptedModule(VALID_CODE, VALID_NAME, VALID_LECTURES, invalidTags);
        assertThrows(IllegalValueException.class, module::toModelType);
    }

}
