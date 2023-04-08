package seedu.ultron.logic.commands;

import seedu.ultron.commons.core.Messages;
import seedu.ultron.model.Model;
import seedu.ultron.model.opening.KeydateSort;
import seedu.ultron.model.opening.OpeningsBeforeDaysPredicate;

import static java.util.Objects.requireNonNull;

public class UpcomingCommand extends Command {
    public static final String COMMAND_WORD = "upcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Opens a window which displays upcoming events for the next DAYS days.\n"
            + "Parameters: DAYS (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final String MESSAGE_SUCCESS = "%1$d openings are upcoming!";

    private final OpeningsBeforeDaysPredicate predicate;


    public UpcomingCommand(OpeningsBeforeDaysPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredOpeningList(predicate);
        model.sortFilteredOpeningList(new KeydateSort("asc"));
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredOpeningList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingCommand // instanceof handles nulls
                && predicate.equals(((UpcomingCommand) other).predicate)); // state check
    }
}
