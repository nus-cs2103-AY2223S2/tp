package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {
    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_SUCCESS = "Filter students accordingly.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all address book students.\n "
            + "Parameters: the metric to be sorted (performance or urgency), "
            + "and the desired threshold value (0 to 100)\n"
            + "For example, 'filter performance 40' is a command you can type";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute() throws CommandException {
        Person validPerson = new PersonBuilder().build();
        CommandResult commandResult = new AddCommand(validPerson).execute(model);
        assertEquals(new FilterCommand("temp", 40).execute(model), new CommandResult(MESSAGE_SUCCESS));
    }

    @Test
    public void check_attributes() throws CommandException {
        FilterCommand filterCommand = new FilterCommand("lab", 100);
        assertEquals(filterCommand.MESSAGE_USAGE, MESSAGE_USAGE);
    }
}
