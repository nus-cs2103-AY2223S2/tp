package seedu.modtrek.logic;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.commons.core.LogsCenter;
import seedu.modtrek.logic.commands.Command;
import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.FindCommand;
import seedu.modtrek.logic.commands.SortCommand;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.ModTrekParser;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.model.Model;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ModTrekParser modTrekParser;
    private List<String> filtersList;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        modTrekParser = new ModTrekParser();
        filtersList = new ArrayList<>();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = modTrekParser.parseCommand(commandText);
        if (command instanceof FindCommand) {
            filtersList = ((FindCommand) command).getFiltersList();
        }
        commandResult = command.execute(model);

        try {
            storage.saveDegreeProgression(model.getDegreeProgression());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public List<String> getFiltersList() {
        requireNonNull(filtersList);
        return filtersList;
    }

    @Override
    public ReadOnlyDegreeProgression getDegreeProgression() {
        return model.getDegreeProgression();
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return model.getFilteredModuleList();
    }

    public void sortModuleGroups(SortCommand.Sort sort) {
        model.sortModuleGroups(sort);
    }

    @Override
    public Path getDegreeProgressionFilePath() {
        return model.getDegreeProgressionFilePath();
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
