package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTank.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTanks.TANK_A;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tank.TankName;

/**
 * Test class for {@code JsonAdaptedTank}
 */
class JsonAdaptedTankTest {
    private static final String INVALID_TANK_NAME = "T@nk_A";

    @Test
    public void toModelType_validTankDetails_returnsTank() throws Exception {
        JsonAdaptedTank tank = new JsonAdaptedTank(TANK_A);
        assertEquals(TANK_A, tank.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTank tank =
                new JsonAdaptedTank(INVALID_TANK_NAME);
        String expectedMessage = TankName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tank::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTank tank = new JsonAdaptedTank((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TankName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tank::toModelType);
    }
}
