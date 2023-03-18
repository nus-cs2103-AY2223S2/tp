package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Question;
import seedu.address.model.deck.Deck;

/**
 * Jackson-friendly version of {@link Deck}.
 */
public class JsonAdaptedDeck {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Deck name is missing!";
    private final String deckName;

    /**
     * Constructs a {@code JsonAdaptedDeck} with the given deck details.
     */
    @JsonCreator
    public JsonAdaptedDeck(@JsonProperty("deckName") String deckName) {
        this.deckName = deckName;
    }

    /**
     * Converts a given {@code Deck} into this class for Jackson use.
     */
    public JsonAdaptedDeck(Deck source) {
        this.deckName = source.getDeckName();
    }

    /**
     * Converts this Jackson-friendly adapted Deck object into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted deck.
     */
    public Deck toModelType() throws IllegalValueException {

        if (deckName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT));
        }
        if (!Question.isValidQuestion(deckName)) { // Todo: any constraints on Deck Name?
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        return new Deck(deckName);
    }
}
