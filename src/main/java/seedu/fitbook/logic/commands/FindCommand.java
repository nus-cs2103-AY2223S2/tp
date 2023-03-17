package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.fitbook.commons.core.Messages;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.client.Client;

/**
 * Finds and lists all clients in FitBook whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose details matches or contains "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX, KEYWORD/S\n"
            + "Example: " + COMMAND_WORD + " n/alex or " + COMMAND_WORD + " p/91234567";

    public static final String PREFIX_USAGE = COMMAND_WORD + ": Finds all clients whose details matches or contains "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Available Prefixes: n, p, e, a, t, w, g, cal, app, gl\n"
            + "Example: " + COMMAND_WORD + " n/alex or " + COMMAND_WORD + " p/91234567";

    private List<Predicate<Client>> predicateList;

    public FindCommand(List<Predicate<Client>> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public CommandResult execute(FitBookModel model) {
        requireNonNull(model);
        Predicate<Client> combinedPredicates = predicateList.stream().reduce(x -> false, Predicate::or);
        model.updateFilteredClientList(combinedPredicates);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredClientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicateList.equals(((FindCommand) other).predicateList)); // state check
    }
}
