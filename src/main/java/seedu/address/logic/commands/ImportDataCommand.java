package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_BAD_FILE_INPUT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_FILE;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_FILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.storage.AddressBookStorage;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Imports data from a JSON file.
 */
public class ImportDataCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports data from a JSON file. "
            + "Parameters: "
            + PREFIX_FILEPATH + "FILE_PATH\n"
            + "Example: " + COMMAND_WORD + " "
            + "p/C:\\Users\\User\\Desktop\\data.json";

    public static final String MESSAGE_SUCCESS = "Data imported successfully.";

    private final String filePath;

    public ImportDataCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Path dataFilePath = Paths.get(filePath);
            AddressBookStorage addressBookStorage = new JsonAddressBookStorage(dataFilePath);
            ReadOnlyAddressBook data = addressBookStorage.readAddressBook().get();
            model.setAddressBook(data);
        } catch (DataConversionException d) {
            throw new CommandException(MESSAGE_BAD_FILE_INPUT + "\n" + MESSAGE_USAGE);
        } catch (IOException e) {
            throw new CommandException("Error while importing data");
        } catch (NoSuchElementException e) {
            throw new CommandException(MESSAGE_MISSING_FILE);
        } catch (InvalidPathException e) {
            throw new CommandException(MESSAGE_INVALID_FILE);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
