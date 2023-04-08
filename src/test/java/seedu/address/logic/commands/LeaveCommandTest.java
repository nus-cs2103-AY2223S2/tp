package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEmployeeIds.EMPLOYEE_ID_ONE;
import static seedu.address.testutil.TypicalEmployees.getTypicalExecutiveProDb;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.testutil.EmployeeBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LeaveCommand.
 */
public class LeaveCommandTest {
    private Model model = new ModelManager(getTypicalExecutiveProDb(), new UserPrefs());

    @Test
    public void execute_invalidEmployeeIndexUnfilteredList_failure() {
        EmployeeId outOfBoundEmployeeId = new EmployeeId(String.valueOf(model.getFilteredEmployeeList().size() + 1));
        LeaveCommand leaveCommand = new LeaveCommand(outOfBoundEmployeeId, 1);

        assertCommandFailure(leaveCommand, model, Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_zeroDaysLeaveSpecifiedUnfilteredList_success() {
        LeaveCommand leaveCommand = new LeaveCommand(EMPLOYEE_ID_ONE, 0);
        Employee editedEmployee = model.getFilteredEmployeeList().get(0);

        String expectedMessage = String.format(LeaveCommand.MESSAGE_LEAVE_SUCCESS, editedEmployee.getName(), 0);

        Model expectedModel = new ModelManager(new ExecutiveProDb(model.getExecutiveProDb()), new UserPrefs());

        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneDayLeaveFilteredList_success() {
        Employee employeeInFilteredList = model.getFilteredEmployeeList().get(0);
        int newLeaveCount = employeeInFilteredList.getLeaveCount() - 1;

        Employee editedEmployee = new EmployeeBuilder(employeeInFilteredList)
                .withLeaveCounter(String.valueOf(newLeaveCount)).build();
        LeaveCommand leaveCommand = new LeaveCommand(EMPLOYEE_ID_ONE, 1);

        String expectedMessage = String.format(LeaveCommand.MESSAGE_LEAVE_SUCCESS, editedEmployee.getName(), 1);

        Model expectedModel = new ModelManager(new ExecutiveProDb(model.getExecutiveProDb()), new UserPrefs());
        expectedModel.setEmployee(model.getFilteredEmployeeList().get(0), editedEmployee);

        assertCommandSuccess(leaveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_notEnoughLeaveFilteredList_success() {
        Employee employeeInFilteredList = model.getFilteredEmployeeList().get(0);
        int currentLeaveCount = employeeInFilteredList.getLeaveCount();

        LeaveCommand leaveCommand = new LeaveCommand(EMPLOYEE_ID_ONE, currentLeaveCount + 1);

        String expectedMessage = String.format(LeaveCommand.MESSAGE_NOT_ENOUGH_LEAVE, employeeInFilteredList.getName());
        assertCommandFailure(leaveCommand, model, expectedMessage);
    }
}
