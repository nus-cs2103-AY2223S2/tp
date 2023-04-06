package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.TagContainsGroupsPredicate;

/**
 * Filters all persons in address book who belongs to any of the tags specified in the argument.
 * group name matching is case-insensitive.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds athletes in the contact list who have any of the specified tags.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Varsity Hall";

    public static final String MESSAGE_SHOW_GROUP_SUCCESS = "Found all athletes that belong to "
            + "at least one of the following tags specified: %1$s. \n";

    public static final String MESSAGE_TAG_NOT_FOUND_FAILURE = "Tag does not exist.";

    private final TagContainsGroupsPredicate predicate;

    public ShowCommand(TagContainsGroupsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        if (model.getFilteredPersonList().size() == 0) {
            model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_TAG_NOT_FOUND_FAILURE);
        }
        return new CommandResult(
                String.format(MESSAGE_SHOW_GROUP_SUCCESS, predicate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && predicate.equals(((ShowCommand) other).predicate)); // state check
    }

}
