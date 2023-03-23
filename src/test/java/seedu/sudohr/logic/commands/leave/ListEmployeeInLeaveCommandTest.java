package seedu.sudohr.logic.commands.leave;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_1;
import static seedu.sudohr.logic.commands.CommandTestUtil.VALID_LEAVE_DATE_LEAVE_TYPE_2;
import static seedu.sudohr.testutil.TypicalDepartments.getTypicalSudoHr;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.commons.core.Messages;
import seedu.sudohr.logic.commands.CommandResult;
import seedu.sudohr.logic.commands.exceptions.CommandException;
import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.model.leave.LeaveDate;
import seedu.sudohr.testutil.TypicalEmployees;

public class ListEmployeeInLeaveCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        Leave leave = model
                .getInternalLeaveIfExist(new Leave(new LeaveDate(LocalDate.parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))));
        model.addEmployeeToLeave(leave, TypicalEmployees.ALICE);

    }

    // Handle adding null date
    @Test
    public void execute_listEmployeesOnNullDate_throwsCommandException() throws CommandException {
        assertThrows(NullPointerException.class, () -> new ListEmployeeInLeaveCommand(null).execute(model));
    }

    @Test
    public void execute_listIsFiltered_showsEverything() throws CommandException {
        model.updateFilteredEmployeeList(unused -> false);
        CommandResult commandResult = new ListEmployeeInLeaveCommand(new LeaveDate(LocalDate
                .parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(model);
        assertEquals(String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_listIsNotFiltered_showsEverything() throws CommandException {
        // model.updateFilteredEmployeeList(unused -> true);
        CommandResult commandResult = new ListEmployeeInLeaveCommand(new LeaveDate(LocalDate
                .parse(VALID_LEAVE_DATE_LEAVE_TYPE_1))).execute(model);
        assertEquals(String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 1), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_listIsFiltered_showsNothing() throws CommandException {
        model.updateFilteredEmployeeList(unused -> true);
        CommandResult commandResult = new ListEmployeeInLeaveCommand(new LeaveDate(LocalDate
                .parse(VALID_LEAVE_DATE_LEAVE_TYPE_2))).execute(model);
        assertEquals(String.format(Messages.MESSAGE_EMPLOYEES_LISTED_OVERVIEW, 0), commandResult.getFeedbackToUser());
    }

}
