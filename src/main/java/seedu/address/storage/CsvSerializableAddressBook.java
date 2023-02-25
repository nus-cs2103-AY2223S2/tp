package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable AddressBook that is serializable to CSV format.
 */
class CsvSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private static final String CSV_HEADERS = "Name,Phone,Email,Address,Tags\n";

    private final List<CsvAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code CsvSerializableAddressBook} with the given persons.
     */
    public CsvSerializableAddressBook(List<CsvAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Csv use.
     *
     * @param source future changes to this will not affect the created {@code CsvSerializableAddressBook}.
     */
    public CsvSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(CsvAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (CsvAdaptedPerson csvAdaptedPerson : persons) {
            Person person = csvAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

    @Override
    public String toString() {
        return CSV_HEADERS + String.join("\n", persons.stream()
                .<String>map(person -> person.toString()).collect(Collectors.toList()));
    }

}
