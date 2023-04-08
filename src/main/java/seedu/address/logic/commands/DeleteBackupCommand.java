package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.BackupDataStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Deletes a backup from a specified index
 */
public class DeleteBackupCommand extends Command {

    public static final String COMMAND_WORD = "deletebackup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
                                                   + ": Deletes a backup from a specified slot\n "
                                                   + "Parameters: INDEX (backup must exist in that index)\n "
                                                   + "Example: " + COMMAND_WORD
                                                   + " 3";

    public static final String MESSAGE_SUCCESS = "Backup deleted from: index %1$d";
    public static final String DELETE_ERROR = "Error deleting file!";
    private final Index index;
    private final Path backupLocation;
    private final Path userPrefsPath = Path.of("preferences.json");
    private final Path backupDataPath = Path.of("data/backup/backupData.json");

    /**
     * @param index of the backup file
     */
    public DeleteBackupCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
        this.backupLocation = Path.of("data/backup/hospirecordBackup"
                                          + index.getOneBased()
                                          + ".json");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        UserPrefsStorage userPrefsStorage = model.getUserPrefsStorage();
        AddressBookStorage backupStorage = new JsonAddressBookStorage(backupLocation);
        BackupDataStorage backupDataStorage = model.getBackupDataStorage();
        Storage storage = new StorageManager(backupStorage, userPrefsStorage);
        try {
            model.removeBackupFromBackupData(String.valueOf(index.getOneBased()));
            backupDataStorage.saveBackupData(model.getBackupData());
            storage.deleteBackup(backupLocation);
        } catch (IndexOutOfBoundsException | IOException ex) {
            throw new CommandException(DELETE_ERROR);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, index.getOneBased()), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteBackupCommand)) {
            return false;
        }

        // state check
        DeleteBackupCommand e = (DeleteBackupCommand) other;
        return index.equals(e.index);
    }

}
