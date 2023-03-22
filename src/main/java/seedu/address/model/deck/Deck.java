package seedu.address.model.deck;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * A group of cards
 */
public class Deck {
    public static final String MESSAGE_CONSTRAINTS = "Deck name can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String deckName;
    private ObservableList<Pair<String, String>> deckNameList;

    /**
     * Constructing a deck.
     * Every field must be present and not null.
     */
    public Deck(String deckName) {
        requireAllNonNull(deckName);
        this.deckName = deckName;
        // initialise deckNameList
        this.deckNameList = FXCollections.observableArrayList();
    }

    /**
     * Returns the name of the deck
     * @return deckName
     */
    public String getDeckName() {
        return this.deckName;
    }

    /**
     * Returns a header consisting of the title and the deckname wrapped in an Observablelist
     * @return deckNameList
     */
    public ObservableList<Pair<String, String>> getDeckNameList() {
        this.deckNameList.clear();
        Pair<String, String> header = new Pair("Current Deck:",this.deckName);
        this.deckNameList.add(header);
        return this.deckNameList;
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
