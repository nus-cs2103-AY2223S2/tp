package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_PATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;

/**
 * Imports data from a JSON file.
 */
public class ExportDataCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export data from a JSON file. "
            + "Parameters: "
            + PREFIX_FILE_PATH + "FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + "C:\\Users\\User\\Desktop\\data.json";

    public static final String MESSAGE_SUCCESS = "Data imported from file.";

    private final Path filePath;

    /**
     * Creates an ExportDataCommand to export the data at the specified {@code filePath}
     */
    public ExportDataCommand(String filePath) {
        requireNonNull(filePath);

        this.filePath = Paths.get(filePath, "data.json");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getAddressBook());

        try {
            ReadOnlyAddressBook data = model.getAddressBook();

            FileUtil.createIfMissing(filePath);
            JsonUtil.saveJsonFile(data, filePath);
        } catch (IOException e) {
            throw new CommandException("Error importing data");
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
