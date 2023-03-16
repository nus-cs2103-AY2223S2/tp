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
import seedu.address.model.person.Person;
import seedu.address.model.person.parent.Parent;
import seedu.address.model.person.student.Student;
import seedu.address.storage.person.JsonAdaptedParent;
import seedu.address.storage.person.JsonAdaptedPerson;
import seedu.address.storage.person.JsonAdaptedStudent;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedParent> parents = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("parents") List<JsonAdaptedParent> parents) {
        this.persons.addAll(persons);
        this.students.addAll(students);
        this.parents.addAll(parents);
    }

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> person) {
        this.persons.addAll(persons);
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            if (jsonAdaptedPerson instanceof JsonAdaptedStudent) {
                students.add((JsonAdaptedStudent) person);
            } else if (person instanceof JsonAdaptedParent) {
                parents.add((JsonAdaptedParent) person);
            }
        }
    }


    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        parents.addAll(source.getParentList().stream().map(JsonAdaptedParent::new).collect(Collectors.toList()));
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
            if (jsonAdaptedPerson instanceof JsonAdaptedStudent) {
                addressBook.addStudent((Student) person);
            } else if (jsonAdaptedPerson instanceof JsonAdaptedParent) {
                addressBook.addParent((Parent) person);
            }
        }
        return addressBook;
    }

}
