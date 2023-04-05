package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.SortStudentCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortStudentCommand.
 */
public class SortStudentCommandTest {
    public static final String COMMAND_WORD = "sort-student";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all address book students.\n "
            + "Parameters: The group of students you wish to sort (either all, lab, tutorial or consultation), "
            + "the metric to be sorted (either address, email, name, performance or remark), "
            + "and the desired order (either reverse or nonreverse)\n"
            + "For example: 'sort-student all name reverse' command will order all students in reverse-alphabetical "
            + "ordering of their names";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute() throws CommandException {
        Person validPerson = new PersonBuilder().build();
        CommandResult commandResult = new AddCommand(validPerson).execute(model);
        assertEquals(new SortStudentCommand("temp", "temp", true).execute(model), new CommandResult(MESSAGE_SUCCESS));
    }

    @Test
    public void check_attributes() throws CommandException {
        SortStudentCommand sortCommand = new SortStudentCommand("all", "name", true);
        assertEquals(sortCommand.MESSAGE_USAGE, MESSAGE_USAGE);
    }
}
