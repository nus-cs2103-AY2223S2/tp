package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;

/**
 * Sorts the Dengue Hotspot Tracker.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": imports data from file stored in current directory. "
            + "Example: " + COMMAND_WORD + " sampledata.csv ";

    public static final String MESSAGE_SUCCESS = "Successfully imported data from %s";

    public static final String MESSAGE_FAILURE_IMPORT = "Failed to import data from %s."
            + " Please check if the data is in the correct format.";

    public static final String MESSAGE_FAILURE_EXIST = "Failed to import data from %s."
            + " Please check if the file exists.";

    private final Path filePath;

    /**
     * Creates a {@code SortCommand} to sort the list by the given parameter.
     *
     * @param filePath The path to a file.
     */
    public ImportCommand(Path filePath) {
        requireNonNull(filePath);

        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            model.importCsv(filePath);
        } catch (DataConversionException e) {
            return new CommandResult(String.format(MESSAGE_FAILURE_IMPORT, this.filePath.toString()));
        } catch (IOException e) {
            return new CommandResult(String.format(MESSAGE_FAILURE_EXIST, this.filePath.toString()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.filePath.toString()));
    }
}
