package seedu.sudohr.logic.commands.leave;

import static seedu.sudohr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.sudohr.testutil.TypicalLeave.getTypicalSudoHr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.sudohr.model.Model;
import seedu.sudohr.model.ModelManager;
import seedu.sudohr.model.UserPrefs;
import seedu.sudohr.model.leave.Leave;
import seedu.sudohr.testutil.LeaveBuilder;

public class ListLeaveCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSudoHr(), new UserPrefs());
        expectedModel = new ModelManager(model.getSudoHr(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListLeaveCommand(), model, ListLeaveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsEmptiedFirst_showsEverything() {
        model.updateFilteredLeaveList(unused -> false);
        assertCommandSuccess(new ListLeaveCommand(), model, ListLeaveCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyLeaveAdded_showsEverythingButEmptyLeave() {
        Leave leaveWithNoEmployees = new LeaveBuilder()
                .build();
        model.addLeave(leaveWithNoEmployees);
        assertCommandSuccess(new ListLeaveCommand(), model, ListLeaveCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
