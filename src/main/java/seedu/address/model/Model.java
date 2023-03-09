package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' deck book file path.
     */
    Path getDeckFilePath();

    /**
     * Sets the user prefs' deck file path.
     */
    void setDeckFilePath(Path deckFilePath);

    /**
     * Replaces deck data with the data in {@code addressBook}.
     */
    void setDeck(ReadOnlyAddressBook deck);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getDeck();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasCard(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteCard(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addCard(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setCard(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredCardList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCardList(Predicate<Person> predicate);


    /* NEWLY ADDED COMMANDS TO SUPPORT DECK LIST (NOT IN AB3) */
    /** Returns the deck */
    ReadOnlyAddressBook getSelectedDeck();

    void createDeck();

    void selectDeck(Index idx);

    void unselectDeck();
}
