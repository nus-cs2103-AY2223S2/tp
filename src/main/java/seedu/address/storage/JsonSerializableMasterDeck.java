package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.MasterDeck;
import seedu.address.model.ReadOnlyMasterDeck;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;

/**
 * An Immutable Deck that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableMasterDeck {

    public static final String MESSAGE_DUPLICATE_PERSON = "Card list contains duplicate card(s).";
    public static final String MESSAGE_DUPLICATE_DECK = "Deck list contains duplicate deck(s).";
    public static final String MESSAGE_MISSING_DECK = "Some cards exist without an existing deck.";

    private final List<JsonAdaptedCard> cards = new ArrayList<>();
    private final List<JsonAdaptedDeck> decks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableMasterDeck} with the given persons.
     */
    @JsonCreator
    public JsonSerializableMasterDeck(@JsonProperty("cards") List<JsonAdaptedCard> cards,
                                      @JsonProperty("decks") List<JsonAdaptedDeck> decks) {
        this.cards.addAll(cards);
        this.decks.addAll(decks);
    }

    /**
     * Converts a given {@code ReadOnlyDeck} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableMasterDeck}.
     */
    public JsonSerializableMasterDeck(ReadOnlyMasterDeck source) {
        cards.addAll(source.getCardList().stream().map(JsonAdaptedCard::new).collect(Collectors.toList()));
        decks.addAll(source.getDeckList().stream().map(JsonAdaptedDeck::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MasterDeck toModelType() throws IllegalValueException {
        MasterDeck masterDeck = new MasterDeck();

        for (JsonAdaptedDeck jsonAdaptedDeck : decks) {
            Deck deck = jsonAdaptedDeck.toModelType();
            if (masterDeck.hasDeck(deck)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_DECK);
            }
            masterDeck.addDeck(deck);
        }

        for (JsonAdaptedCard jsonAdaptedCard : cards) {
            Card card = jsonAdaptedCard.toModelType();
            if (masterDeck.hasCard(card)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }

            Deck currDeck = card.getDeck().get();
            if (!masterDeck.hasDeck(currDeck)) {
                throw new IllegalValueException(MESSAGE_MISSING_DECK);
            }

            masterDeck.addCard(card);
        }

        return masterDeck;
    }

}
