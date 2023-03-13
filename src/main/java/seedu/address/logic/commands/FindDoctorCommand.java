package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SPECIALTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YOE;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;

/**
 * Finds and lists all doctors in address book matching the respective parameters.
 * Parameter matching is case-insensitive and substrings are matched.
 * At least one parameter is required
 * Tags are matched fully
 */
public class FindDoctorCommand extends Command {

    public static final String COMMAND_WORD = "find-doc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds a doctor in the address book. "
            + "Parameters: "
            + "(" + PREFIX_NAME + "NAME) "
            + "(" + PREFIX_PHONE + "PHONE) "
            + "(" + PREFIX_EMAIL + "EMAIL) "
            + "(" + PREFIX_SPECIALTY + "SPECIALITY) "
            + "(" + PREFIX_YOE + "YEARS OF EXPERIENCE) "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "At least one of the parameters must be present. \n"
            + "Only one of each parameter (excluding " + PREFIX_TAG + "TAG) may be present. \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe";

    private final DoctorContainsKeywordsPredicate predicate;

    public FindDoctorCommand(DoctorContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindDoctorCommand // instanceof handles nulls
                && predicate.equals(((FindDoctorCommand) other).predicate)); // state check
    }


}
