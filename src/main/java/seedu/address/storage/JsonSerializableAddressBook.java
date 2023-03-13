package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "docedex")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "Doctors list contains duplicate doctors(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedDoctor> doctors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given doctors.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("doctors") List<JsonAdaptedDoctor> doctors) {
        this.doctors.addAll(doctors);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream()
                .map(JsonSerializableAddressBook::convertToJsonAdaptedPerson).collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code Person} into either a JsonAdaptedPerson or JsonAdaptedDoctor.
     *
     * @param person a person object.
     * @return a JsonAdaptedPerson.
     */
    private static JsonAdaptedPerson convertToJsonAdaptedPerson(Person person) {
        if (person instanceof Doctor) {
            return new JsonAdaptedDoctor((Doctor) person);
        } else {
            return new JsonAdaptedPerson(person);
        }
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        return addressBook;
    }

}
