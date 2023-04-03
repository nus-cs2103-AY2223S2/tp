package seedu.recipe.storage.jsonadapters;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.recipe.testutil.Assert.assertThrows;
import static seedu.recipe.testutil.TypicalRecipes.CACIO_E_PEPE;

import org.junit.jupiter.api.Test;

import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.model.recipe.unit.TimeUnit;

public class JsonAdaptedTimeUnitTest {
    private static final String VALID_UNIT = "hour";
    private static final String INVALID_UNIT = "123";

    @Test
    public void constructor_validUnitString_returnsJsonAdaptedTimeUnit() {
        JsonAdaptedTimeUnit adaptedTimeUnit = new JsonAdaptedTimeUnit(VALID_UNIT);
        assertEquals(VALID_UNIT, adaptedTimeUnit.getUnit());
    }

    @Test
    public void constructor_validTimeUnit_returnsJsonAdaptedTimeUnit() throws Exception {
        JsonAdaptedTimeUnit adaptedTimeUnit = new JsonAdaptedTimeUnit(CACIO_E_PEPE.getDuration().getTimeUnit());
        assertEquals(CACIO_E_PEPE.getDuration().getTimeUnit().toString(), adaptedTimeUnit.getUnit());
    }

    @Test
    public void toModelType_validUnit_success() throws IllegalValueException {
        JsonAdaptedTimeUnit adaptedUnit = new JsonAdaptedTimeUnit(VALID_UNIT);
        TimeUnit expectedUnit = new TimeUnit(VALID_UNIT);
        assertEquals(expectedUnit, adaptedUnit.toModelType());
    }

    //Should be fixed once Filber code is merged
    //    @Test
    //    public void toModelType_invalidUnit_throwsIllegalValueException() {
    //        JsonAdaptedTimeUnit adaptedUnit = new JsonAdaptedTimeUnit(INVALID_UNIT);
    //        assertThrows(IllegalValueException.class, adaptedUnit::toModelType);
    //    }
}
