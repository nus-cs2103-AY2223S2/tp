package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_SALARY_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoles.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Order;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class SalaryCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
        expectedModel.displaySortedRoleList(new Order("asc"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
