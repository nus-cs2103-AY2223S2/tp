package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.commandresult.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.MasterDeckParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.MasterDeck;
import seedu.address.model.Model;
import seedu.address.model.ModelState;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String UNKNOWN_STATE_MESSAGE = "Unknown state reached";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MasterDeckParser masterDeckParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        masterDeckParser = new MasterDeckParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        Command command;

        ModelState currentState = model.getState();
        switch (currentState) {
        case MAIN_MODE:
            command = masterDeckParser.parseCommandInMainMode(commandText);
            break;
        case DECK_MODE:
            command = masterDeckParser.parseCommandInDeckMode(commandText);
            break;
        case REVIEW_MODE:
            command = masterDeckParser.parseCommandInReviewMode(commandText);
            break;
        default:
            throw new CommandException(UNKNOWN_STATE_MESSAGE);
        }

        CommandResult commandResult = command.execute(model);

        try {
            storage.saveMasterDeck(model.getMasterDeck());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyMasterDeck getMasterDeck() {
        return model.getMasterDeck();
    }

    @Override
    public ObservableList<Card> getFilteredCardList() {
        return model.getFilteredCardList();
    }

    @Override
    public ObservableList<Deck> getFilteredDeckList() {
        return model.getFilteredDeckList();
    }

    public ObservableList<String> getDeckNameList() {
        return FXCollections.observableArrayList(model.getSelectedDeckName());
    }

    @Override
    public Path getMasterDeckFilePath() {
        return model.getMasterDeckFilePath();
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
    public ObservableList<Pair<String, String>> getReviewStatsList() {
        return model.getReviewStatsList();
    }

    @Override
    public ModelState getMode() {
        return this.model.getState();
    }

    @Override
    public ObservableList<Pair<String, String>> getReviewDeckNameList() {
        return model.getReviewDeckNameList();
    }

    @Override
    public void factoryReset() {
        model.setMasterDeck(new MasterDeck());
        try {
            storage.saveMasterDeck(model.getMasterDeck());
        } catch (IOException ioe) {
            logger.info("Something went wrong while resetting data.");
        }
    }
}
