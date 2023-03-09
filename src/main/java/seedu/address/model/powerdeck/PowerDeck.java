package seedu.address.model.powerdeck;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.powercard.PowerCard;
import seedu.address.model.powercard.UniqueCardList;


/**
 * Wraps all data at the PowerDeck level
 * Duplicates are not allowed (by .isSamePowerCard comparison)
 */
public class PowerDeck implements ReadOnlyPowerDeck {
    private final UniqueCardList cards;
    private String deckName; // Todo: create new Class for DeckName

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        cards = new UniqueCardList();
    }

    public PowerDeck() {}

    /**
     * Creates a PowerDeck using the Cards in the {@code toBeCopied}
     */
    public PowerDeck(ReadOnlyPowerDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /* OVERWRITE OPERATIONS */

    /**
     * Replaces the contents of the card list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setCards(List<PowerCard> cards) {
        this.cards.setCards(cards);
    }

    /**
     * Resets the existing data of this {@code Deck} with {@code newData}.
     */
    public void resetData(ReadOnlyPowerDeck newData) {
        requireNonNull(newData);
        setCards(newData.getCardList());
    }

    /* CARD-LEVEL OPERATIONS */

    /**
     * Returns true if a card with the same identity as {@code card} exists in the deck.
     */
    public boolean hasCard(PowerCard card) {
        requireNonNull(card);
        return cards.contains(card);
    }

    /**
     * Adds a card to the deck.
     * The deck must not already exist in the address book.
     */
    public void addCard(PowerCard c) {
        cards.add(c);
    }

    /**
     * Removes {@code card} from this {@code PowerDeck}.
     * {@code card} must exist in the PowerDeck.
     */
    public void removeCard(PowerCard card) {
        cards.remove(card);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the address book.
     */
    public void setCard(PowerCard target, PowerCard editedCard) {
        requireNonNull(editedCard);

        cards.setCard(target, editedCard);
    }

    /* UTIL METHODS */

    @Override
    public String toString() {
        return null; // TODO: refine later
    }

    @Override
    public ObservableList<PowerCard> getCardList() {
        return cards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PowerDeck // instanceof handles nulls
                && cards.equals(((PowerDeck) other).cards));
    }

    @Override
    public int hashCode() {
        return cards.hashCode();
    }
}
