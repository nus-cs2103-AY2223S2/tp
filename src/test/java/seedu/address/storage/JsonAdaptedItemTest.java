package seedu.address.storage;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.entity.Name;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedItem.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntities.SPOON;

class JsonAdaptedItemTest {
    private static final String INVALID_NAME = "BR@KEN SPOON";
    private static final int INVALID_COST = -2;
    private static final float INVALID_WEIGHT = -2.33f;

    private static final String VALID_NAME =  SPOON.getName().toString();
    private static final int VALID_COST = SPOON.getCost();
    private static final float VALID_WEIGHT = SPOON.getWeight();
    private static final List<JsonAdaptedTag> VALID_TAGS = SPOON.getTags().stream()
            .map(JsonAdaptedTag::new).collect(Collectors.toList());

    @Test
    public void toModelType_validItemDetails_returnsItem() throws Exception {
        JsonAdaptedItem item = new JsonAdaptedItem(SPOON);
        assertEquals(SPOON, item.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(INVALID_NAME, VALID_COST, VALID_WEIGHT,
                VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(null, VALID_COST, VALID_WEIGHT,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, INVALID_COST, VALID_WEIGHT,
                VALID_TAGS);
        String expectedMessage = JsonAdaptedItem.INVALID_COST;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedItem item = new JsonAdaptedItem(VALID_NAME, VALID_COST, INVALID_WEIGHT,
                VALID_TAGS);
        String expectedMessage = JsonAdaptedItem.INVALID_WEIGHT;
        assertThrows(IllegalValueException.class, expectedMessage, item::toModelType);
    }
}
