package seedu.dengue.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.dengue.commons.core.GuiSettings;
import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.commands.Command;
import seedu.dengue.logic.commands.CommandResult;
import seedu.dengue.logic.commands.exceptions.CommandException;
import seedu.dengue.logic.parser.DengueHotspotTrackerParser;
import seedu.dengue.logic.parser.exceptions.ParseException;
import seedu.dengue.model.Model;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.overview.Overview;
import seedu.dengue.model.person.Person;
import seedu.dengue.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final DengueHotspotTrackerParser dengueHotspotTrackerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        dengueHotspotTrackerParser = new DengueHotspotTrackerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = dengueHotspotTrackerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveDengueHotspotTracker(model.getDengueHotspotTracker());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyDengueHotspotTracker getDengueHotspotTracker() {
        return model.getDengueHotspotTracker();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public Path getDengueHotspotTrackerFilePath() {
        return model.getDengueHotspotTrackerFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public Overview getOverview() {
        return model.getOverview();
    }

    @Override
    public ObservableList<DataBin> getOverviewContent() {
        return getOverview().getOverviewContent();
    }
}
