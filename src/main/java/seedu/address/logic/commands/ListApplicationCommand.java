package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ApplicationModel.PREDICATE_SHOW_ALL_APPLICATIONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ApplicationModel;

/**
 * Lists all applications in the internship book to the user.
 */
public class ListApplicationCommand extends ApplicationCommand {

	public static final String COMMAND_WORD = "list";

	public static final String MESSAGE_SUCCESS = "Listed all applications";
	@Override
	public CommandResult execute(ApplicationModel model) throws CommandException {
		requireNonNull(model);
		model.updateFilteredApplicationList(PREDICATE_SHOW_ALL_APPLICATIONS);
		return new CommandResult(MESSAGE_SUCCESS);
	}
}
