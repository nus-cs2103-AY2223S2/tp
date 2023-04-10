package seedu.address.storage.fish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.fish.Fish;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_FISH = "fishes list contains duplicate fish(es).";

    private final List<JsonAdaptedFish> fishes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given fishes.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("fishes") List<JsonAdaptedFish> fishes) {
        this.fishes.addAll(fishes);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        fishes.addAll(source.getFishList().stream().map(JsonAdaptedFish::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedFish jsonAdaptedFish : fishes) {
            Fish fish = jsonAdaptedFish.toModelType();
            if (addressBook.hasFish(fish)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_FISH);
            }
            addressBook.addFish(fish);
        }
        return addressBook;
    }

}
