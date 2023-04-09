package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCharacter.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.LEEROY;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Name;

class JsonAdaptedCharacterTest {
    private static final String INVALID_NAME = "John Cena!";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = LEEROY.getName().toString();
    private static final JsonAdaptedStats VALID_STATS = new JsonAdaptedStats(LEEROY.getStats());
    private static final JsonAdaptedProgression VALID_PROGRESSION = new JsonAdaptedProgression(LEEROY.getProgression());
    private static final JsonAdaptedInventory VALID_INVENTORY = new JsonAdaptedInventory(LEEROY.getInventory());
    private static final List<JsonAdaptedTag> VALID_TAGS = LEEROY.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validCharacterDetails_returnsCharacter() throws Exception {
        JsonAdaptedCharacter character = new JsonAdaptedCharacter(LEEROY);
        assertEquals(LEEROY, character.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(INVALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_PROGRESSION, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(null, VALID_STATS, VALID_INVENTORY,
                        VALID_PROGRESSION, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, character::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCharacter character =
                new JsonAdaptedCharacter(VALID_NAME, VALID_STATS, VALID_INVENTORY,
                        VALID_PROGRESSION, invalidTags);
        assertThrows(IllegalValueException.class, character::toModelType);
    }
}
