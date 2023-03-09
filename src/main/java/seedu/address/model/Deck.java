package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.card.Card;
import seedu.address.model.card.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Deck implements ReadOnlyDeck {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public Deck() {}

    /**
     * Creates an Deck using the Persons in the {@code toBeCopied}
     */
    public Deck(ReadOnlyDeck toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the card list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setPersons(List<Card> cards) {
        this.persons.setPersons(cards);
    }

    /**
     * Resets the existing data of this {@code Deck} with {@code newData}.
     */
    public void resetData(ReadOnlyDeck newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// card-level operations

    /**
     * Returns true if a card with the same identity as {@code card} exists in the address book.
     */
    public boolean hasPerson(Card card) {
        requireNonNull(card);
        return persons.contains(card);
    }

    /**
     * Adds a card to the address book.
     * The card must not already exist in the address book.
     */
    public void addPerson(Card p) {
        persons.add(p);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the address book.
     * The card identity of {@code editedCard} must not be the same as another existing card in the address book.
     */
    public void setPerson(Card target, Card editedCard) {
        requireNonNull(editedCard);

        persons.setPerson(target, editedCard);
    }

    /**
     * Removes {@code key} from this {@code Deck}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Card key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Card> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Deck // instanceof handles nulls
                && persons.equals(((Deck) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
