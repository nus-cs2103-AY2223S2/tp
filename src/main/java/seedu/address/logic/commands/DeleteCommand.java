package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.ArrayList;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;


/**
 * Deletes a person identified by his/her NRIC from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by his/her NRIC in the displayed person list.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + "S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person Count: %d";

    private final ArrayList<Nric> nricList;

    public DeleteCommand(ArrayList<Nric> nricList) {
        this.nricList = nricList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        for (Nric nric: nricList) {
            Person personToDelete = model.findPersonByNric(nric);
            if (personToDelete == null) {
                throw new CommandException(Messages.MESSAGE_NRIC_DOES_NOT_EXIST);
            }
            model.deletePerson(personToDelete);
        }
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, nricList.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && nricList.equals(((DeleteCommand) other).nricList)); // state check
    }
}
