package seedu.sudohr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_FIRST;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_NOT_IN_SUDOHR;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_SECOND;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import org.junit.jupiter.api.Test;


import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteDepartmentCommandTest {

    private Model model = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    @Test
    public void execute_existingDepartmentNameUnfilteredList_success() {
        DepartmentName targetDepartmentName = DEPARTMENT_NAME_FIRST;
        Department departmentToDelete = model.getDepartment(targetDepartmentName);
        assert departmentToDelete != null : "Typical SudoHr guaranteed to contain the given department";

        DeleteDepartmentCommand deleteDepartmentCommand = new DeleteDepartmentCommand(targetDepartmentName);

        String expectedMessage = String.format(DeleteDepartmentCommand.MESSAGE_DELETE_DEPARTMENT_SUCCESS, departmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
        expectedModel.removeDepartment(departmentToDelete);

        assertCommandSuccess(deleteDepartmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonExistingDepartmentNameUnfilteredList_throwsCommandException() {

        DeleteDepartmentCommand deleteDepartmentCommand = new DeleteDepartmentCommand(DEPARTMENT_NAME_NOT_IN_SUDOHR);


        assertCommandFailure(deleteDepartmentCommand, model, DeleteDepartmentCommand.MESSAGE_DEPARTMENT_NOT_EXIST);
    }
    @Test
    public void equals() {
        DeleteDepartmentCommand deleteFirstCommand = new DeleteDepartmentCommand(DEPARTMENT_NAME_FIRST);
        DeleteDepartmentCommand deleteSecondCommand = new DeleteDepartmentCommand(DEPARTMENT_NAME_SECOND);


        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDepartmentCommand deleteFirstCommandCopy = new DeleteDepartmentCommand(DEPARTMENT_NAME_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }


}
