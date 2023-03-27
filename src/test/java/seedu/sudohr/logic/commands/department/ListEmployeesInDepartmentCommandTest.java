package seedu.sudohr.logic.commands.department;

import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalDepartmentNames.DEPARTMENT_NAME_NOT_IN_SUDOHR;
import static seedu.sudohr.testutil.TypicalDepartmentNames.EMPTY_DEPARTMENT_NAME;
import static seedu.sudohr.testutil.TypicalDepartmentNames.HUMAN_RESOURCES_DEPARTMENT_NAME;
import static seedu.sudohr.testutil.TypicalDepartments.EMPTY_DEPARTMENT;
import static seedu.sudohr.testutil.TypicalDepartments.HUMAN_RESOURCES;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.department.Department;
import seedu.sudohr.model.department.DepartmentName;


public class ListEmployeesInDepartmentCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_unfilteredList_success() {
        Department department = HUMAN_RESOURCES;
        DepartmentName departmentName = HUMAN_RESOURCES_DEPARTMENT_NAME;
        String expectedMessage = String.format(ListEmployeesInDepartmentCommand.MESSAGE_SUCCESS, departmentName);
        expectedModel.updateFilteredEmployeeList(e -> department.hasEmployee(e));
        ListEmployeesInDepartmentCommand command = new ListEmployeesInDepartmentCommand(departmentName);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_filteredList_success() {
        model.updateFilteredDepartmentList(unused -> false);
        Department department = HUMAN_RESOURCES;
        DepartmentName departmentName = HUMAN_RESOURCES_DEPARTMENT_NAME;
        String expectedMessage = String.format(ListEmployeesInDepartmentCommand.MESSAGE_SUCCESS, departmentName);
        expectedModel.updateFilteredEmployeeList(e -> department.hasEmployee(e));
        expectedModel.updateFilteredDepartmentList(unused -> false);
        ListEmployeesInDepartmentCommand command = new ListEmployeesInDepartmentCommand(departmentName);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        return;
    }

    @Test
    public void execute_emptyDepartment_showsNothing() {
        Department department = EMPTY_DEPARTMENT;
        DepartmentName departmentName = EMPTY_DEPARTMENT_NAME;
        String expectedMessage =
                String.format(ListEmployeesInDepartmentCommand.MESSAGE_SUCCESS_NO_EMPLOYEE, departmentName);
        // shows no employee
        expectedModel.updateFilteredEmployeeList(unused -> false);
        ListEmployeesInDepartmentCommand command = new ListEmployeesInDepartmentCommand(departmentName);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);


    }

    @Test
    public void execute_nonExistingDepartmentThrows_throwsException() {
        DepartmentName departmentName = DEPARTMENT_NAME_NOT_IN_SUDOHR;
        ListEmployeesInDepartmentCommand command = new ListEmployeesInDepartmentCommand(departmentName);

        assertCommandFailure(command, model, ListEmployeesInDepartmentCommand.MESSAGE_DEPARTMENT_NOT_FOUND);
    }



}
