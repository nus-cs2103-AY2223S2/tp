package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.sprint.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.ApplicationModel;
import seedu.sprint.model.application.DefaultComparator;

/**
 * Lists all applications in the internship book to the user in order of their creation.
 * More recently created applications will show up higher.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications in default order!";
    @Override
    public CommandResult execute(ApplicationModel model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
        model.updateSortedApplicationList(new DefaultComparator(model.getFilteredApplicationList()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
