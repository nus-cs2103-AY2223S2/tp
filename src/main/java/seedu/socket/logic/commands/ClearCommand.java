package seedu.socket.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.socket.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.socket.logic.commands.exceptions.CommandException;
import seedu.socket.model.Model;
import seedu.socket.model.person.predicate.FindCommandTagPredicate;

/**
 * Clears SOCket.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all persons, or persons whose tag(s) "
            + "contain any of the keyword(s) (case-insensitive).\n"
            + "Parameters: "
            + "[" + PREFIX_TAG + "TAG]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "friend OR " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "SOCket has been cleared!";

    public static final String MESSAGE_NOT_SUCCESS = "No such tag found!";

    private final FindCommandTagPredicate tagPredicate;

    public ClearCommand(FindCommandTagPredicate tagPredicate) {
        this.tagPredicate = tagPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (tagPredicate.isEmpty()) {
            model.hasDeleteMultiplePerson(x -> true);
        } else if (!model.hasDeleteMultiplePerson(tagPredicate)) {
            throw new CommandException(MESSAGE_NOT_SUCCESS);
        }
        model.commitSocket();
        return new CommandResult(MESSAGE_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearCommand // instanceof handles nulls
                && tagPredicate.equals(((ClearCommand) other).tagPredicate)); // state check for predicate
    }
}
