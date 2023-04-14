package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;


/**
 * Deletes person(s) identified by their NRIC from the patient records. (at least one NRIC to be supplied)
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes person(s) identified by their NRIC in the displayed person list.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + "S1234567A";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person List: \n";

    private final Set<Nric> nricList;

    public DeleteCommand(Set<Nric> nricList) {
        this.nricList = nricList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isMissing = false;
        String errorMessage = "";
        String deletedPersonMessage = MESSAGE_DELETE_PERSON_SUCCESS;
        requireNonNull(model);

        for (Nric nric: nricList) {
            Person personToDelete = model.findPersonByNric(nric);
            if (personToDelete == null) {
                isMissing = true;
                errorMessage += String.format(Messages.MESSAGE_NRIC_DOES_NOT_EXIST, nric);
            } else {
                model.deletePerson(personToDelete);
                deletedPersonMessage += String.format("%s\n", nric);
            }
        }
        if (isMissing) {
            throw new CommandException(errorMessage);
        }
        return new CommandResult(deletedPersonMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && nricList.equals(((DeleteCommand) other).nricList)); // state check
    }
}
