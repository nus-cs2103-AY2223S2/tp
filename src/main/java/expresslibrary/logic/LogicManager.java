package expresslibrary.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import expresslibrary.commons.core.GuiSettings;
import expresslibrary.commons.core.LogsCenter;
import expresslibrary.logic.commands.Command;
import expresslibrary.logic.commands.CommandResult;
import expresslibrary.logic.commands.exceptions.CommandException;
import expresslibrary.logic.parser.ExpressLibraryParser;
import expresslibrary.logic.parser.exceptions.ParseException;
import expresslibrary.model.Model;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.book.Book;
import expresslibrary.model.person.Person;
import expresslibrary.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ExpressLibraryParser expressLibraryParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        expressLibraryParser = new ExpressLibraryParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = expressLibraryParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveExpressLibrary(model.getExpressLibrary());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyExpressLibrary getExpressLibrary() {
        return model.getExpressLibrary();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Book> getFilteredBookList() {
        return model.getFilteredBookList();
    }

    @Override
    public Path getExpressLibraryFilePath() {
        return model.getExpressLibraryFilePath();
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
