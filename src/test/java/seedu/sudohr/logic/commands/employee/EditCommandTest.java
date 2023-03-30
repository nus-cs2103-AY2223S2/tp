package seedu.sudohr.logic.commands.employee;

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
import static seedu.sudohr.testutil.TypicalDepartments.EMPLOYEE_IN_HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.SALES;
import static seedu.sudohr.testutil.TypicalIds.ID_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIds.ID_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.sudohr.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.sudohr.testutil.TypicalLeave.EMPLOYEE_ON_LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.EMPLOYEE_ON_LEAVE_TYPE_2_AND_3;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_1;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_2;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_3;

import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.commons.core.index.Index;
import seedu.sudohr.logic.commands.ClearCommand;
import seedu.sudohr.logic.commands.employee.EditCommand.EditEmployeeDescriptor;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.SudoHr;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.employee.Email;
import seedu.sudohr.model.employee.Employee;
import seedu.sudohr.model.employee.Id;
import seedu.sudohr.testutil.EditEmployeeDescriptorBuilder;
import seedu.sudohr.testutil.EmployeeBuilder;
import seedu.sudohr.testutil.TypicalDepartments;
import seedu.sudohr.testutil.TypicalEmployees;
import seedu.sudohr.testutil.TypicalLeave;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalEmployees.getTypicalSudoHr(), new UserPrefs());
    private Model modelWithDepts = new ModelManager(TypicalDepartments.getTypicalSudoHr(), new UserPrefs());
    private Model modelWithLeaves = new ModelManager(TypicalLeave.getTypicalSudoHr(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        // pick some employee to edit
        Employee toEdit = model.getFilteredEmployeeList().get(INDEX_FIRST_PERSON.getZeroBased());

        Employee editedEmployee = new EmployeeBuilder().build();
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(editedEmployee).build();
        EditCommand editCommand = new EditCommand(toEdit.getId(), descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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

        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
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
    public void execute_cascadeUpdateEmployeeFromDepartment_success() {
        Employee employeeToEdit = EMPLOYEE_IN_HUMAN_RESOURCES;
        assertTrue(employeeToEdit != null);
        // ensure employee exists in HUMAN_RESOURCES
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToEdit));

        Employee editedEmployee = new EmployeeBuilder(EMPLOYEE_IN_HUMAN_RESOURCES).withId(VALID_ID_AMY).build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(EMPLOYEE_IN_HUMAN_RESOURCES)
                .withId(VALID_ID_AMY)
                .build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
        Model expectedModel = new ModelManager(modelWithDepts.getSudoHr(), new UserPrefs());
        expectedModel.setEmployee(employeeToEdit, editedEmployee);

        // check edit command
        EditCommand editCommand = new EditCommand(employeeToEdit.getId(), descriptor);
        assertCommandSuccess(editCommand, modelWithDepts, expectedMessage, expectedModel);

        // check cascaded to department level, since employee is identified by id, old employee object
        // should not be found in list whereas new employee object should be found.
        assertTrue(!modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToEdit));
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(editedEmployee));
        // check all other fields remain the same
        Employee extractFromDept = modelWithDepts
                .getDepartment(HUMAN_RESOURCES.getName())
                .getEmployee(new Id(VALID_ID_AMY));
        assertTrue(extractFromDept.getName().equals(employeeToEdit.getName()));
        assertTrue(extractFromDept.getPhone().equals(employeeToEdit.getPhone()));
        assertTrue(extractFromDept.getEmail().equals(employeeToEdit.getEmail()));
        assertTrue(extractFromDept.getTags().equals(employeeToEdit.getTags()));
        assertTrue(extractFromDept.getAddress().equals(employeeToEdit.getAddress()));
    }

    @Test
    public void execute_cascadeUpdateEmployeeFromTwoDepartments_success() {
        Employee employeeToEdit = EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES;
        assertTrue(employeeToEdit != null);
        // ensure employee exists in HUMAN_RESOURCES and SALES
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(employeeToEdit));
        assertTrue(modelWithDepts.getDepartment(SALES.getName()).hasEmployee(employeeToEdit));

        Employee editedEmployee = new EmployeeBuilder(EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES).withEmail(VALID_EMAIL_AMY)
                .build();
        EditCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder(EMPLOYEE_IN_HUMAN_RESOURCES_AND_SALES).withEmail(VALID_EMAIL_AMY)
                .build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
        Model expectedModel = new ModelManager(modelWithDepts.getSudoHr(), new UserPrefs());
        expectedModel.setEmployee(employeeToEdit, editedEmployee);

        // check edit command
        EditCommand editCommand = new EditCommand(employeeToEdit.getId(), descriptor);
        assertCommandSuccess(editCommand, modelWithDepts, expectedMessage, expectedModel);

        assertTrue(modelWithDepts.getDepartment(SALES.getName()).hasEmployee(editedEmployee));
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName()).hasEmployee(editedEmployee));
        // check employee name field in each department has been updated
        assertTrue(modelWithDepts.getDepartment(SALES.getName())
                .getEmployee(employeeToEdit.getId()).getEmail().equals(new Email(VALID_EMAIL_AMY)));
        assertTrue(modelWithDepts.getDepartment(HUMAN_RESOURCES.getName())
                .getEmployee(employeeToEdit.getId()).getEmail().equals(new Email(VALID_EMAIL_AMY)));
    }

    @Test
    public void execute_cascadeUpdateEmployeeInLeave_success() {
        Employee employeeToEdit = EMPLOYEE_ON_LEAVE_TYPE_1;
        assertTrue(employeeToEdit != null);
        // ensure employee exists in the list of employee on leave that day
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_1.getDate()).hasEmployee(employeeToEdit));

        Employee editedEmployee = new EmployeeBuilder(EMPLOYEE_ON_LEAVE_TYPE_1).withId(VALID_ID_AMY).build();
        EditCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder(EMPLOYEE_ON_LEAVE_TYPE_1)
                .withId(VALID_ID_AMY)
                .build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
        Model expectedModel = new ModelManager(modelWithLeaves.getSudoHr(), new UserPrefs());
        expectedModel.setEmployee(employeeToEdit, editedEmployee);

        // check edit command
        EditCommand editCommand = new EditCommand(employeeToEdit.getId(), descriptor);
        assertCommandSuccess(editCommand, modelWithLeaves, expectedMessage, expectedModel);

        // check cascaded to leave level, since employee is identified by id, old employee object
        // should not be found in list whereas new employee object should be found.
        assertTrue(!modelWithLeaves.getLeave(LEAVE_TYPE_1.getDate()).hasEmployee(employeeToEdit));
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_1.getDate()).hasEmployee(editedEmployee));
        // check all other fields remain the same
        Employee extractFromLeave = modelWithLeaves
                .getLeave(LEAVE_TYPE_1.getDate())
                .getEmployee(new Id(VALID_ID_AMY));
        assertTrue(extractFromLeave.getName().equals(employeeToEdit.getName()));
        assertTrue(extractFromLeave.getPhone().equals(employeeToEdit.getPhone()));
        assertTrue(extractFromLeave.getEmail().equals(employeeToEdit.getEmail()));
        assertTrue(extractFromLeave.getTags().equals(employeeToEdit.getTags()));
        assertTrue(extractFromLeave.getAddress().equals(employeeToEdit.getAddress()));
    }

    @Test
    public void execute_cascadeUpdateEmployeeInTwoLeaves_success() {
        Employee employeeToEdit = EMPLOYEE_ON_LEAVE_TYPE_2_AND_3;
        assertTrue(employeeToEdit != null);
        // ensure employee exists in the respective list of employees for each leave
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_2.getDate()).hasEmployee(employeeToEdit));
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_3.getDate()).hasEmployee(employeeToEdit));

        Employee editedEmployee = new EmployeeBuilder(EMPLOYEE_ON_LEAVE_TYPE_2_AND_3).withEmail(VALID_EMAIL_AMY)
                .build();
        EditCommand.EditEmployeeDescriptor descriptor =
                new EditEmployeeDescriptorBuilder(EMPLOYEE_ON_LEAVE_TYPE_2_AND_3).withEmail(VALID_EMAIL_AMY)
                        .build();
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_EMPLOYEE_SUCCESS,
                editedEmployee.toStringAllFields());
        Model expectedModel = new ModelManager(modelWithLeaves.getSudoHr(), new UserPrefs());
        expectedModel.setEmployee(employeeToEdit, editedEmployee);

        // check edit command
        EditCommand editCommand = new EditCommand(employeeToEdit.getId(), descriptor);
        assertCommandSuccess(editCommand, modelWithLeaves, expectedMessage, expectedModel);

        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_2.getDate()).hasEmployee(editedEmployee));
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_3.getDate()).hasEmployee(editedEmployee));
        // check employee name field in employee list of each leave object has been updated
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_2.getDate())
                .getEmployee(employeeToEdit.getId()).getEmail().equals(new Email(VALID_EMAIL_AMY)));
        assertTrue(modelWithLeaves.getLeave(LEAVE_TYPE_3.getDate())
                .getEmployee(employeeToEdit.getId()).getEmail().equals(new Email(VALID_EMAIL_AMY)));
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
