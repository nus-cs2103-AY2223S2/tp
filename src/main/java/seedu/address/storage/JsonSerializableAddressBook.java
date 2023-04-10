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
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "wheregottime")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_GROUP = "Groups list contains duplicate group(s).";
    public static final String MESSAGE_INCONSISTENT_GROUP = "Groups list is inconsistent with persons.";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedGroup> groups = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given groups.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("groups") List<JsonAdaptedGroup> groups) {
        this.persons.addAll(persons);
        this.groups.addAll(groups);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        groups.addAll(source.getGroupList().stream().map(JsonAdaptedGroup::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedGroup jsonAdaptedGroup : groups) {
            Group group = jsonAdaptedGroup.toModelType();
            if (addressBook.hasGroup(group)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GROUP);
            }
            addressBook.addGroup(group);
        }

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            for (Group group : person.getGroups()) {
                if (!addressBook.hasGroup(group)) {
                    throw new IllegalValueException(MESSAGE_INCONSISTENT_GROUP);
                }
            }
            addressBook.addPerson(person);
        }

        return addressBook;
    }

}
