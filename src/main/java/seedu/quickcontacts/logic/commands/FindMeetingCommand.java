package seedu.quickcontacts.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;
import seedu.quickcontacts.model.meeting.MeetingContainsNamesPredicate;

/**
 *  Finds and lists all meetings in address book whose attendees contains any of the argument person names.
 *  name matching is case-insensitive.
 */
public class FindMeetingCommand extends Command {
    public static final String COMMAND_WORD = "findm";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose attendees contain any of "
        + "the specified person names\n (case-insensitive) and displays them as a list with index numbers.\n"
        + "Use without arguments to list all meetings.\n"
        + "Parameters: [PERSON_NAMES]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie\n"
        + "or"
        + "Example: " + COMMAND_WORD;
    public static final String COMMAND_DESCRIPTION = "Finds all meetings whose attendees contain any of "
        + "the specified person names (case-insensitive).";
    private final MeetingContainsNamesPredicate predicate;

    public FindMeetingCommand(MeetingContainsNamesPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        if (predicate.getPersonNames() == null) {
            return new CommandResult("Listed all meetings!");
        }
        return new CommandResult(
            String.format("Listed all meetings with attendees: %s", predicate.getPersonNames().toString())
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindMeetingCommand // instanceof handles nulls
            && predicate.equals(((FindMeetingCommand) other).predicate)); // state check
    }
}
