package seedu.patientist.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.model.Model;
import seedu.patientist.model.person.staff.IsStaffPredicate;

/**
 * Lists all staff members in the patientist book to the user.
 */
public class ListStaffCommand extends Command {
    public static final String COMMAND_WORD = "lsstf";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all staff members";
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new IsStaffPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        return other instanceof ListStaffCommand; // instanceof handles nulls
    }
}
