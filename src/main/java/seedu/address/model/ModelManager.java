package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;
import seedu.address.model.powerdeck.PowerDeck;
import seedu.address.model.powerdeck.ReadOnlyPowerDeck;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private AddressBook deck;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredDecks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook deck, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(deck, userPrefs);

        logger.fine("Initializing with address book: " + deck + " and user prefs " + userPrefs);

        this.deck = new AddressBook(deck);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredDecks = new FilteredList<>(this.deck.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDeckFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setDeckFilePath(Path deckFilePath) {
        requireNonNull(deckFilePath);
        userPrefs.setAddressBookFilePath(deckFilePath);
    }

    //=========== PowerDeck ================================================================================

    @Override
    public void setDeck(ReadOnlyAddressBook deck) {
        this.deck.resetData(deck);
    }

    @Override
    public ReadOnlyAddressBook getDeck() {
        return deck;
    }

    @Override
    public boolean hasCard(Person person) {
        requireNonNull(person);
        return deck.hasPerson(person);
    }

    @Override
    public void deleteCard(Person target) {
        deck.removePerson(target);
    }

    @Override
    public void addCard(Person person) {
        deck.addPerson(person);
        updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
    }

    @Override
    public void setCard(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        deck.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredCardList() {
        return filteredDecks;
    }

    @Override
    public void updateFilteredCardList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return deck.equals(other.deck)
                && userPrefs.equals(other.userPrefs)
                && filteredDecks.equals(other.filteredDecks);
    }


    /* NEWLY ADDED COMMANDS TO SUPPORT DECK LIST */
    // Todo: Link getDeck() to GUI
    @Override
    public ReadOnlyAddressBook getSelectedDeck() {
        return this.deck;
    }

    @Override
    public void createDeck() { // Todo: deck should have a name - how to store in storage?
        PowerDeck newDeck = new PowerDeck();
//        this.deck.add(newDeck);
    }

    @Override
    public void selectDeck(Index deckIndex) {
        int zeroBasesIdx = deckIndex.getZeroBased();
//        deck = deck.get(zeroBasesIdx);
    }

    @Override
    public void unselectDeck() {
        this.deck = null;
        this.filteredDecks = null;
    }
}
