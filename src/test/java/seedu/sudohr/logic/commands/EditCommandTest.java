package seedu.sudohr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sudohr.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.logic.commands.CommandTestUtil.showEmployeeAtIndex;
import static seedu.sudohr.testutil.TypicalEmployees.getTypicalSudoHr;
import static seedu.sudohr.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.testutil.EditEmployeeDescriptorBuilder;
import seedu.sudohr.testutil.EmployeeBuilder;
import seedu.sudohr.testutil.TypicalEmployees;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // pick some employee to edit
        Employee toEdit = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());

        Employee editedEmployee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(editedEmployee).build();
        EditCommand editCommand = new EditCommand(toEdit.getId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());
        expectedModel.setEmployee(model.getEmployee(toEdit.getId()), editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allFieldUnchangedUnfilteredList_success() {
        // pick some employee to edit
        Employee toEdit = model.getFilteredEmployeeList().get(INDEX_SECOND_PERSON.getZeroBased());

        // all fields specified but no change
        Employee editedEmployee = new EmployeeBuilder(toEdit).build();
        EditCommand editCommand = new EditCommand(toEdit.getId(),
                new EditEmployeeDescriptorBuilder(editedEmployee).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        // pick some employee to edit
        Index indexLastEmployee = Index.fromOneBased(model.getFilteredEmployeeList().size());
        Employee lastEmployee = model.getFilteredEmployeeList().get(indexLastEmployee.getZeroBased());

        EmployeeBuilder employeeInList = new EmployeeBuilder(lastEmployee);
        Employee editedEmployee = employeeInList.withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .build();
        EditCommand editCommand = new EditCommand(lastEmployee.getId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());
        expectedModel.setEmployee(lastEmployee, editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_otherFieldsSpecifiedUnfilteredList_success() {
        // pick some employee to edit
        Index indexLastEmployee = Index.fromOneBased(model.getFilteredEmployeeList().size());
        Employee lastEmployee = model.getFilteredEmployeeList().get(indexLastEmployee.getZeroBased());

        EmployeeBuilder employeeInList = new EmployeeBuilder(lastEmployee);
        Employee editedEmployee = employeeInList.withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_FRIEND).build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withId(VALID_ID_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_FRIEND)
                .build();
        EditCommand editCommand = new EditCommand(lastEmployee.getId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());
        expectedModel.setEmployee(lastEmployee, editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // test editing employees in filtered list
        // pick two employees from unfiltered list first
        Employee employeeNotInFilteredList = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());
        Employee employeeInFilteredList = model.getFilteredEmployeeList().get(INDEX_SECOND_PERSON.getZeroBased());

        // filter
        showEmployeeAtIndex(model, INDEX_SECOND_PERSON);

        // edit employee that is displayed on filtered list
        Employee editedEmployee = new EmployeeBuilder(employeeInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(employeeInFilteredList.getId(),
                new EditEmployeeDescriptorBuilder(employeeInFilteredList).withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        Model expectedModel = new ModelManager(new SudoHr(model.getSudoHr()), new UserPrefs());
        expectedModel.setEmployee(employeeInFilteredList, editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        // edit employee that is not displayed on filtered list
        editedEmployee = new EmployeeBuilder(employeeInFilteredList).withId(VALID_ID_AMY)
                .withEmail(VALID_EMAIL_AMY).withPhone(VALID_PHONE_AMY)
                .build();
        editCommand = new EditCommand(employeeNotInFilteredList.getId(),
                new EditEmployeeDescriptorBuilder(employeeInFilteredList).withId(VALID_ID_AMY)
                        .withEmail(VALID_EMAIL_AMY).withPhone(VALID_PHONE_AMY)
                        .build());

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS, editedEmployee);
        expectedModel.setEmployee(employeeNotInFilteredList, editedEmployee);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateEmployeeUnfilteredList_failure() {
        // pick two employees
        Employee firstEmployee = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());
        Employee secondEmployee = model.getFilteredEmployeeList().get(INDEX_SECOND_PERSON.getZeroBased());

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(firstEmployee).build();
        EditCommand editCommand = new EditCommand(secondEmployee.getId(), descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_duplicateEmployeeFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_PERSON);

        Employee toEdit = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());
        // edit employee in filtered list into a duplicate in SudoHR
        Employee employeeInList = model.getSudoHr().getEmployeeList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(toEdit.getId(),
                new EditEmployeeDescriptorBuilder(employeeInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EMPLOYEE);
    }

    @Test
    public void execute_invalidEmployeeIdUnfilteredList_failure() {
        EditCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalEmployees.ID_NOT_EXIST, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_EMPLOYEE_TO_EDIT_NOT_FOUND);
    }

    @Test
    public void execute_invalidEmployeeIdFilteredList_failure() {
        showEmployeeAtIndex(model, INDEX_FIRST_PERSON);

        EditCommand editCommand = new EditCommand(TypicalEmployees.ID_NOT_EXIST,
                new EditEmployeeDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_EMPLOYEE_TO_EDIT_NOT_FOUND);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(ID_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditEmployeeDescriptor copyDescriptor = new EditEmployeeDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(ID_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(ID_FIRST_PERSON, DESC_BOB)));
    }

}
