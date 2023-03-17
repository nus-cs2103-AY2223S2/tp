package seedu.sudohr.testutil;

import seedu.sudohr.model.department.DepartmentName;

/**
 * A utility class containing a list of {@code DepartmentName} objects to be used in tests.
 */
public class TypicalDepartmentNames {
    public static final DepartmentName DEPARTMENT_NAME_FIRST = new DepartmentName("Human Resources");
    public static final DepartmentName DEPARTMENT_NAME_SECOND = new DepartmentName("Engineering");
    public static final DepartmentName DEPARTMENT_NAME_THIRD = new DepartmentName("Sales");

    public static final DepartmentName DEPARTMENT_NAME_NOT_IN_SUDOHR = new DepartmentName("Random Department");
}
