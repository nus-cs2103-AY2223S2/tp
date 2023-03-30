package seedu.dengue.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dengue.logic.parser.CliSyntax.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.model.Model;
import seedu.dengue.model.person.Person;

/**
 * Sorts the Dengue Hotspot Tracker.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": imports data file from filePath. "
            + "Example: " + COMMAND_WORD + " sampledata.csv ";

    public static final String MESSAGE_SUCCESS = "Successfully imported data from %s";

    private final Path filePath;

    /**
     * Creates a {@code SortCommand} to sort the list by the given parameter.
     *
     * @param type The type of sort.
     */
    public ImportCommand(Path filePath) {
        requireNonNull(filePath);
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.importCsv(filePath);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.filePath.toString()));
    }
}
