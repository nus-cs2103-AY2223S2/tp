package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.ApplicationModel;
import seedu.address.model.application.NameContainsKeywordsPredicate;

/**
 * Finds and lists all internship applications in internship book which has company names
 * containing any of the argument keywords. Keyword matching is case-insensitive.
 */
public class FindApplicationCommand extends ApplicationCommand {

	public static final String COMMAND_WORD = "find";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship applications "
			+ "which has company names containing "
			+ "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
			+ "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
			+ "Example: " + COMMAND_WORD + "  Goggle Mata";

	private final NameContainsKeywordsPredicate predicate;

	public FindApplicationCommand(NameContainsKeywordsPredicate predicate) {
		this.predicate = predicate;
	}

	@Override
	public CommandResult execute(ApplicationModel model) {
		requireNonNull(model);
		model.updateFilteredApplicationList(predicate);
		return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW,
						model.getFilteredApplicationList().size()));
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof FindApplicationCommand // instanceof handles nulls
				&& predicate.equals(((FindApplicationCommand) other).predicate)); // state check
	}
}
