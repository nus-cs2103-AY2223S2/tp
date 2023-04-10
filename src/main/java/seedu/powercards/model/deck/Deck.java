package seedu.powercards.model.deck;

import static seedu.powercards.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

/**
 * A deck associated with a list of cards. Deck is guaranteed to be immutable.
 */
public final class Deck {
    public static final String MESSAGE_CONSTRAINTS = "Deck name cannot be blank.";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String deckName;
    private final boolean isSelected;

    /**
     * Constructing a deck.
     * Every field must be present and not null.
     */
    public Deck(String deckName) {
        requireAllNonNull(deckName);
        this.deckName = deckName;
        isSelected = false;
    }

    /**
     * Private constructor called internally during deck selection. This is to keep deck immutable.
     * @param deckName Name of the deck
     * @param isSelected Boolean indicating if deck is selected.
     */
    private Deck(String deckName, boolean isSelected) {
        requireAllNonNull(deckName);
        this.deckName = deckName;
        this.isSelected = isSelected;
    }

    public Deck buildSelectedDeck() {
        return new Deck(deckName, true);
    }

    public Deck buildUnselectedDeck() {
        return new Deck(deckName, false);
    }

    /**
     * Returns the name of the deck
     * @return deckName
     */
    public String getDeckName() {
        return this.deckName;
    }

    /**
     * Returns a header consisting of the title and the Deck Name wrapped in an ObservableList
     * @return deckNameList
     */
    public ObservableList<Pair<String, String>> getDeckNameList() {
        ObservableList<Pair<String, String>> deckNameList = FXCollections.observableArrayList();
        Pair<String, String> header = new Pair<>("Current Deck:", this.deckName);
        deckNameList.add(header);
        return deckNameList;
    }

    /**
     * Returns true if a given string is a valid deck name.
     */
    public static boolean isValidDeckName(String deckName) {
        return deckName.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if there is a deck of same name.
     * This defines a weaker notion of equality between two decks.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck != null
                && otherDeck.getDeckName().equals(getDeckName());
    }

    public boolean isSelected() {
        return isSelected;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deck)) {
            return false;
        }

        Deck otherDeck = (Deck) other;
        return otherDeck.getDeckName().equals(getDeckName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(deckName);
    }

    @Override
    public String toString() {
        return this.deckName;
    }


}
