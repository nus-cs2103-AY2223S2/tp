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

/**
 * An Immutable Deck that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate card(s).";

    private final List<JsonAdaptedCard> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedCard> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyDeck} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyMasterDeck source) {
        persons.addAll(source.getCardList().stream().map(JsonAdaptedCard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Deck} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public MasterDeck toModelType() throws IllegalValueException {
        MasterDeck addressBook = new MasterDeck();
        for (JsonAdaptedCard jsonAdaptedCard : persons) {
            Card card = jsonAdaptedCard.toModelType();
            if (addressBook.hasCard(card)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addCard(card);
        }
        return addressBook;
    }

}
