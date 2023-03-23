package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.DefaultComparator;

/**
 * Lists all applications in the internship book to the user in order of their creation.
 * More recently created applications will show up higher.
 */
public class ListApplicationCommand extends ApplicationCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all applications";
    @Override
    public CommandResult execute(ApplicationModel model) throws CommandException {
        requireNonNull(model);
        model.updateSortedApplicationList(new DefaultComparator(model.getFilteredApplicationList()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
