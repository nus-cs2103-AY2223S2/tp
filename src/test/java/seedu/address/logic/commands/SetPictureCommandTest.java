package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_ONE;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_THREE;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_TWO;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.EmployeeId;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SetPictureCommand}.
 */
public class SetPictureCommandTest {

    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());

    //    @Test
    //    public void execute_validIndexUnfilteredList_success() {
    //        Employee employeeToDelete = model.getFilteredEmployeeList().get(EMPLOYEE_ID_ONE.getEmployeeId());
    //        DeleteCommand deleteCommand = new DeleteCommand(EMPLOYEE_ID_ONE);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);
    //
    //        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        expectedModel.deleteEmployee(employeeToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        EmployeeId outOfBoundEmployeeId = new EmployeeId(Integer.toString(
                model.getFilteredEmployeeList().size() + 1));
        SetPictureCommand setPictureCommand = new SetPictureCommand(outOfBoundEmployeeId);

        assertCommandFailure(setPictureCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Employee employeeToDelete = model.getFilteredEmployeeList().get(EMPLOYEE_ID_ONE.getEmployeeId());
    //        DeleteCommand deleteCommand = new DeleteCommand(EMPLOYEE_ID_ONE);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //        expectedModel.deleteEmployee(employeeToDelete);
    //        showNoEmployee(expectedModel);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }

    @Test
    public void equals() {
        SetPictureCommand setPictureFirstCommand = new SetPictureCommand(EMPLOYEE_ID_ONE);
        SetPictureCommand setPictureSecondCommand = new SetPictureCommand(EMPLOYEE_ID_TWO);
        SetPictureCommand setPictureThirdCommand = new SetPictureCommand(EMPLOYEE_ID_THREE);

        // same object -> returns true
        assertTrue(setPictureFirstCommand.equals(setPictureFirstCommand));

        // same values -> returns true
        SetPictureCommand setPictureFirstCommandCopy = new SetPictureCommand(EMPLOYEE_ID_ONE);
        assertTrue(setPictureFirstCommand.equals(setPictureFirstCommandCopy));

        // different types -> returns false
        assertFalse(setPictureFirstCommand.equals(1));

        // null -> returns false
        assertFalse(setPictureFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(setPictureFirstCommand.equals(setPictureSecondCommand));
        assertFalse(setPictureThirdCommand.equals(setPictureSecondCommand));
    }
}
