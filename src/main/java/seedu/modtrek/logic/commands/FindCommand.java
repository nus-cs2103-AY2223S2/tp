package seedu.modtrek.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.modtrek.commons.core.Messages;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.module.ModuleCodePredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds module with the specified module code \n"
            + "Parameters: MODULE CODE \n"
            + "Example: " + COMMAND_WORD + " CS1101S";

    private final ModuleCodePredicate predicate;

    public FindCommand(ModuleCodePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredModuleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredModuleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
