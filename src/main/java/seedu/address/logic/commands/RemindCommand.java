package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.pet.DeadlineWithinThreeDaysPredicate;

/**
 * Lists all pets in the pet list to the user.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_SUCCESS = "Here are all the upcoming reminders!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all pets whose deadline is within 3 days "
            + "and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " ";

    private final DeadlineWithinThreeDaysPredicate predicate;

    public RemindCommand(DeadlineWithinThreeDaysPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(predicate);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemindCommand // instanceof handles nulls
                && predicate.equals(((RemindCommand) other).predicate)); // state check
    }
}
