package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import seedu.address.commons.core.Messages;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyTracker;

public class Archive {
    private final Storage storage;
    public Archive(Storage storage) {
        this.storage = storage;
    }

    public void export(Path archivedPath, ReadOnlyTracker tracker, boolean isOverwriting) throws CommandException {
        if (Files.exists(archivedPath) && Files.isRegularFile(archivedPath) && !isOverwriting) {
            throw new CommandException(String.format(Messages.MESSAGE_ARCHIVE_FILE_ALREADY_EXIST));
        }

        try {
            storage.saveTracker(tracker, archivedPath);
        } catch (IOException ioe) {
            throw new CommandException(LogicManager.FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }
}