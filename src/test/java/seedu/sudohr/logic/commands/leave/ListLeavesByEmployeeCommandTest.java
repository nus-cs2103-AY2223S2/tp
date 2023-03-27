package seedu.sudohr.logic.commands.leave;


import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalEmployees.ID_NOT_EXIST;
import static seedu.sudohr.testutil.TypicalLeave.EMPLOYEE_ON_LEAVE_TYPE_2_AND_3;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_2;
import static seedu.sudohr.testutil.TypicalLeave.LEAVE_TYPE_3;
import static seedu.sudohr.testutil.TypicalLeave.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.employee.Id;


public class ListLeavesByEmployeeCommandTest {
    private Model model;

    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalSudoHr(), new UserPrefs());

    }

    @Test
    public void execute_validEmployeeId_success() {
        Id employeeId = EMPLOYEE_ON_LEAVE_TYPE_2_AND_3.getId();

        expectedModel.updateFilteredLeaveList(l -> l.isSameLeave(LEAVE_TYPE_2) || l.isSameLeave(LEAVE_TYPE_3));
        String expectedMessage = String.format(ListLeavesByEmployeeCommand.MESSAGE_SUCCESS, employeeId);

        assertCommandSuccess(new ListLeavesByEmployeeCommand(employeeId), model,
                expectedMessage,
                expectedModel);

    }

    @Test
    public void execute_invalidEmployeeId_failure() {
        Id employeeIdNotInSudoHr = ID_NOT_EXIST;
        ListLeavesByEmployeeCommand command = new ListLeavesByEmployeeCommand(employeeIdNotInSudoHr);

        assertCommandFailure(command, model, ListLeavesByEmployeeCommand.MESSAGE_EMPLOYEE_NOT_FOUND);
    }



}
