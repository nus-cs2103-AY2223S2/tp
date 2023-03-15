package seedu.sudohr.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.sudohr.storage.JsonAdaptedDepartment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalDepartments.ENGINEERING;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.exceptions.IllegalValueException;
import seedu.sudohr.model.department.DepartmentName;

public class JsonAdaptedDepartmentTest {
    private static final String INVALID_DEPARTMENT_NAME = "@Engineering";

    private static final String VALID_NAME = ENGINEERING.getName().toString();
    private static final List<JsonAdaptedPerson> VALID_EMPLOYEES = ENGINEERING.getEmployees().stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validDepartmentDetails_returnsDepartment() throws Exception {
        JsonAdaptedDepartment department = new JsonAdaptedDepartment(ENGINEERING);
        assertEquals(ENGINEERING, department.toModelType());
    }

    @Test
    public void toModelType_invalidDepartmentName_throwsIllegalValueException() {
        JsonAdaptedDepartment department = new JsonAdaptedDepartment(INVALID_DEPARTMENT_NAME, VALID_EMPLOYEES);
        String expectedMessage = DepartmentName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, department::toModelType);
    }

    @Test
    public void toModelType_nullDepartmentName_throwsIllegalValueException() {
        JsonAdaptedDepartment department = new JsonAdaptedDepartment(null, VALID_EMPLOYEES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DepartmentName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, department::toModelType);
    }
}
