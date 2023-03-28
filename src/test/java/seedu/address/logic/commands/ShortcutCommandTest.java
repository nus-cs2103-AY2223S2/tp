package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ShortcutCommandParser.CommandType;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShortcutCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_correctExecution() {
        String shortForm = "fer";
        CommandType filterType = CommandType.FILTER;
        Command command = new ShortcutCommand(filterType, shortForm);
        assertCommandSuccess(command, model,
                new CommandResult(String.format(ShortcutCommand.MESSAGE_SUCCESS, filterType, shortForm),
                        true, true), model);
    }
}
