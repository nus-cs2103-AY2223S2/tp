package seedu.address.logic.commands;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.SaveCommandResult;
import seedu.address.model.Model;

/**
 * Saves the EduMate into a file.
 */
public class SaveCommand extends Command {

    public static final String COMMAND_WORD = "save";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": saves the current EduMate into memory.\n"
            + "Parameters:\n"
            + "save FILENAME(as single string without spaces)"
            + "Example:\n"
            + "save backup";

    public static final String MESSAGE_SUCCESS = "Successfully saved to: %s";

    /** Where to save the EduMate */
    private final Path filePath;

    public SaveCommand(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public SaveCommandResult execute(Model model) throws CommandException {
        // Logic manager will handle the actual storage functions
        return new SaveCommandResult(String.format(MESSAGE_SUCCESS, filePath.toString()), filePath);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SaveCommand // instanceof handles nulls
                && filePath.equals(((SaveCommand) other).filePath)); // state check
    }
}
