package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.backup.Backup;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


/**
 * Loads the data from a specified backup
 */
public class LoadCommand extends Command{

    private final Backup backup;


    public LoadCommand(Index index) {
        requireAllNonNull(index);
        this.backup = new Backup(index);
    }

    public static final String COMMAND_WORD = "load";
    public static final String MESSAGE_SUCCESS = "File loaded from: index %1$d";
    public static final String LOAD_ERROR = "Error loading file!";
    public static final String FILE_NOT_THERE = "Backup does not exist!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Loads the file from a specified slot "
            + "Parameters: INDEX (must be an integer between 1 and 10) "
            + "[INDEX]\n"
            + "Example: " + COMMAND_WORD
            + " 3";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        AddressBookStorage loadStorage = new JsonAddressBookStorage(Path.of(backup.BACKUP_LOCATION));
        ReadOnlyAddressBook data;
        try {
            Optional<ReadOnlyAddressBook> addressBookOptional;
            addressBookOptional = loadStorage.readAddressBook();
            if (addressBookOptional.isPresent()) {
                data = addressBookOptional.get();
                model.setAddressBook(data);
            } else {
                throw new CommandException(FILE_NOT_THERE);
            }
        } catch (IOException | DataConversionException ex) {
            throw new CommandException(LOAD_ERROR);
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
        if (!(other instanceof LoadCommand)) {
            return false;
        }

        // state check
        LoadCommand e = (LoadCommand) other;
        return backup.equals(e.backup);
    }
}
