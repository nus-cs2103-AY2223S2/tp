package seedu.roles.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.roles.commons.core.Messages.MESSAGE_SALARY_COMMAND_FORMAT;
import static seedu.roles.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.roles.model.Model;
import seedu.roles.model.ModelManager;
import seedu.roles.model.UserPrefs;
import seedu.roles.model.job.Order;

/**
 * Contains integration tests (interaction with the Model) for {@code NameCommand}.
 */
public class SalaryCommandTest {
    private Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalRoleBook(), new UserPrefs());

    @Test
    public void equals() {

        Order order1 = new Order("asc");
        Order order2 = new Order("desc");

        SalaryCommand salaryFirstCommand = new SalaryCommand(order1);
        SalaryCommand salarySecondCommand = new SalaryCommand(order2);

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
        SalaryCommand command = new SalaryCommand(new Order("asc"));
        expectedModel.displaySortedSalaryList(new Order("asc"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
