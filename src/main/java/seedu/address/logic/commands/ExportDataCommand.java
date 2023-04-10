package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyMathutoring;
import seedu.address.storage.JsonSerializableMathutoring;



/**
 * Imports data from a JSON file.
 */
public class ExportDataCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Export data to a Directory. "
            + "Parameters: "
            + PREFIX_FILEPATH + "FILE_PATH [Optional] \n"
            + "Example: " + COMMAND_WORD + " "
            + "p/C:\\Users\\User\\Desktop\\";

    public static final String MESSAGE_SUCCESS = "Data exported successfully.";

    private final Path filePath;

    /**
     * Creates an ExportDataCommand to export the data at the specified {@code filePath}
     */
    public ExportDataCommand(String filePath) {
        requireNonNull(filePath);
        if (filePath.isEmpty()) {
            this.filePath = Paths.get("data\\data.json");
        } else {
            this.filePath = Paths.get(filePath, "data.json");
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        requireNonNull(model.getMathutoring());

        try {
            ReadOnlyMathutoring data = model.getMathutoring();
            FileUtil.createIfMissing(filePath);
            JsonUtil.saveJsonFile(new JsonSerializableMathutoring(data), filePath);
        } catch (IOException e) {
            throw new CommandException("Error!\n" + e.getMessage());
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
