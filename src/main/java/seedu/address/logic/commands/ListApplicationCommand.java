package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.DefaultComparator;

/**
 * Lists all applications in the internship book to the user in order of their creation.
 * More recently created applications will show up higher.
 */
public class ListApplicationCommand extends ApplicationCommand {

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
