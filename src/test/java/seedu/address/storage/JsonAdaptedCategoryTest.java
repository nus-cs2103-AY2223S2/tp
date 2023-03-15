package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCategory.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;


import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.category.MiscellaneousCategory;

public class JsonAdaptedCategoryTest {
    private static final String VALID_CATEGORY = "Category";
    private static final String VALID_SUMMARY = "Description";
    @Test
    public void toModelType_validCategoryDetails_returnsCategory() throws Exception {
        JsonAdaptedCategory category = new JsonAdaptedCategory(
            new UserDefinedCategory(VALID_CATEGORY, VALID_SUMMARY));
        assertEquals(VALID_CATEGORY, category.toModelType().getCategoryName());
        assertEquals(VALID_SUMMARY, category.toModelType().getSummary());
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedCategory category = new JsonAdaptedCategory(
            new UserDefinedCategory(null, VALID_SUMMARY));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Category");
        assertThrows(IllegalValueException.class, expectedMessage, category::toModelType);
    }

    @Test
    public void toModelType_nullSummary_throwsIllegalValueException() {
        JsonAdaptedCategory category = new JsonAdaptedCategory(
            new UserDefinedCategory(VALID_CATEGORY, null));
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Summary");
        assertThrows(IllegalValueException.class, expectedMessage, category::toModelType);
    }

    @Test
    public void toModelType_miscellaneousCategory_returnsCategory() throws Exception {
        JsonAdaptedCategory category = new JsonAdaptedCategory(
            new MiscellaneousCategory());
        assertEquals("Miscellaneous", category.toModelType().getCategoryName());
        assertEquals("Placeholder Description", category.toModelType().getSummary());
    }
}
