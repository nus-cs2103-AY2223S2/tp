package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_ONE;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_THREE;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_TWO;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        EmployeeId outOfBoundEmployeeId = new EmployeeId(Integer.toString(
                model.getFilteredEmployeeList().size() + 1));
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundEmployeeId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Employee employeeToDelete = model.getFilteredEmployeeList().get(0);
        DeleteCommand deleteCommand = new DeleteCommand(EMPLOYEE_ID_ONE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        ModelManager expectedModel = new ModelManager(model.getExecutiveProDb(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_employeeIdNotInList_throwsCommandException() {
        EmployeeId nonExistentEmployeeId = new EmployeeId(
                Integer.toString(model.getExecutiveProDb().getEmployeeList().size() + 1));
        DeleteCommand deleteCommand = new DeleteCommand(nonExistentEmployeeId);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(EMPLOYEE_ID_ONE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(EMPLOYEE_ID_TWO);
        DeleteCommand deleteThirdCommand = new DeleteCommand(EMPLOYEE_ID_THREE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(EMPLOYEE_ID_ONE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
        assertFalse(deleteThirdCommand.equals(deleteSecondCommand));
    }
}
