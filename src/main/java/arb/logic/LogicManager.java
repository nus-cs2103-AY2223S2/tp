package arb.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import arb.commons.core.GuiSettings;
import arb.commons.core.LogsCenter;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.logic.parser.AddressBookParser;
import arb.logic.parser.exceptions.ParseException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ReadOnlyAddressBook;
import arb.model.client.Client;
import arb.model.project.Project;
import arb.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AddressBookParser addressBookParser;

    private ListType currentListType;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model, currentListType);

        try {
            storage.saveAddressBook(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public void setListType(ListType newListType) {
        this.currentListType = newListType;
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Client> getSortedClientList() {
        return model.getSortedClientList();
    }

    @Override
    public ObservableList<Project> getSortedProjectList() {
        return model.getSortedProjectList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
