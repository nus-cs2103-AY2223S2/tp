package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Storage storage;
    private final AddressBookParser addressBookParser;
    private final OfficeConnectModel officeConnectModel;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Storage storage) {
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        this.officeConnectModel = new OfficeConnectModel();
    }


    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Storage storage, OfficeConnectModel officeConnectModel) {
        this.storage = storage;
        addressBookParser = new AddressBookParser();
        this.officeConnectModel = officeConnectModel;
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(officeConnectModel.getPersonModelManager(), officeConnectModel);

        try {
            storage.saveAddressBook(officeConnectModel.getPersonModelManager().getAddressBook());
            storage.saveTaskBook(officeConnectModel.getTaskModelManager().getReadOnlyRepository());
            storage.savePersonTaskBook(officeConnectModel.getAssignTaskModelManager().getReadOnlyRepository());

        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return officeConnectModel.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return officeConnectModel.getPersonModelManager().getFilteredPersonList();
    }
    @Override
    public OfficeConnectModel getOfficeConnectModel() {
        return officeConnectModel;
    }

    @Override
    public Path getAddressBookFilePath() {
        return officeConnectModel.getPersonModelManager().getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return officeConnectModel.getPersonModelManager().getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        officeConnectModel.getPersonModelManager().setGuiSettings(guiSettings);
    }
}
