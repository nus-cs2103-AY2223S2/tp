package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.storage.Storage;


/**
 * Put a module in archive.
 */

public class ExportCommand extends Command {
    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": archive all modules currently in Le Tracker " + "\n"
            + "\n"
            + "*** Command Format *** " + "\n"
            + COMMAND_WORD + " {file_name}" + "\n"
            + "\n"
            + "*** Example *** " + "\n"
            + COMMAND_WORD + " EG2310";
    public static final String MESSAGE_SUCCESS = "All modules archived to %1$s";

    private final String fileName;
    private final Storage storage;
    private final boolean isOverwritingExistingFile;

    /**
     * Creates an ExportCommand to archive all modules in the current tracker
     */

    public ExportCommand(String fileName, Storage storage, boolean isOverwritingExistingFile) {
        this.fileName = fileName;
        this.storage = storage;
        this.isOverwritingExistingFile = isOverwritingExistingFile;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Path archivePath = Paths.get("data", fileName);
        ReadOnlyTracker tracker = model.getTracker();

        if (Files.exists(archivePath) && Files.isRegularFile(archivePath) && !isOverwritingExistingFile) {
            throw new CommandException(String.format(Messages.MESSAGE_ARCHIVE_FILE_ALREADY_EXIST, fileName));
        }

        try {
            storage.saveTracker(tracker, archivePath);
            return new CommandResult(String.format(MESSAGE_SUCCESS, fileName));
        } catch (IOException ioe) {
            throw new CommandException(LogicManager.FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
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
                && storage.equals(otherCommand.storage)
                && isOverwritingExistingFile == otherCommand.isOverwritingExistingFile;
    }
}
