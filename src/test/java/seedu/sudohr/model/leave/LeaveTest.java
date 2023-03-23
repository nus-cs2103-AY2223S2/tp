package seedu.sudohr.model.leave;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_2;
import static seedu.sudohr.testutil.Assert.assertThrows;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_2;

import org.junit.jupiter.api.Test;

import seedu.sudohr.model.employee.exceptions.DuplicateEmployeeException;
import seedu.sudohr.model.employee.exceptions.EmployeeNotFoundException;
import seedu.sudohr.testutil.EmployeeBuilder;
import seedu.sudohr.testutil.LeaveBuilder;
import seedu.sudohr.testutil.TypicalEmployees;

public class LeaveTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Leave leave = new LeaveBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> leave.getEmployees().remove(0));
    }

    @Test
    public void execute_addEmployee_success() {
        Leave leave = new LeaveBuilder().build();

        leave.addEmployee(TypicalEmployees.ALICE);
        assertTrue(leave.hasEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void execute_addDuplicateEmployee_throwsException() {
        Leave leave = new LeaveBuilder().build();

        leave.addEmployee(new EmployeeBuilder().build());

        assertThrows(DuplicateEmployeeException.class, () -> leave.addEmployee(new EmployeeBuilder().build()));
    }

    @Test
    public void execute_addExactDuplicateEmployee_throwsException() {
        Leave leave = new LeaveBuilder().build();

        leave.addEmployee(TypicalEmployees.ALICE);

        assertThrows(DuplicateEmployeeException.class, () -> leave.addEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void execute_removeEmployee_success() {
        Leave leave = new LeaveBuilder().build();
        leave.addEmployee(new EmployeeBuilder().build());
        leave.deleteEmployee(new EmployeeBuilder().build());
        assertTrue(leave.equals(new LeaveBuilder().build()));
    }

    @Test
    public void execute_removeExactEmployee_success() {
        Leave leave = new LeaveBuilder().build();
        leave.addEmployee(TypicalEmployees.ALICE);

        leave.deleteEmployee(TypicalEmployees.ALICE);
        assertTrue(leave.equals(new LeaveBuilder().build()));
    }

    @Test
    public void execute_nonExistantEmployee_throwsException() {
        Leave leave = new LeaveBuilder().build();
        assertThrows(EmployeeNotFoundException.class, () -> leave.deleteEmployee(TypicalEmployees.ALICE));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Leave leaveTypeOneCopy = new LeaveBuilder(LEAVE_TYPE_1).build();
        assertTrue(LEAVE_TYPE_1.equals(leaveTypeOneCopy));

        // same object -> returns true
        assertTrue(LEAVE_TYPE_1.equals(LEAVE_TYPE_1));

        // null -> returns false
        assertFalse(LEAVE_TYPE_1.equals(null));

        // different type -> returns false
        assertFalse(LEAVE_TYPE_1.equals(5));

        // different employee -> returns false
        assertFalse(LEAVE_TYPE_1.equals(LEAVE_TYPE_2));

        // different date -> returns false
        Leave editedLeave = new LeaveBuilder(
                LEAVE_TYPE_1)
                .withLeaveDate(VALID_LEAVE_DATE_LEAVE_TYPE_2).build();
        assertFalse(LEAVE_TYPE_1.equals(editedLeave));
    }
}
