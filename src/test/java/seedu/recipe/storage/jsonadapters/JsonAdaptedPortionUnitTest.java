package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.PortionUnit;

//@@author alson001
public class JsonAdaptedPortionUnitTest {
    private static final String VALID_UNIT = "grams";

    @Test
    public void constructor_validUnit_returnsJsonAdaptedPortionUnitTest() {
        PortionUnit modelUnit = new PortionUnit(VALID_UNIT);
        JsonAdaptedPortionUnit adaptedUnit = new JsonAdaptedPortionUnit(modelUnit);
        assertEquals(VALID_UNIT, adaptedUnit.getUnit());
    }

    @Test
    public void toModelType_validUnit_success() throws IllegalValueException {
        JsonAdaptedPortionUnit adaptedUnit = new JsonAdaptedPortionUnit(VALID_UNIT);
        PortionUnit modelUnit = adaptedUnit.toModelType();
        assertEquals(VALID_UNIT, modelUnit.getUnit());
    }
}
