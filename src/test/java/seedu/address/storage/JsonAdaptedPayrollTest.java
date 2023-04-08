package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Payroll;

public class JsonAdaptedPayrollTest {
    private static final String INVALID_PAYROLL = "-1 15";
    private static final String VALID_PAYROLL = "1000 15";
    @Test
    public void toModelType_validPayroll_returnsPayroll() throws Exception {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_PAYROLL);
        assertEquals(new Payroll(VALID_PAYROLL), payroll.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(INVALID_PAYROLL);
        String expectedMessage = Payroll.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, payroll::toModelType);
    }
}
