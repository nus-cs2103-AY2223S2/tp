package seedu.address.logic.commands;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.LoadCommandResult;
import seedu.address.model.Model;

/**
 * Loads the EduMate from a file.
 */
public class LoadCommand extends Command {

    public static final String COMMAND_WORD = "load";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": loads the current EduMate from memory."
            + "Parameters:\n"
            + "load FILENAME(as single string without spaces)"
            + "Example:\n"
            + "load backup";

    public static final String MESSAGE_SUCCESS = "Successfully loaded from: %s";

    private final Path filePath;

    public LoadCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public LoadCommandResult execute(Model model) throws CommandException {
        // Logic manager will handle the actual storage functions
        return new LoadCommandResult(String.format(MESSAGE_SUCCESS, filePath.toString()), filePath);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LoadCommand // instanceof handles nulls
                && filePath.equals(((LoadCommand) other).filePath)); // state check
    }
}
