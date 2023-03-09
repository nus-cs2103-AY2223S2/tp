package seedu.addressbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.addressbook.commons.core.GuiSettings;
import seedu.addressbook.commons.core.LogsCenter;
import seedu.addressbook.logic.commands.Command;
import seedu.addressbook.logic.commands.CommandResult;
import seedu.addressbook.logic.commands.exceptions.CommandException;
import seedu.addressbook.logic.parser.FitBookParser;
import seedu.addressbook.logic.parser.exceptions.ParseException;
import seedu.addressbook.model.FitBookModel;
import seedu.addressbook.model.ReadOnlyFitBook;
import seedu.addressbook.model.client.Client;
import seedu.addressbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final FitBookModel model;
    private final Storage storage;
    private final FitBookParser addressBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code FitBookModel} and {@code Storage}.
     */
    public LogicManager(FitBookModel model, Storage storage) {
        this.model = model;
        this.storage = storage;
        addressBookParser = new FitBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = addressBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFitBook(model.getFitBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFitBook getFitBook() {
        return model.getFitBook();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public Path getFitBookFilePath() {
        return model.getFitBookFilePath();
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
