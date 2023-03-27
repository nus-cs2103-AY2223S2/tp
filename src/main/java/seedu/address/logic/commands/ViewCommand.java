package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Detailed view of one's patient record specified by NRIC.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a detailed, zoomed-in view of the patient's record who has the specified NRIC.\n"
            + "Parameters: " + PREFIX_NRIC + "NRIC\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NRIC + "S1234567A";

    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Displaying Patient Record!";

    private final Nric nric;

    public ViewCommand(Nric nric) {
        this.nric = nric;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Person specified = model.findPersonByNric(nric);
        if (specified == null) {
            throw new CommandException(Messages.MESSAGE_NRIC_DOES_NOT_EXIST);
        }
        Predicate<Person> predicate = new NricContainsKeywordsPredicate(Arrays.asList(nric.toString()));
        model.updateFilteredPersonList(predicate);
        return new CommandResult(MESSAGE_VIEW_PERSON_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && nric.equals(((ViewCommand) other).nric)); // state check
    }
}
