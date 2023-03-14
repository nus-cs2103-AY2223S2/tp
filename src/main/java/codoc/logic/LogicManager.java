package codoc.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import codoc.commons.core.GuiSettings;
import codoc.commons.core.LogsCenter;
import codoc.logic.commands.Command;
import codoc.logic.commands.CommandResult;
import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.CodocParser;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.Model;
import codoc.model.ReadOnlyCodoc;
import codoc.model.person.Person;
import codoc.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final CodocParser codocParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        codocParser = new CodocParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = codocParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveCodoc(model.getCodoc());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyCodoc getCodoc() {
        return model.getCodoc();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Person getProtagonist() {
        return model.getProtagonist();
    }

    @Override
    public String getCurrentTab() {
        return model.getCurrentTab();
    }

    @Override
    public void setCurrentTab(String tab) {
        model.setCurrentTab(tab);
    }

    @Override
    public Path getCodocFilePath() {
        return model.getCodocFilePath();
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
