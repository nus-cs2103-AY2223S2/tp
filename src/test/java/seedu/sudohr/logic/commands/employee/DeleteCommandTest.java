package seedu.sudohr.logic.commands.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.sudohr.testutil.TypicalDepartments.EMPLOYEE_IN_HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.SALES;
import static seedu.sudohr.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalLeave.EMPLOYEE_ON_LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.EMPLOYEE_ON_LEAVE_TYPE_2_AND_3;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_2;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_3;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.TypicalDepartments;
import seedu.sudohr.testutil.TypicalEmployees;
import seedu.sudohr.testutil.TypicalLeave;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(TypicalEmployees.getTypicalSudoHr(), new UserPrefs());
    private Model modelWithDepts = new ModelManager(TypicalDepartments.getTypicalSudoHr(), new UserPrefs());
    private Model modelWithLeaves = new ModelManager(TypicalLeave.getTypicalSudoHr(), new UserPrefs());

    @Test
    public void execute_validIdUnfilteredList_success() {
        Employee employeeToDelete = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        ModelManager expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIdUnfilteredList_throwsCommandException() {
        DeleteCommand deleteCommand = new DeleteCommand(TypicalEmployees.ID_NOT_EXIST);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_EMPLOYEE_TO_DELETE_NOT_FOUND);
    }

    @Test
    public void execute_validIdFilteredList_success() {
        showEmployeeAtIndex(model, INDEX_FIRST_PERSON);

        Employee employeeToDelete = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        Model expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);
        showNoEmployee(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIdCascadeToDepartment_success() {
        Employee employeeToDelete = EMPLOYEE_IN_HUMAN_RESOURCES;
        assertTrue(employeeToDelete != null);
        // ensure employee exists in HUMAN_RESOURCES
        // Note: Access a department through the model since each model clones the department to allow for re-use
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToDelete));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        Model expectedModel = new ModelManager(modelWithDepts.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        // check deletion command
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());
        assertCommandSuccess(deleteCommand, modelWithDepts, expectedMessage, expectedModel);

        // check cascaded to department level
        assertTrue(!modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToDelete));
    }

    @Test
    public void execute_cascadeDeleteEmployeeFromTwoDepartments_success() {
        Employee employeeToDelete = EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES;
        assertTrue(employeeToDelete != null);
        // ensure employee exists in the two departments
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToDelete));
        assertTrue(modelWithDepts.getDepartment(SALES.getName()).hasEmployee(employeeToDelete));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        Model expectedModel = new ModelManager(modelWithDepts.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        // check deletion command
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());
        assertCommandSuccess(deleteCommand, modelWithDepts, expectedMessage, expectedModel);

        // check cascaded to department level
        assertTrue(!modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToDelete));
        assertTrue(!modelWithDepts.getDepartment(SALES.getName()).hasEmployee(employeeToDelete));
    }

    @Test
    public void execute_validIdCascadeInLeave_success() {
        Employee employeeToDelete = EMPLOYEE_ON_LEAVE_TYPE_1;
        assertTrue(employeeToDelete != null);
        /// ensure employee exists in the list of employee on leave that day
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_1.getDate()).hasEmployee(employeeToDelete));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        Model expectedModel = new ModelManager(modelWithLeaves.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        // check deletion command
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());
        assertCommandSuccess(deleteCommand, modelWithLeaves, expectedMessage, expectedModel);

        // check cascaded to leave level
        assertTrue(!modelWithLeaves.getLeave(LEAVE_TYPE_1.getDate()).hasEmployee(employeeToDelete));
    }

    @Test
    public void execute_cascadeDeleteEmployeeInTwoLeaves_success() {
        Employee employeeToDelete = EMPLOYEE_ON_LEAVE_TYPE_2_AND_3;
        assertTrue(employeeToDelete != null);
        // ensure employee exists in the respective list of employees for each leave
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_2.getDate()).hasEmployee(employeeToDelete));
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_3.getDate()).hasEmployee(employeeToDelete));
        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EMPLOYEE_SUCCESS, employeeToDelete);

        Model expectedModel = new ModelManager(modelWithLeaves.getSudoHr(), new UserPrefs());
        expectedModel.deleteEmployee(employeeToDelete);

        // check deletion command
        DeleteCommand deleteCommand = new DeleteCommand(employeeToDelete.getId());
        assertCommandSuccess(deleteCommand, modelWithLeaves, expectedMessage, expectedModel);

        // check cascaded to leave level
        assertTrue(!modelWithLeaves.getLeave(LEAVE_TYPE_2.getDate()).hasEmployee(employeeToDelete));
        assertTrue(!modelWithLeaves.getLeave(LEAVE_TYPE_3.getDate()).hasEmployee(employeeToDelete));
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(ID_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(ID_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different employee -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEmployee(Model model) {
        model.updateFilteredEmployeeList(p -> false);

        assertTrue(model.getFilteredEmployeeList().isEmpty());
    }
}
