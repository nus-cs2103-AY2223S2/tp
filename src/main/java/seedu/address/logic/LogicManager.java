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
import seedu.address.logic.parser.FriendlyLinkParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPair;
import seedu.address.model.pair.Pair;
import seedu.address.model.person.Elderly;
import seedu.address.model.person.Volunteer;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final FriendlyLinkParser friendLinkParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        friendLinkParser = new FriendlyLinkParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = friendLinkParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.savePair(model.getFriendlyLink());
            storage.saveElderly(model.getFriendlyLink());
            storage.saveVolunteer(model.getFriendlyLink());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyPair getFriendlyLink() {
        return model.getFriendlyLink();
    }

    @Override
    public Path getElderlyFilePath() {
        return model.getElderlyFilePath();
    }

    @Override
    public Path getVolunteerFilePath() {
        return model.getVolunteerFilePath();
    }

    @Override
    public Path getPairFilePath() {
        return model.getPairFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    // --- The following are displayed in the UI.
    @Override
    public ObservableList<Elderly> getFilteredElderlyList() {
        return model.getFilteredElderlyList();
    }

    @Override
    public ObservableList<Volunteer> getFilteredVolunteerList() {
        return model.getFilteredVolunteerList();
    }

    @Override
    public ObservableList<Pair> getFilteredPairList() {
        return model.getFilteredPairList();
    }

}
