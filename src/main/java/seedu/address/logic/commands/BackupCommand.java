package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.backup.Backup;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Backs up the data to a specified index
 */
public class BackupCommand extends Command {

    public static final String COMMAND_WORD = "backup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Backs up the file to a specified slot "
            + "Existing backup file be overwritten.\n"
            + "Parameters: INDEX (must be an integer between 1 and 10) "
            + "[INDEX]\n"
            + "Example: " + COMMAND_WORD
            + " 3";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";

    public static final String MESSAGE_SUCCESS = "File saved to: index %1$d";
    public static final String SAVE_ERROR = "Error saving file!";
    private final Backup backup;
    private final Path userPrefsPath = Path.of("preferences.json");

    /**
     * @param index of the backup file
     */
    public BackupCommand(Index index) {
        requireAllNonNull(index);
        this.backup = new Backup(index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(userPrefsPath);
        AddressBookStorage backupStorage = new JsonAddressBookStorage(Path.of(backup.backupLocation));
        Storage storage = new StorageManager(backupStorage, userPrefsStorage);
        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ex) {
            throw new CommandException(SAVE_ERROR);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, backup.getBackupIndex().getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BackupCommand)) {
            return false;
        }

        // state check
        BackupCommand e = (BackupCommand) other;
        return backup.equals(e.backup);
    }

}
