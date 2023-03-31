package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * Sorts the Dengue Hotspot Tracker.
 */
public class CheckoutCommand extends Command {

    public static final String COMMAND_WORD = "checkout";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": checkout overview to filename in current directory. "
            + "Example: " + COMMAND_WORD + " sampleoverview.csv ";

    public static final String MESSAGE_SUCCESS = "Successfully checked out overview to %s";

    public static final String MESSAGE_FAILURE = "Failed to checkout overview to %s."
            + " Please check if the filename is in the correct format.";

    private final Path filePath;

    /**
     * Creates a {@code SortCommand} to sort the list by the given parameter.
     *
     * @param filePath to export to.
     */
    public CheckoutCommand(Path filePath) {
        requireNonNull(filePath);

        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.checkout(filePath);
        } catch (IOException e) {
            return new CommandResult(String.format(MESSAGE_FAILURE, this.filePath.toString()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.filePath.toString()));
    }
}
