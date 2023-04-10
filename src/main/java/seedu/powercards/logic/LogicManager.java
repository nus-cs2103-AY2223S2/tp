package seedu.powercards.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.commons.core.LogsCenter;
import seedu.powercards.logic.commands.Command;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.logic.parser.MasterDeckParser;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelState;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.storage.Storage;

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
        case MAIN_UNSELECTED_MODE:
            command = masterDeckParser.parseCommandInMainUnselectedMode(commandText);
            break;
        case MAIN_SELECTED_MODE:
            command = masterDeckParser.parseCommandInMainSelectedMode(commandText);
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
    public ObservableList<Card> getReviewCardList() {
        return model.getReviewCardList();
    }

    @Override
    public void factoryReset() {
        model.setMasterDeck(new MasterDeck());
        model.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        model.updateFilteredDeckList(Model.PREDICATE_SHOW_ALL_DECKS);
        try {
            storage.saveMasterDeck(model.getMasterDeck());
        } catch (IOException ioe) {
            logger.info("Something went wrong while resetting data.");
        }
    }
}
