package fasttrack.storage;

import static fasttrack.storage.JsonAdaptedCategory.MISSING_FIELD_MESSAGE_FORMAT;
import static fasttrack.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fasttrack.commons.exceptions.IllegalValueException;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.category.UserDefinedCategory;

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
        assertEquals("Misc", category.toModelType().getCategoryName());
        assertEquals("Placeholder Description", category.toModelType().getSummary());
    }
}
