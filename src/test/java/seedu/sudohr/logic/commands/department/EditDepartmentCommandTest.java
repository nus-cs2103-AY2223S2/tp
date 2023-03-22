package seedu.sudohr.logic.commands.department;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.DESC_DEPARTMENT_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.DESC_DEPARTMENT_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_ENGINEERING;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_DEPARTMENT_NAME_HUMAN_RESOURCES;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_FIRST;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_SECOND;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_THIRD;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.ClearCommand;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;
import seedu.sudohr.testutil.DepartmentBuilder;
import seedu.sudohr.testutil.EditDepartmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDepartmentCommand.
 */
public class EditDepartmentCommandTest {

    private Model model = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Department editedDepartment = new DepartmentBuilder().build();
        EditDepartmentCommand.EditDepartmentDescriptor descriptor =
                new EditDepartmentDescriptorBuilder(editedDepartment).build();
        EditDepartmentCommand editDepartmentCommand = new EditDepartmentCommand(DEPARTMENT_NAME_SECOND, descriptor);

        String expectedMessage = String.format(EditDepartmentCommand.MESSAGE_EDIT_DEPARTMENT_SUCCESS, editedDepartment);

        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());
        expectedModel.setDepartment(model.getDepartment(DEPARTMENT_NAME_FIRST), editedDepartment);

        model.removeDepartment(new Department(DEPARTMENT_NAME_FIRST));

        assertCommandSuccess(editDepartmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDepartmentCommand editDepartmentCommand =
                new EditDepartmentCommand(DEPARTMENT_NAME_FIRST, new EditDepartmentCommand.EditDepartmentDescriptor());
        Department editedDepartment = model.getDepartment(DEPARTMENT_NAME_FIRST);

        String expectedMessage =
                String.format(EditDepartmentCommand.MESSAGE_EDIT_DEPARTMENT_SUCCESS, editedDepartment);

        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());

        assertCommandSuccess(editDepartmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDepartmentUnfilteredList_failure() {
        Department firstDepartment = model.getDepartment(DEPARTMENT_NAME_FIRST);
        EditDepartmentCommand.EditDepartmentDescriptor descriptor =
                new EditDepartmentDescriptorBuilder(firstDepartment).build();
        EditDepartmentCommand editDepartmentCommand = new EditDepartmentCommand(DEPARTMENT_NAME_SECOND, descriptor);

        assertCommandFailure(editDepartmentCommand, model, EditDepartmentCommand.MESSAGE_DUPLICATE_DEPARTMENT);
    }

    @Test
    public void execute_invalidDepartmentIndexUnfilteredList_failure() {
        EditDepartmentCommand.EditDepartmentDescriptor descriptor =
                new EditDepartmentDescriptorBuilder().withName(DEPARTMENT_NAME_THIRD).build();
        EditDepartmentCommand editDepartmentCommand =
                new EditDepartmentCommand(new DepartmentName("Fake Department"), descriptor);

        assertCommandFailure(editDepartmentCommand, model, Messages.MESSAGE_DEPARTMENT_NOT_FOUND);
    }

    @Test
    public void equals() {
        final EditDepartmentCommand standardCommand =
                new EditDepartmentCommand(new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES),
                DESC_DEPARTMENT_ENGINEERING);

        // same values -> returns true
        EditDepartmentCommand.EditDepartmentDescriptor copyDescriptor =
                new EditDepartmentCommand.EditDepartmentDescriptor(DESC_DEPARTMENT_ENGINEERING);
        EditDepartmentCommand commandWithSameValues =
                new EditDepartmentCommand(new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES), copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDepartmentCommand(
                new DepartmentName(VALID_DEPARTMENT_NAME_ENGINEERING), DESC_DEPARTMENT_ENGINEERING)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDepartmentCommand(
                new DepartmentName(VALID_DEPARTMENT_NAME_HUMAN_RESOURCES), DESC_DEPARTMENT_HUMAN_RESOURCES)));
    }

}
