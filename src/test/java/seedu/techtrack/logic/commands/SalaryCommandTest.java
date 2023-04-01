package seedu.techtrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.techtrack.commons.core.Messages.MESSAGE_SALARY_COMMAND_FORMAT;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.techtrack.logic.parser.OrderParser;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code NameCommand}.
 */
public class SalaryCommandTest {
    private Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRoleBook(), new UserPrefs());

    @Test
    public void equals() {

        OrderParser orderParser1 = new OrderParser("asc");
        OrderParser orderParser2 = new OrderParser("desc");

        SalaryCommand salaryFirstCommand = new SalaryCommand(orderParser1);
        SalaryCommand salarySecondCommand = new SalaryCommand(orderParser2);

        // different types -> returns false
        assertFalse(salaryFirstCommand.equals(1));

        // null -> returns false
        assertFalse(salaryFirstCommand.equals(null));

        // different sorted list -> returns false
        assertFalse(salaryFirstCommand.equals(salarySecondCommand));
    }

    @Test
    public void execute_asecOrder() {
        String expectedMessage = String.format(MESSAGE_SALARY_COMMAND_FORMAT, "asc");
        SalaryCommand command = new SalaryCommand(new OrderParser("asc"));
        expectedModel.displaySortedSalaryList(new OrderParser("asc"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
