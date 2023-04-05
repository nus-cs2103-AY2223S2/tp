package seedu.quickcontacts.logic.commands;

import seedu.quickcontacts.logic.commands.exceptions.CommandException;
import seedu.quickcontacts.model.Model;

/**
 * A command that shows all not done meetings
 */
public class ShowNotDoneCommand extends Command {
    public static final String COMMAND_WORD = "pending";
    public static final String MESSAGE = "Showing all un-done and not over meetings! To view all meetings again, enter "
            + "'findm'";
    public static final String MESSAGE_USAGE = "No parameters required";
    public static final String COMMAND_DESCRIPTION = "Shows all un-done and not-over meetings";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredMeetingList(Model.PREDICATE_UNDONE_MEETINGS);
        return new CommandResult(MESSAGE);
    }
}
