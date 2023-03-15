package seedu.wife.storage;

import static seedu.wife.storage.JsonAdaptedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.wife.testutil.Assert.assertThrows;
import static seedu.wife.testutil.TypicalFood.MEIJI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.wife.commons.exceptions.IllegalValueException;
import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;

public class JsonAdaptedFoodTest {
    private static final String INVALID_NAME = "M@iji";
    private static final String INVALID_UNIT = "+651234";
    private static final String INVALID_QUANTITY = "-1";
    private static final String INVALID_EXPIRY_DATE = "11122022";
    private static final String INVALID_TAG = "#food";

    private static final String VALID_NAME = MEIJI.getName().toString();
    private static final String VALID_UNIT = MEIJI.getUnit().toString();
    private static final String VALID_QUANTITY = MEIJI.getQuantity().toString();
    private static final String VALID_EXPIRY_DATE = MEIJI.getExpiryDate().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = MEIJI.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    /*
    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood(MEIJI);
        assertEquals(MEIJI, food.toModelType());
    }
    */

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(INVALID_NAME, VALID_UNIT, VALID_QUANTITY, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_UNIT, VALID_QUANTITY, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidUnit_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, INVALID_UNIT, VALID_QUANTITY, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = Unit.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullUnit_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, null, VALID_UNIT, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Unit.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_UNIT, INVALID_QUANTITY, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = Quantity.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullQuantity_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, VALID_UNIT, null, VALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Quantity.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_UNIT, VALID_QUANTITY, INVALID_EXPIRY_DATE, VALID_TAGS);
        String expectedMessage = ExpiryDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(VALID_NAME, VALID_UNIT, VALID_QUANTITY, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ExpiryDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_UNIT, VALID_QUANTITY, VALID_EXPIRY_DATE, invalidTags);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
