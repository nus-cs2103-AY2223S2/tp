package seedu.fitbook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.fitbook.logic.parser.CliSyntax.PREFIX_ROUTINE;

import seedu.fitbook.logic.commands.exceptions.CommandException;
import seedu.fitbook.model.FitBookModel;
import seedu.fitbook.model.routines.Routine;

/**
 * Adds a routine to the FitBook.
 */
public class AddRoutineCommand extends Command {

    public static final String COMMAND_WORD = "addRoutine";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a routine to the FitBook. "
            + "Parameters: "
            + PREFIX_ROUTINE + "ROUTINE NAME "
            + "[" + PREFIX_EXERCISE + "EXERCISE]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ROUTINE + "Cardio "
            + PREFIX_EXERCISE + "3x5 1km Jog "
            + PREFIX_EXERCISE + "3x10 Jumping Jacks ";

    public static final String MESSAGE_SUCCESS = "New routine added: %1$s";
    public static final String MESSAGE_DUPLICATE_ROUTINE = "This routine already exists in the FitBook";

    private final Routine toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Client}
     */
    public AddRoutineCommand(Routine routine) {
        requireNonNull(routine);
        toAdd = routine;
    }

    @Override
    public CommandResult execute(FitBookModel model) throws CommandException {
        requireNonNull(model);
        if (model.hasRoutine(toAdd)) {

            throw new CommandException(MESSAGE_DUPLICATE_ROUTINE);
        }

        model.addRoutine(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoutineCommand // instanceof handles nulls
                && toAdd.equals(((AddRoutineCommand) other).toAdd));
    }
}
