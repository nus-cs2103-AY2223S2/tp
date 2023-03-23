package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.testutil.TypicalModules;

public class JsonAdaptedModuleTest {

    private static final String INVALID_CODE = "Lorem";
    private static final String INVALID_TAG = "H@rd";

    private static final ReadOnlyModule ORIGINAL_MODULE = TypicalModules.getCs2040s();
    private static final String VALID_CODE = ORIGINAL_MODULE.getCode().code;
    private static final String VALID_NAME = ORIGINAL_MODULE.getName().name;
    private static final List<JsonAdaptedLecture> VALID_LECTURES = ORIGINAL_MODULE.getLectureList()
            .stream().map(JsonAdaptedLecture::new).collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = ORIGINAL_MODULE.getTags()
            .stream().map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validModuleDetails_returnsModule() throws Exception {
        JsonAdaptedModule adaptedModule = new JsonAdaptedModule(ORIGINAL_MODULE);
        assertEquals(ORIGINAL_MODULE, adaptedModule.toModelType());
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

    @Test
    public void toModelType_duplicateLectures_throwsIllegalValueException() {
        List<JsonAdaptedLecture> duplicatedLectures = new ArrayList<>(VALID_LECTURES);
        duplicatedLectures.addAll(VALID_LECTURES);

        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_NAME, duplicatedLectures, VALID_TAGS);
        String expectedMessage = JsonAdaptedModule.MESSAGE_DUPLICATE_LECTURE;
        assertThrows(IllegalValueException.class, expectedMessage, module::toModelType);
    }

    @Test
    public void toModelType_nullInLectures_nullValueIgnored() throws Exception {
        List<JsonAdaptedLecture> lecturesContainingNull = new ArrayList<>(VALID_LECTURES);
        lecturesContainingNull.add(null);

        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_NAME, lecturesContainingNull, VALID_TAGS);
        assertEquals(ORIGINAL_MODULE, module.toModelType());
    }

    @Test
    public void toModelType_duplicateTags_duplicatesIgnored() throws Exception {
        List<JsonAdaptedTag> duplicatedTags = new ArrayList<>(VALID_TAGS);
        duplicatedTags.addAll(VALID_TAGS);

        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_NAME, VALID_LECTURES, duplicatedTags);
        assertEquals(ORIGINAL_MODULE, module.toModelType());
    }

    @Test
    public void toModelType_duplicateTags_nullValueIgnored() throws Exception {
        List<JsonAdaptedTag> tagsContainingNull = new ArrayList<>(VALID_TAGS);
        tagsContainingNull.add(null);

        JsonAdaptedModule module = new JsonAdaptedModule(VALID_CODE, VALID_NAME, VALID_LECTURES, tagsContainingNull);
        assertEquals(ORIGINAL_MODULE, module.toModelType());
    }

}
