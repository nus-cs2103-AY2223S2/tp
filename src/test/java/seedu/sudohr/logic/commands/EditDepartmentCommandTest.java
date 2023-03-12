package seedu.sudohr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.*;
import static seedu.sudohr.testutil.TypicalDepartmentNames.*;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
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
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditCommand.EditPersonDescriptor copyDescriptor = new EditCommand.EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
