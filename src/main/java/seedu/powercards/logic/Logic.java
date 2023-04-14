package seedu.powercards.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import javafx.util.Pair;
import seedu.powercards.commons.core.GuiSettings;
import seedu.powercards.logic.commands.commandresult.CommandResult;
import seedu.powercards.logic.commands.exceptions.CommandException;
import seedu.powercards.logic.parser.exceptions.ParseException;
import seedu.powercards.model.ModelState;
import seedu.powercards.model.ReadOnlyMasterDeck;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Deck.
     *
     * @see seedu.powercards.model.Model#getMasterDeck()
     */
    ReadOnlyMasterDeck getMasterDeck();

    /** Returns an unmodifiable view of the filtered list of cards */
    ObservableList<Card> getFilteredCardList();

    /** Returns an unmodifiable view of the filtered list of decks */
    ObservableList<Deck> getFilteredDeckList();

    /**
     * Returns the user prefs' master deck file path.
     */
    Path getMasterDeckFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the current mode of the application.
     *
     * @return the state the model is in.
     */
    ModelState getMode();

    ObservableList<Pair<String, String>> getReviewStatsList();

    ObservableList<String> getDeckNameList();

    ObservableList<Pair<String, String>> getReviewDeckNameList();

    /**
     * Returns the card list storing cards in Review.
     * @return The Observable List in Review.
     */
    ObservableList<Card> getReviewCardList();

    /**
     * Removes all the card and deck data of users.
     */
    void factoryReset();
}
