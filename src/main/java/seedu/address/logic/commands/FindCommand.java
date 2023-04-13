package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;

/**
 * Finds and displays the person in address book with the given NRIC.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds the person with the given NRIC.\n"
            + "Parameters: ic/NRIC \n"
            + "Example: " + COMMAND_WORD + " ic/S1234567A";

    private final Nric nric;

    public FindCommand(Nric nric) {
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> personsToFind = model.getFilteredPersonList();
        int index = -1;

        for (int i = 0; i < personsToFind.size(); i++) {
            if (personsToFind.get(i).getNric().equals(nric)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            model.updateFilteredPersonListNric(nric);
            return new CommandResult("This is the person you are looking for:");
        } else {
            throw new CommandException(Messages.MESSAGE_PERSON_NOT_FOUND);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && nric.equals(((FindCommand) other).nric)); // state check
    }
}
