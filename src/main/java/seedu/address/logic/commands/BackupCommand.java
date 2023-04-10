package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESC;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.BackupData;
import seedu.address.model.Model;
import seedu.address.model.backup.Backup;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.BackupDataStorage;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.storage.UserPrefsStorage;

/**
 * Backs up the data to a specified index.
 */
public class BackupCommand extends Command {

    public static final String COMMAND_WORD = "backup";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Backs up the file to a specified slot "
        + "Existing backup file will be overwritten.\n"
        + "Parameters: INDEX (must be an integer between 1 and 10) "
        + "[" + PREFIX_DESC + "DESCRIPTION]...\n"
        + "Example: " + COMMAND_WORD
        + " 3 "
        + PREFIX_DESC + "day 1";


    public static final String MESSAGE_SUCCESS = "File saved to: index %1$d";
    public static final String SAVE_ERROR = "Error saving file!";
    private final Backup backup;

    /**
     * @param index of the backup file
     * @param desc  description of the backup
     */
    public BackupCommand(Index index, String desc) {
        requireAllNonNull(index);
        this.backup = new Backup(index, desc);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        UserPrefsStorage userPrefsStorage = model.getUserPrefsStorage();
        AddressBookStorage backupStorage = new JsonAddressBookStorage(Path.of(backup.backupLocation));
        Storage storage = new StorageManager(backupStorage, userPrefsStorage);
        BackupDataStorage backupDataStorage = model.getBackupDataStorage();
        try {
            storage.saveAddressBook(model.getAddressBook());
            model.addBackupToBackupData(backup);
            BackupData backupData = model.getBackupData();
            backupDataStorage.saveBackupData(backupData);
        } catch (IOException ex) {
            throw new CommandException(SAVE_ERROR);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, backup.getBackupIndex().getOneBased()),
            false, false, true);
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
