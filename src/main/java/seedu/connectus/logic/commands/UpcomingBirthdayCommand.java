package seedu.connectus.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.connectus.commons.core.Messages;
import seedu.connectus.model.Model;
import seedu.connectus.model.person.RecentBirthdayPredicate;

/**
 * Finds and lists all persons in address book whose birthdays are upcoming (in the next two months).
 */
public class UpcomingBirthdayCommand extends Command {
    public static final String COMMAND_WORD = "upcoming-b";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows people whose birthdays are upcoming (in the next two months).\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new RecentBirthdayPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_UPCOMING_BIRTHDAY, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingBirthdayCommand); // instanceof handles nulls
    }

}
