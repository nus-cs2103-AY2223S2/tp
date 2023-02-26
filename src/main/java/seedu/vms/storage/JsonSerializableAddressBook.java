package seedu.vms.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.person.AddressBook;
import seedu.vms.model.person.Person;
import seedu.vms.model.person.ReadOnlyAddressBook;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String DUPLICATE_ID = "Persons list contains duplicate ID(s).";

    private final List<JsonAdaptedPersonData> datas = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("datas") List<JsonAdaptedPersonData> datas) {
        this.datas.addAll(datas);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        datas.addAll(source.getMapView()
                .values()
                .stream()
                .map(JsonAdaptedPersonData::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPersonData jsonAdaptedPersonData : datas) {
            IdData<Person> personData = jsonAdaptedPersonData.toModelType();
            if (addressBook.contains(personData.getId())) {
                throw new IllegalValueException(DUPLICATE_ID);
            }
            addressBook.add(personData);
        }
        return addressBook;
    }

}
