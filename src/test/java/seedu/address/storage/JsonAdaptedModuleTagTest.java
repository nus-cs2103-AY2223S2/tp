package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.model.tag.util.TypicalModuleTag.CFG1002_F;
import static seedu.address.model.tag.util.TypicalModuleTag.CS2101_HA;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class JsonAdaptedModuleTagTest {

    private static final String MODULE_CODE = CS2101_HA.tagName;
    private static final List<JsonAdaptedLesson> JSON_LESSONS =
            CS2101_HA.getImmutableLessons()
                    .stream()
                    .map(JsonAdaptedLesson::new)
                    .collect(Collectors.toList());

    @Test
    public void toModelType_validModuleTagDetails_returnsModuleTag() throws Exception {
        JsonAdaptedModuleTag jsonAdaptedModuleTag = new JsonAdaptedModuleTag(CS2101_HA);

        assertEquals(jsonAdaptedModuleTag.toModelType(), CS2101_HA);

        List<JsonAdaptedLesson> jsonLessons = CS2101_HA.getImmutableLessons()
                .stream()
                .map(JsonAdaptedLesson::new)
                .collect(Collectors.toList());
        jsonAdaptedModuleTag = new JsonAdaptedModuleTag(MODULE_CODE, jsonLessons);

        assertEquals(jsonAdaptedModuleTag.toModelType(), CS2101_HA);
    }

    @Test
    public void toModelType_validEmptyList_returnsModuleTag() throws Exception {
        JsonAdaptedModuleTag jsonAdaptedModuleTag = new JsonAdaptedModuleTag(CFG1002_F);

        assertEquals(jsonAdaptedModuleTag.toModelType(), CFG1002_F);

        jsonAdaptedModuleTag = new JsonAdaptedModuleTag(CFG1002_F.tagName, List.of());

        assertEquals(jsonAdaptedModuleTag.toModelType(), CFG1002_F);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedModuleTag jsonAdaptedModuleTag =
                new JsonAdaptedModuleTag(null, JSON_LESSONS);
        assertThrows(IllegalValueException.class, jsonAdaptedModuleTag::toModelType);
    }

    @Test
    public void toModelType_nullLessons_throwsIllegalValueException() {
        JsonAdaptedModuleTag jsonAdaptedModuleTag =
                new JsonAdaptedModuleTag(CFG1002_F.tagName, null);
        assertThrows(IllegalValueException.class, jsonAdaptedModuleTag::toModelType);
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedModuleTag jsonAdaptedModuleTag =
                new JsonAdaptedModuleTag("CS50", JSON_LESSONS);
        assertThrows(IllegalValueException.class, jsonAdaptedModuleTag::toModelType);
    }
}
