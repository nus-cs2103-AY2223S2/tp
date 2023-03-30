package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Put a module in archive.
 */

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = "\n" + COMMAND_WORD + ":\n"
            + "(1) Archive all modules currently in Le Tracker to a new file. File must be of JSON format\n"
            + "Parameter: "
            + "{file_name}\n"
            + "Example: " + COMMAND_WORD + " hello.json\n\n"
            + "(2) Archive all modules currently in Le Tracker to an existing file.\n"
            + "Parameter: "
            + "{file_name}\n"
            + "Example: " + COMMAND_WORD + " hello.json /overwrite true \n\n";
    public static final String MESSAGE_SUCCESS = "All modules archived to %1$s";

    private final String fileName;
    private final boolean isOverwritingExistingFile;

    /**
     * Creates an ExportCommand to archive all modules in the current tracker
     */

    public ExportCommand(String fileName, boolean isOverwritingExistingFile) {
        this.fileName = fileName;
        this.isOverwritingExistingFile = isOverwritingExistingFile;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path archivePath = Paths.get("data", fileName);

        boolean isExporting = true;

        return new CommandResult(String.format(MESSAGE_SUCCESS, fileName),
                archivePath, isExporting, isOverwritingExistingFile);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherCommand = (ExportCommand) other;

        return fileName.equals(otherCommand.fileName)
                && isOverwritingExistingFile == otherCommand.isOverwritingExistingFile;
    }
}
